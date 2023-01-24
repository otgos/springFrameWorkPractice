package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplication {
    public static void main(String[] args) {
        Message message = new Message();
        message.setMessage("Your order has been received...");

        //Config file is indicated
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(AppConfiguration.class);

        //retrieving bean
//        MailService service = context.getBean(MailService.class); // it will create instance of mail service and send it to us

        //after adding this, run this one//
        //then change MailService.class to MessageService.class

        //it is getting interface as bean, it confuses which child to pass...and throws NoUniqueBeanDefinitionException

        // MessageService service = context.getBean(MessageService.class);

        //after adding SMS class run this again then add @Component
        MessageService service = context.getBean("whatsAppService", MessageService.class);

        service.sendMessage(message);


        context.close(); // life cycle of created instantiated beans will be ended...

    }
}
