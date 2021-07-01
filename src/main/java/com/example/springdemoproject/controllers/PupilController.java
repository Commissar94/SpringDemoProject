package com.example.springdemoproject.controllers;

import com.example.springdemoproject.entities.Pupil;
import com.example.springdemoproject.interfaces.PupilInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pupils")
public class PupilController implements PupilInterface {

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Pupil.class)
            .buildSessionFactory();


    @PostMapping
    @Override
    public Pupil create(Pupil pupil) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(pupil);
        long pupilFromDbId = pupil.getId();
        Pupil pupilFromDb = session.get(Pupil.class, pupilFromDbId);
        session.getTransaction().commit();

        return pupilFromDb;
    }

    @PutMapping
    @Override
    public Pupil update(Pupil pupil) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("update Pupil set name = :nameParam, " +
                "schoolClass = :classParam" +
                " where id = :idParam");
        query.setParameter("idParam", pupil.getId());
        query.setParameter("nameParam", pupil.getName());
        query.setParameter("classParam", pupil.getSchoolClass());
        query.executeUpdate();
        long pupilFromDbId = pupil.getId();
        Pupil pupilFromDb = session.get(Pupil.class, pupilFromDbId);
        session.getTransaction().commit();

        return pupilFromDb;
    }

    @DeleteMapping("/{id}")
    @Override
    public Pupil delete(@PathVariable long id) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Pupil pupilFromDb = session.get(Pupil.class, id);
        session.delete(pupilFromDb);
        session.getTransaction().commit();

        return pupilFromDb;
    }

    @GetMapping("/{id}")
    @Override
    public Pupil getById(@PathVariable long id) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Pupil pupilFromDb = session.get(Pupil.class, id);
        session.getTransaction().commit();

        return pupilFromDb;
    }
}
