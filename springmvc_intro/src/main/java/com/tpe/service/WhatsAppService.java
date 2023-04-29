package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component("whatsAppService")
public class WhatsAppService implements MessageService{
    @Override
    public void sendMessage(Message message) {
        System.out.println("I am WhatsApp service, and I am sending you message: "+message.getMessage());
    }

    @Override
    public void saveMessage(Message message) {
    }
}
