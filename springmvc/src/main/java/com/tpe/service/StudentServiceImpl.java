package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //we cannot create obj from interface
public class StudentServiceImpl implements StudentService{

    //ask? why we are implementing interface not the concrete class????
    //answ: interface is main class, and I may change the name of parent class, or delete class...so it is loose coupling

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAllStudent() {
        return repository.getAll();
    }

    @Override
    public Student findStudentById(Long id) {
        Student student = repository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Student object not found with  id: "+id));
        return student;
    }

    @Override
    public void saveStudent(Student student) {
        repository.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        repository.update(student);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.delete(id);
    }
}
