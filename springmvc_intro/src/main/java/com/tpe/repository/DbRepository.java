package com.tpe.repository;

import com.tpe.domain.Message;
import org.springframework.stereotype.Component;

@Component("dbRepository")
public class DbRepository implements Repository{
    @Override
    public void saveMessage(Message message) {
        System.out.println("Your message has been saved to DataBase: "+message.getMessage());
    }
}
