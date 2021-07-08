package com.example.springdemoproject.service;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.dto.ClassRoomData;

import java.util.List;

public interface ClassRoomService {

    ClassRoom getClassRoomById(long schoolClassId);

    ClassRoom createClassRoom(ClassRoom classRoom);

    List<ClassRoom> findByAttribute(String attribure);

    List<ClassRoom> getAll();
}
