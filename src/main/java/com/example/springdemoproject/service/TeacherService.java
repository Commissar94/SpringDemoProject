package com.example.springdemoproject.service;

import com.example.springdemoproject.data.Teacher;
import com.example.springdemoproject.dto.TeacherData;

public interface TeacherService {

    TeacherData createTeacher (TeacherData teacher);
    TeacherData updateTeacher(TeacherData teacher, long id) throws Exception;
    boolean deleteTeacher(long teacherId);
    Teacher getTeacherById(long teacherId);
}
