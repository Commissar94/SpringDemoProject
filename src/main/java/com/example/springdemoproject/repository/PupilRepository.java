package com.example.springdemoproject.repository;

import com.example.springdemoproject.data.Pupil;
import org.springframework.data.repository.CrudRepository;

public interface PupilRepository extends CrudRepository<Pupil,Long> {
}
