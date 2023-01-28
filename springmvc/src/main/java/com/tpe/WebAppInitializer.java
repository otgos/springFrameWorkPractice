package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//instead of web.xml we will use this config class
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    //DB and Hibernate configuration
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    //Mvg config settings
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    //servlet and url mapping
    protected String[] getServletMappings() {
        return new String[]{
                "/" // all requests will be received by this servlet
        };
    }
}
