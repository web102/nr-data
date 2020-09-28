package com.bobandata.iot.basedb.config;

import com.alibaba.fastjson.JSONObject;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import com.bobandata.iot.transport.util.TaskParam;
import com.bobandata.iot.transport.util.WebMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    private LinkedBlockingQueue<Session> queue = new LinkedBlockingQueue(10);

    private static IMasterProtocol mprotocol;

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
    public void onMessage(String message, Session session) throws IOException {
        TaskParam taskParam = JSONObject.parseObject(message, TaskParam.class);
        IMasterProtocol protocol = null;
        try {
            //通过消息中的规约对象，初始化一个实例
            Class clazz = Class.forName(taskParam.getRestPath());
            Constructor<IMasterProtocol> constructor = clazz.getConstructor();
            protocol = constructor.newInstance();
            mprotocol = protocol;
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage(session, new WebMessage(-1, "构造规约失败！").toJson());
            return;
        }

        try {
            mprotocol.init(taskParam, session);
        } catch (Exception e) {
            e.printStackTrace();
            sendMessage(session, new WebMessage(-1, "连接从站失败！").toJson());
            return;
        }

        try {
            mprotocol.executeTask(taskParam);
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            sendMessage(session, new WebMessage(-1, "规约执行失败").toJson());
        }
        sendMessage(session, new WebMessage(3, "连接断开!").toJson());
        session.close();
        System.out.println(session.isOpen());
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
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}