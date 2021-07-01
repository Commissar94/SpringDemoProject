package com.example.springdemoproject.interfaces;

import com.example.springdemoproject.entities.Teacher;

public interface TeacherInterface {
    Teacher create(Teacher teacher);

    Teacher update(Teacher teacher);

    Teacher delete(long id);

    Teacher getById(long id);
}
