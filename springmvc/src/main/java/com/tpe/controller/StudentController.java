package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students") // requests that came with "/students" end point will be handled hear
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/hi") //students/hi
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Helloo");
        mav.addObject("messagebody", "I am Student management system");
        mav.setViewName("hi"); //hi.jsp
        return mav;
        //added changes
    }

    @GetMapping("/new")
    public String sendStudentForm(@ModelAttribute("student") Student student){
        return "studentForm"; //@ModelAttribute added "student" obj to Model
    }

    @PostMapping ("/saveStudent") //student/saveStudent
    public String createStudent(@Valid @ModelAttribute Student student){
        service.saveStudent(student);

        return "redirect:/students";
    }

    @GetMapping()

    public ModelAndView getStudents(){
        List<Student> list = service.getAllStudent();

        ModelAndView mav = new ModelAndView();
        mav.addObject("students", list); //setting model

        return mav;
    }

    //it will work like this:
    //http://localhost:8080/springmvc/student/update?id=1
    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model){
        Student student = service.findStudentById(id);
        model.addAttribute(student);
        return "studentForm"; //will return studentForm.jsp  page

    }
    //http://localhost:8080/springmvc/student/delete/1

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        service.deleteStudent(id);
        return "redirect:/students";
        //added
    }

}
