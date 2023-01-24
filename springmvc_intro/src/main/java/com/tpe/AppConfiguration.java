package com.tpe;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //this will be my configuration class
@ComponentScan ("com.tpe") //to be able tp use my classes without using new keyword.. IoC
//"com.tpe" from this package start scanning... by default it is the same but for code readablity
public class AppConfiguration {
}
