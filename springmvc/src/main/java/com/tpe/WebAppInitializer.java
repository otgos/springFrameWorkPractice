package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//instead of web.xml we will use this config class
//we need to register dispatcher servlet
//AbstractAnnotationConfigDispatcherServletInitializer ::--
// start dispathcer serve and indicate where configuration made

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
        Dispatcher Servlet WebContext -> Controller, Handler Mapping, View Resolver
        Root WebAppContext -> services and repositories
     */

    @Override
    //DB and Hibernate configuration
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override
    //Mvg config settings--Controller, Handler Mapping, View Resolver
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    //servlet and url mapping // which urls should e received by servlet
    protected String[] getServletMappings() {
        return new String[]{
                "/" // all requests will be received by this servlet
        };
    }
}
