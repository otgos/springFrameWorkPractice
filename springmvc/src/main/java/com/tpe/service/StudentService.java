package com.tpe.service;

import com.tpe.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();

    Student findStudentById(Long id); // we donot need write optional because it will be already handlerd in repo

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long id);
}
