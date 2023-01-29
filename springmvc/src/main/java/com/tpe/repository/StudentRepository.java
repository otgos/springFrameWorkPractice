package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    //to get all students
    List<Student> getAll();

    //get specific student by Id
    Optional<Student> findById(Long id); // if i have 10 students and if i
    // request student whose id is 15 what will happen?? I will get null point exception so we add here Optional

    //save student
    void save(Student student);

    void update(Student student);

    void delete(Long id);


}
