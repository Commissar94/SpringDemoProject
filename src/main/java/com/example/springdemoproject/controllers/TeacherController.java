package com.example.springdemoproject.controllers;

import com.example.springdemoproject.entities.Teacher;
import com.example.springdemoproject.interfaces.TeacherInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController implements TeacherInterface {

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Teacher.class)
            .buildSessionFactory();

    @PostMapping
    @Override
    public Teacher create(Teacher teacher) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(teacher);
        long teacherFromDbId = teacher.getId();
        Teacher teacherFromDb = session.get(Teacher.class, teacherFromDbId);
        session.getTransaction().commit();

        return teacherFromDb;
    }

    @PutMapping
    @Override
    public Teacher update(Teacher teacher) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("update Teacher set name = :nameParam, " +
                "specialization = :specParam, " + "schoolClass = :classParam " +
                " where id = :idParam");
        query.setParameter("idParam", teacher.getId());
        query.setParameter("nameParam", teacher.getName());
        query.setParameter("specParam", teacher.getSpecialization());
        query.setParameter("classParam", teacher.getSchoolClass());
        query.executeUpdate();
        long teacherFromDbId = teacher.getId();
        Teacher teacherFromDb = session.get(Teacher.class, teacherFromDbId);
        session.getTransaction().commit();

        return teacherFromDb;
    }

    @DeleteMapping("/{id}")
    @Override
    public Teacher delete(@PathVariable long id) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Teacher teacherFromDb = session.get(Teacher.class, id);
        session.delete(teacherFromDb);
        session.getTransaction().commit();

        return teacherFromDb;
    }

    @GetMapping("/{id}")
    @Override
    public Teacher getById(@PathVariable long id) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Teacher teacherFromDb = session.get(Teacher.class, id);
        session.getTransaction().commit();

        return teacherFromDb;
    }
}
