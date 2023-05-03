package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
@Repository // explain this
public class StudentRepositoryImpl implements StudentRepository{
    @Autowired// Since we are at DB layer we are injecting SessionFactory dependency
    //why we need this, we are working with hibernate....
    private SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
       Session session = sessionFactory.openSession();
       Transaction tx = session.beginTransaction();

       List<Student> listOfStudents = session.createQuery("FROM Student", Student.class).getResultList();

       tx.commit();
       session.close();
       //sessionFactory.close(); we dont need because spring will destroy its beans

       //we do not need to close sessionfactory because it may use session factory in other methods
        return  listOfStudents;

    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.get(Student.class, id);

        Optional <Student> opt;
        opt = Optional.ofNullable(student); // if there is no student, i will get empty student instead of null

        tx.commit();
        session.close();
        return opt;
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(student); // if there is student then update, if no then create... avoids null point exception

        tx.commit();
        session.close();
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(student);

        tx.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        //why don't we need load
        Student student = session.get(Student.class, id);

        session.delete(student);

        tx.commit();
        session.close();
    }
}
