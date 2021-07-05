package com.example.springdemoproject.repository;

import com.example.springdemoproject.data.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findClassRoomByClassRoom(String classRoom);
}
