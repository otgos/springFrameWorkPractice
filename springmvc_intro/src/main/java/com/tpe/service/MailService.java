package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component ("mailService")// when scanner starts scanning it creates object and puts
// it inside container... which will be ready to be used without new keyword...
public class MailService implements MessageService{
    public void sendMessage(Message message){
        System.out.println("I am Mail service, and I am sending you message: "+message.getMessage());
    }
}
