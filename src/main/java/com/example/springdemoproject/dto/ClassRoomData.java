package com.example.springdemoproject.dto;

import com.example.springdemoproject.data.Teacher;

public class ClassRoomData {

    private long id;
    private long teacher_id;

    private TeacherData teacher;
    private String classRoom;

    public ClassRoomData(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeacherData getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherData teacher) {
        this.teacher = teacher;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
