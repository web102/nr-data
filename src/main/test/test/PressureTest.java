package test;

import com.bobandata.iot.basedb.controller.TestController;
import org.junit.Test;

import java.text.ParseException;

/**
 * @Author: 李志鹏
 * @Date: 2021/3/1 11:32
 * @Desc:
 * @param:
 * @return:
 **/
public class PressureTest {

    @Test
    public  void testOne() throws ParseException, InterruptedException {
        for(int i=0;i<10;i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    TestController testController = new TestController();
                    try {
                        testController.newTestProtocol("192.168.2.51");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        Thread.sleep(1000000000);
    }
}
