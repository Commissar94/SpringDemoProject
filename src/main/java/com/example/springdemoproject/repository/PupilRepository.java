package com.example.springdemoproject.repository;

import com.example.springdemoproject.data.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PupilRepository extends JpaRepository<Pupil, Long>, JpaSpecificationExecutor<Pupil> {

    @Query("FROM Pupil WHERE name LIKE %:name%")
    List<Pupil> findByNameQuery(String name);
}
