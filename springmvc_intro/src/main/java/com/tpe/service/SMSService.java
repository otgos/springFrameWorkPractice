package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("smsService")
public class SMSService implements MessageService{
    @Autowired
    private Properties properties;
    @Override
    public void sendMessage(Message message) {
        System.out.println("I am SMS service, and I am sending you message: "+message.getMessage());
        System.out.println("Email: " +properties.get("app.email"));
        System.out.println("Java Home: "+properties.get("JAVA_HOME"));
    }

    @Override
    public void saveMessage(Message message) {
    }
}
