package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.awt.*;
import java.util.Properties;
import java.util.Random;

@Configuration //this will be my configuration class
@ComponentScan ("com.tpe") //to be able tp use my classes without using new keyword.. IoC
@PropertySource("classpath:application.properties")
//"com.tpe" from this package start scanning... by default it is the same but for code readablity


public class AppConfiguration {

    //if I need
    @Bean //creates a bean from Random class, and puts it in a container
    public Random randomInteger(){
        return new Random();
    }


    @Autowired // to access to app.properties it is from spring framework
    Environment environment;



    @Bean
    public Point point(){
        return new Point(56, 45);
    }
    //what is the main difference between @Component and @Bean???
    //If i try to inject builtin classes into my project, i can use them as method using bean annotation
    //it is method based annotation


    @Bean
    public Properties properties(){
        Properties properties = new Properties();
        properties.put("myEmail", environment.getProperty("app.email")); //wors as hashtable
       // properties.put("JAVA_HOME", environment.getProperty("JAVA_HOME"));
        return properties;
    }

}
