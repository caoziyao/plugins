package com.zel.market.app.service.mail;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
public class SmsServiceImpl implements SmsService{
    @Override
    public void send() {
        try {
            System.out.println("send sms");
            Thread.sleep(1000);
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
