package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.Random;

@Component("smsService")
public class SMSService implements MessageService{


    @PostConstruct //runs just after constructor is called
    public void init(){
        System.out.println("New instance from SMS service is being created...");
    }


    @PreDestroy //this method will be called just before this obj destroyed.. final words of obj...))
    public void end(){
        System.out.println("Instance from SMS service is being destroyed...");
    }
    @Autowired
    private Properties properties;

    @Autowired
    //initially the value is null(before we add @Autowired annotation)
    //with above annotation, now it will create one instance from the Repository and INJECTS
    @Qualifier("fileRepository")
    private Repository repository;
    @Autowired
    private Random random;
    @Override
    public void sendMessage(Message message) {
        System.out.println("I am SMS service, and I am sending you message: "+message.getMessage());
        System.out.println("Random number: "+random.nextInt(100));
        System.out.println("Email: " +properties.get("app.email"));
        System.out.println("Java Home: "+properties.get("JAVA_HOME"));
    }

    @Override
    public void saveMessage(Message message) {
        repository.saveMessage(message);
    }

    //will get these variables from outside
    @Value("${app.email}")
    private String email;
    @Value("${app.phoneNu}")
    private String phoneNu;

    //method to get contact detail
    public void printContact(){
        //System.out.println("email: email@email.com"); //this is hardcoding
        //System.out.println("phone number: 13456788");
        System.out.println("email: "+email+" and "+phoneNu);
    }

    //method to get contact detail to read above data from properies
    public void printProperties(){
        System.out.println("contact email: "+properties.get("myEmail"));
    }
}
