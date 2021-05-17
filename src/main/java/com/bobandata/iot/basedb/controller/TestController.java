package com.bobandata.iot.basedb.controller;

import ias.extension.master.iec102.Iec102MasterProtocol;
import ias.extension.master.iec102.codec.Iec102CodecFactory;
import ias.extension.master.iec102.util.ManualTransport;
import net.njcp.ias.constants.ETaskType;
import net.njcp.ias.data.TaskParam;
import net.njcp.ias.entity.TaskItems;
import net.njcp.ias.protocol.IAsynProtocol;
import net.njcp.icodes.util.ConvertUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 李志鹏
 * @Date: 2020/10/30 11:46
 * @Desc:  测试机器的工作量
 * @param:
 * @return:
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(AcquiredController.class);

    @RequestMapping("/newProtocol")
    public void newTestProtocol (String ipAddr) throws ParseException {
        IAsynProtocol mprotocol;
        IoSession session = null;
        try {
            //通过消息中的规约对象，初始化一个实例
            mprotocol = new Iec102MasterProtocol();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return;
        }


        TaskParam taskParam = new TaskParam();
        taskParam.setTaskType(ETaskType.TEST_TASK);
        taskParam.setStartAddr(1);
        taskParam.setEndAddr(100);
        taskParam.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-8-27 00:00:00"));
        taskParam.setEndTime(new Date());
        taskParam.setTaskItem(new TaskItems((short) 120));
        taskParam.setRad(13);
        taskParam.setLinkAddr("0001");
        taskParam.setOccurTime(new Date());

        try {
            mprotocol.init(taskParam);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
             return;
        }

        try {
            SocketConnector connector;
            ConnectFuture future;

            connector = new NioSocketConnector();
            mprotocol.installProtocolFilter(connector.getFilterChain());
            ManualTransport transport = new  ManualTransport();
            connector.setHandler(transport);
            mprotocol.installTransport(transport);
            ipAddr = ipAddr==null?"127.0.0.1":ipAddr;
            future = connector.connect(new InetSocketAddress(ipAddr,2000));
//         等待连接创建完成
            future.awaitUninterruptibly();
            session = future.getSession();
            session.getConfig().setUseReadOperation(true);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

        try {
            mprotocol.execute(taskParam.getTaskItem());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
       }
        session.closeNow();
    }

    @RequestMapping("/new")
    public void newTest (String ipAddr) throws ParseException {
        SocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new Iec102CodecFactory()));
        connector.setHandler(new IoHandlerAdapter(){
            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                byte[] bytes = (byte[]) message;
                logger.info("响应："+ConvertUtil.bytes2hexstr(bytes," "));
                if(bytes.length>4&&bytes[4]==0x8){
                    session.closeNow();
                }else {
                    logger.info("请求：10 5A 01 00 5B 16");
                    session.write(IoBuffer.wrap(ConvertUtil.hexstr2bytes("10 5A 01 00 5B 16".replaceAll(" ",""))));
                }
            }
        });
        ConnectFuture future;
        ipAddr = ipAddr==null?"127.0.0.1":ipAddr;
        future = connector.connect(new InetSocketAddress(ipAddr,2000));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        session.getConfig().setUseReadOperation(true);
        logger.info("请求：68 15 15 68 33 01 00 78 00 06 01 00 0C 01 64 00 00 1B 08 14 00 00 0A 0B 14 84 16");
        session.write(IoBuffer.wrap(ConvertUtil.hexstr2bytes("68 15 15 68 33 01 00 78 00 06 01 00 0C 01 64 00 00 1B 08 14 00 00 0A 0B 14 84 16".replaceAll(" ",""))));

    }
}
