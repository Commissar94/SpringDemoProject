package com.example.springdemoproject.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class_room")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "class_room")
    private String classRoom;


    @ManyToMany
    @JoinTable(
            name = "pupils_in_class_room",
            joinColumns = @JoinColumn(name = "class_room_id"),
            inverseJoinColumns = @JoinColumn(name = "pupil_id")
    )
    Set<Pupil> pupilsInClassRoom = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    public ClassRoom(long id, String schoolClass) {
        this.id = id;
        this.classRoom = schoolClass;
    }

    public ClassRoom(String schoolClass) {
        this.classRoom = schoolClass;
    }

    public ClassRoom() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Pupil> getPupilsInClassRoom() {
        return pupilsInClassRoom;
    }

    public void setPupilsInClassRoom(Set<Pupil> pupilsInClassRoom) {
        this.pupilsInClassRoom = pupilsInClassRoom;
    }


    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", schoolClass='" + classRoom + '\'' +
                '}';
    }
}
