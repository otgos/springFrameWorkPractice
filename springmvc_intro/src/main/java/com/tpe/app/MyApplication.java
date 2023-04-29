package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SMSService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.util.Random;

public class MyApplication {
    public static void main(String[] args) {
        Message message = new Message();
        message.setMessage("Your order has been received...");

        //Config file is indicated
        //read config class
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(AppConfiguration.class);
        //with ComponentScan in Configuration class, it will scan all classes
        //and creates instances from each component class, we can get them whenever we need

        //retrieving bean
      // MailService service = context.getBean(MailService.class); // it will create instance of mail service and send it to us
        //** we did not use "new" keyword

        //**then change MailService.class to MessageService.class

        //it is getting interface as bean, it confuses which child to pass...and throws NoUniqueBeanDefinitionException

        // MessageService service = context.getBean(MailService.class);


        //**after adding SMS class run this again then add @Component

        //IF  there are more than one class, which implements interface,
        // we have to clarify which concrete class we are using
//       MessageService service = context.getBean("mailService", MessageService.class);
//        service.sendMessage(message);

        //**after adding reposotory run this
        MessageService service = context.getBean("mailService", MessageService.class);
//        service.sendMessage(message);
//        service.saveMessage(message);

        //how to use @Bean
        //** How can we generate random numbers?
        //Random random = new Random(); //We will get this from Spring
        Random random = context.getBean(Random.class);
        System.out.println("Random number: "+random.nextInt(100));

        //if i need this random number in different classes, do I need to create instance using new keyword



        //let s test if they are referring to the same object or not
        MessageService service1 = context.getBean(MailService.class);
        MessageService service2 = context.getBean(MailService.class);
        if(service1==service2){
            System.out.println("They are the same object");
            System.out.println("Reference: "+service1);
            System.out.println("Reference: "+service2);
        }else{
            System.out.println("They are NOT the same object");
            System.out.println("Reference: "+service1);
            System.out.println("Reference: "+service2);
        }


        //** to show autowired, and bean show below example for Random class
        MessageService smsServoce = context.getBean("smsService", SMSService.class);
        smsServoce.sendMessage(message);

//
//
//        //by default it has Singleton scope, wherever I call i will get the sam object...
//        //if I want to create different object, then go to @Component where
//        service.sendMessage(message);
//
//        Point point = context.getBean("point",Point.class);
//        System.out.println(point.getX());

        /*
                to sum up:
                    1. when we add metadata by annotation to our pojo classes, spring will create one instance
                    2. the process where we outsource the control (whole lifecycle)to spring is called IoC
                    3. by default spring creates one instance, so scope is singleton
                    4. if we want we can create protype to create new instance different instances, but spring
                        is not responsible for the ending its lifecycle
                    5. a
         */

        //if i want to see all beans created by context
        String [] beanNames = context.getBeanDefinitionNames();
        for (String bean: beanNames) {
            System.out.println(bean);

        }

        SMSService smsService2 = context.getBean(SMSService.class);
        smsService2.printContact();
        smsService2.printProperties();


        context.close(); // life cycle of created instantiated beans will be ended...

    }
}
