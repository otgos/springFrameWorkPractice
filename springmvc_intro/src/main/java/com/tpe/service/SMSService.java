package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component("smsService")
public class SMSService implements MessageService{
    @Override
    public void sendMessage(Message message) {
        System.out.println("I am SMS service, and I am sending you message: "+message.getMessage());
    }
}
