package com.example.springdemoproject.repository;

import com.example.springdemoproject.data.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    @Query("FROM Teacher WHERE name = ?1")
    List<Teacher> findByNameQuery(String name);
}
