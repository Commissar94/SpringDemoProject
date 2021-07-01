package com.example.springdemoproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Specialization")
    private String specialization;

    @Column(name = "Class")
    private String schoolClass;

    public Teacher(String name, String specialization, String schoolClass) {
        this.name = name;
        this.specialization = specialization;
        this.schoolClass = schoolClass;
    }

    public Teacher(Long id, String name, String specialization, String schoolClass) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.schoolClass = schoolClass;
    }

    public Teacher() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", schoolClass='" + schoolClass + '\'' +
                '}';
    }
}
