package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration //without this configuration app will not start.. first thing that app will loock for is this config
@EnableWebMvc // indicating that we will work with MVC
@ComponentScan("com.tpe")
public class WebMvcConfig implements WebMvcConfigurer {
    //add viewResolver obj as a bean
    @Bean //why we did not used component
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/"); //idicating location of view folder
        resolver.setSuffix(".jsp"); //only consider about .jps extension
        return resolver;

    }

    //we are setting the location of our resources (css, image,  etc)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").
                addResourceLocations("/resources").setCachePeriod(0);
    }
}
