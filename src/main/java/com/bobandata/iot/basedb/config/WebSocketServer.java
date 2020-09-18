package com.bobandata.iot.basedb.config;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSONObject;

import com.bobandata.iot.basedb.bean.TaskParam;
import com.bobandata.iot.transport.protocol.IMasterProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private static IMasterProtocol mprotocol;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        addOnlineCount();           //在线数加1
        logger.info("连接前段成功");
    }

    //	//连接打开时执行
    //	@OnOpen
    //	public void onOpen(@PathParam("user") String user, Session session) {
    //		currentUser = user;
    //		System.out.println("Connected ... " + session.getId());
    //	}

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        subOnlineCount();           //在线数减1
        logger.info("前端连接减1");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        if(message.equals("stop")) {
//            if (mprotocol != null) {
//                mprotocol.stopLinkIncrease();
//            }
//        }else if(message.equals("exit")){
//            mprotocol.stop();
//        }else{
//            if (mprotocol != null) {
//                mprotocol.stop();
//            }
            //通过websocke接收任务参数
            TaskParam taskParam = JSONObject.parseObject(message, TaskParam.class);
            IMasterProtocol protocol = null;
            try {
                //通过消息中的规约对象，初始化一个实例
                Class clazz = Class.forName(taskParam.getRestPath());


                com.bobandata.iot.transport.util.TaskParam taskParam1 = new com.bobandata.iot.transport.util.TaskParam();
                if(taskParam.getStartDate()!=null)taskParam1.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskParam.getStartDate()));
                if(taskParam.getEndDate()!=null)taskParam1.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(taskParam.getEndDate()));
                taskParam1.setStartMark(taskParam.getStartMark());
                taskParam1.setEndMark(taskParam.getEndMark());
                taskParam1.setIpAddress(taskParam.getIpAddress());
                taskParam1.setIpPort(taskParam.getIpPort());
                taskParam1.setTaskType(taskParam.getTaskType());
                taskParam1.setRestPath(taskParam.getLinkAddress());
                Constructor<IMasterProtocol> constructor = clazz.getConstructor(com.bobandata.iot.transport.util.TaskParam.class,Session.class);
                protocol = constructor.newInstance(taskParam1,session);
                mprotocol = protocol;
            } catch (Exception e) {
                e.printStackTrace();
                sendMessage("终端连接异常！");
            }
//        for (WebSocketServer item : webSocketSet) {
//            while(true){
//                try {
//                    String msg = protocol.massageQueue.poll(1000, TimeUnit.MILLISECONDS);
//                    if (msg != null){
//                        item.sendMessage(msg);
//                        Thread.sleep(300);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        }
    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
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