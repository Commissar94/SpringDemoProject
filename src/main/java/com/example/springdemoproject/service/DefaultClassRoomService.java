package com.example.springdemoproject.service;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.dto.ClassRoomData;
import com.example.springdemoproject.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("classRoomService")
public class DefaultClassRoomService implements ClassRoomService {

    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Override
    public ClassRoomData getClassRoomById(long schoolClassId) {
        return populateClassRoomData(classRoomRepository.findById(schoolClassId).orElseThrow(() -> new EntityNotFoundException("Class not found")));
    }


    @Override
    public ClassRoomData createClassRoom(ClassRoomData classData) {
        ClassRoom classModel = populateClassRoomEntity(classData);

        return populateClassRoomData(classRoomRepository.save(classModel));
    }

    @Override
    public List<ClassRoom> findByAttribute(String attribure) {
        return classRoomRepository.findClassRoomByClassRoom(attribure);
    }


    @Override
    public List<ClassRoom> getAll() {
        return classRoomRepository.findAll();
    }


    private ClassRoomData populateClassRoomData(ClassRoom classRoom) {
        ClassRoomData classRoomData = new ClassRoomData();
        classRoomData.setId(classRoom.getId());
        classRoomData.setClassRoom(classRoomData.getClassRoom());

        return classRoomData;
    }


    private ClassRoom populateClassRoomEntity(ClassRoomData classRoomData) {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(classRoomData.getId());
        classRoom.setClassRoom(classRoomData.getClassRoom());

        return classRoom;
    }
}
