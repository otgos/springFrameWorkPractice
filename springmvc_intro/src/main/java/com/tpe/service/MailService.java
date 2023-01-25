package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.DbRepository;

import com.tpe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component ("mailService")// when scanner starts scanning it creates object and puts
// it inside container... which will be ready to be used without new keyword...
@Scope("prototype") //creates new object whenever I call this object
//default is "singleton"
public class MailService implements MessageService{
    @Value("${app.email}")
    private String email; //i want to make this to get the value from application.properties //email=  "email@email.com"


    //normally in java we do this:
    //and use object

    //Repository repository = new DbRepository();

    // now since we used component here

    //field injection
//    @Autowired//explain: finds object below from ioc container and injects it here as singleton scope.
//            //it is called Dependency Injectio
//    @Qualifier("dbRepository") //it used after autowired,if u have 2 concrete classes implementing from the same
//            // interface.. so we are writing this which concrete class is qualified
//    private Repository repository;

    //Setter injection
//    private Repository repository;
//
//    @Autowired
//    @Qualifier("dbRepository")
//    public void setRepository(Repository repository) {
//        this.repository = repository;
//    }


    //Constructor Injector
    private Repository repository;

    @Autowired
    public MailService(@Qualifier("dbRepository") Repository repository) {
        this.repository = repository;

    }






    public void sendMessage(Message message){
        System.out.println("I am Mail service, and I am sending you message: "+message.getMessage());
        repository.saveMessage(message);
        System.out.println(email);
    }
}
