package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;

public class MyApplication {
    public static void main(String[] args) {
        Message message = new Message();
        message.setMessage("Your order has been received...");

        //Config file is indicated
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(AppConfiguration.class);
        //with ComponentScan in Configuration class, it will scan all classes
        //and creates instances from each component class

        //retrieving bean
//        MailService service = context.getBean(MailService.class); // it will create instance of mail service and send it to us

        //after adding this, run this one//
        //then change MailService.class to MessageService.class

        //it is getting interface as bean, it confuses which child to pass...and throws NoUniqueBeanDefinitionException

        // MessageService service = context.getBean(MessageService.class);

        //after adding SMS class run this again then add @Component
        MessageService service = context.getBean("mailService", MessageService.class);



        //let s test if they are referring to the same object or not
        MessageService service2 = context.getBean("smsService", MessageService.class);
        if(service==service2){
            System.out.println("They are the same object");
        }else{
            System.out.println("They are NOT the same object");
        }


        //by default it has Singleton scope, wherever I call i will get the sam object...
        //if I want to create different object, then go to @Component where
        service.sendMessage(message);

        Point point = context.getBean("point",Point.class);
        System.out.println(point.getX());

        context.close(); // life cycle of created instantiated beans will be ended...

    }
}
