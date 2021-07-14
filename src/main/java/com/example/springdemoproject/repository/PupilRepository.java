package com.example.springdemoproject.repository;

import com.example.springdemoproject.data.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PupilRepository extends JpaRepository<Pupil, Long>, JpaSpecificationExecutor<Pupil> {
}
