package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students") //http://localhost:8080/SpringMvc/students/hi
// requests that came with "/students" end point will be handled hear
//RequestMapping can be used class level and method level
// when used class level all methods inside of that method, the path will be applied for all methods
public class StudentController {
    @Autowired
    private StudentService service;

    //ModelAndView = Holder for both Model and View in the web MVC framework.
    //it returns either ModelAndView obj (data+view)
    //or String view name
    @GetMapping("/hi") //students/hi
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Helloo");
        mav.addObject("messagebody", "I am Student management system");
        mav.setViewName("hi"); //hi.jsp //Set a view name for this ModelAndView, to be resolved by the DispatcherServlet via a ViewResolver.
        return mav;
        //view resolver finds hi.jsp file from location we have set
        //and binds data from mav to jsp file
    }

    //1 - Student Creation

    //we need to display form page to enter new data
    @GetMapping("/new") //http://localhost:8080/SpringMvc/students/new
    public String sendStudentForm(@ModelAttribute("student") Student student){
        return "studentForm"; //@ModelAttribute added "student" obj to Model
    }
    //@ModelAttribute--used to bind data from in view to model

    @PostMapping ("/saveStudent") //student/saveStudent
    public String createStudent(@Valid @ModelAttribute Student student){

        service.saveStudent(student);

        return "redirect:/students";
    }

    //to see error message on  form

//    @PostMapping ("/saveStudent") //student/saveStudent
//    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "studentForm";
//        }
//        service.saveStudent(student);
//
//        return "redirect:/students";
//    }

    //2 - Get All Students
    @GetMapping() //http://localhost:8080/SpringMvc/students

    public ModelAndView getStudents(){
        List<Student> list = service.getAllStudent();

        ModelAndView mav = new ModelAndView();
        mav.addObject("students", list); //setting model
        mav.setViewName("students"); //students.jsp
        return mav;
    }

    //3 - Update Students

    //it will work like this:
    //http://localhost:8080/springmvc/student/update?id=1
//    @GetMapping("/update")
//    public ModelAndView showFormForUpdate(@RequestParam("id") Long id){
//        Student foundStudent = service.findStudentById(id);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("student", foundStudent);
//        mav.setViewName("studentForm");
//        return mav; //will return studentForm.jsp  page
//
//    }

    //2nd way to update
    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model){
        Student foundStudent = service.findStudentById(id);
        model.addAttribute("student", foundStudent);
        return "studentForm";

    }


    //http://localhost:8080/springmvc/student/delete/1

    //4 - Delete Students
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        service.deleteStudent(id);
        return "redirect:/students";
        //added
    }


    //5- Exception Handling
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(Exception ex){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }


    //to return RestFul  //http://localhost:8080/springmvc/students/restAll
    @GetMapping("/restAll")
    @ResponseBody
    public List<Student> getAllStudentsInJson(){
        return service.getAllStudent();
    }



}
