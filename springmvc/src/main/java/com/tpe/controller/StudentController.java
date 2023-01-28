package com.tpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students") // requests that came with "/students" end point will be handled hear
public class StudentController {

    @GetMapping("/hi") //students/hi
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Helloo");
        mav.addObject("messagebody", "I am Student management system");
        mav.setViewName("hi"); //hi.jsp
        return mav;
        //added changes
    }
}
