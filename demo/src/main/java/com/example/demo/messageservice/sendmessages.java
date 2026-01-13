package com.example.demo.messageservice;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Component
public class sendmessages {

    @Value("${twilio_id}")
    public String twilioid;
    @Value("${twilio_token}")
    public String twiliotoken;


    public void sendmessage(String sendingmessage, String number) throws UnsupportedEncodingException {
        try {
            number="+91"+number;
           Twilio.init(twilioid, twiliotoken);
            Message message= Message.creator(new PhoneNumber(number),
                    new PhoneNumber("+15302966897"),
                    sendingmessage).create();
            System.out.println(message.getSid());
        } catch (Exception e) {
System.out.println(e);
        }

    }
}