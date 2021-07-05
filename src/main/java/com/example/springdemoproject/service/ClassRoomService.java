package com.example.springdemoproject.service;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.dto.ClassRoomData;

import java.util.List;

public interface ClassRoomService {

    ClassRoomData getClassRoomById(long schoolClassId);

    ClassRoomData createClassRoom(ClassRoomData classRoom);

    List<ClassRoom> findByAttribute(String attribure);

    List<ClassRoom> getAll();
}
