package com.example.springdemoproject.dto;

public class ClassRoomData {

    private long id;
    private String classRoom;
    private long teacher_id;

    private TeacherData teacher;

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

    public long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(long teacher_id) {
        this.teacher_id = teacher_id;
    }
}
