package com.bobandata.iot.basedb.config;

import com.alibaba.fastjson.JSONObject;
import com.bobandata.iot.transport.util.WebMessage;
import ias.extension.master.iec102.util.ManualTransport;
import net.njcp.ias.data.TaskParam;
import net.njcp.ias.data.WebParam;
import net.njcp.ias.entity.TaskItems;
import net.njcp.ias.protocol.IAsynProtocol;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingQueue;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer2 {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer2.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private LinkedBlockingQueue<Session> queue = new LinkedBlockingQueue<>(10);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        if (!queue.add(session)) {
            queue.remove().close();
            queue.add(session);
        }
        addOnlineCount();           //在线数加1
        logger.info("连接前段成功");
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        queue.remove(session);
        subOnlineCount();           //在线数减1
        logger.info("前端连接减1");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session webSession) throws IOException {
        IAsynProtocol mprotocol;
        WebParam webParam = JSONObject.parseObject(message, WebParam.class);
        IoSession session = null;
        try {
            //通过消息中的规约对象，初始化一个实例
            Class clazz = Class.forName(webParam.getRestPath());
            Constructor<IAsynProtocol> constructor = clazz.getConstructor();
            mprotocol = constructor.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            sendMessage(webSession, new WebMessage(WebMessage.Code.ERROR, "构造规约失败！").toJson());
            return;
        }


        TaskParam taskParam = new TaskParam();
        taskParam.setStartAddr(webParam.getStartMark());
        taskParam.setEndAddr(webParam.getEndMark());
        taskParam.setStartTime(webParam.getStartDate());
        taskParam.setEndTime(webParam.getEndDate());
        taskParam.setTaskItem(new TaskItems((short) webParam.getTaskType()));
        taskParam.setRad(webParam.getRad());
        taskParam.setLinkAddr("0001");
        taskParam.setWebSession(webSession);

        try {
            mprotocol.init(taskParam);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            sendMessage(webSession, new WebMessage(WebMessage.Code.ERROR, "连接从站失败！").toJson());
            return;
        }

        try {
            NioSocketConnector connector;
            ConnectFuture future;

            connector = new NioSocketConnector();
            mprotocol.installProtocolFilter(connector.getFilterChain());
            ManualTransport transport = new  ManualTransport();
            connector.setHandler(transport);
            mprotocol.installTransport(transport);
            future = connector.connect(new InetSocketAddress(webParam.getIpAddress(), webParam.getIpPort()));
//         等待连接创建完成
            future.awaitUninterruptibly();
            session = future.getSession();
            session.getConfig().setUseReadOperation(true);
        }catch (Exception e){
            sendMessage(webSession, new WebMessage(WebMessage.Code.CLOSE, "连接终端失败!").toJson());
            webSession.close();
            logger.error(e.getMessage(),e);
        }

        try {
            mprotocol.execute(taskParam.getTaskItem());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            sendMessage(webSession, new WebMessage(WebMessage.Code.ERROR, "规约执行失败").toJson());
        }
        sendMessage(webSession, new WebMessage(WebMessage.Code.CLOSE, "连接断开!").toJson());
        webSession.close();
        session.closeNow();
    }


    /**
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }


    private void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        onlineCount--;
    }
}