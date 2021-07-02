package com.example.springdemoproject.repository;

import com.example.springdemoproject.data.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
}
