package com.example.springdemoproject.service;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("classRoomService")
public class DefaultClassRoomService implements ClassRoomService {

    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Override
    public ClassRoom getClassRoomById(long schoolClassId) {
        return classRoomRepository.findById(schoolClassId).get();
    }

    @Override
    public ClassRoom createClassRoom(ClassRoom classData) {
        return classRoomRepository.save(classData);
    }

    @Override
    public List<ClassRoom> findByAttribute(String attribure) {
        return classRoomRepository.findClassRoomByClassRoom(attribure);
    }

    @Override
    public List<ClassRoom> getAll() {
        return classRoomRepository.findAll();
    }

}
