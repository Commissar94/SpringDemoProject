package com.example.springdemoproject.data;

import javax.persistence.*;

@Entity
@Table(name = "pupils")
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Class")
    private String schoolClass;

    public Pupil(String name, String schoolClass) {
        this.name = name;
        this.schoolClass = schoolClass;
    }

    public Pupil(Long id, String name, String schoolClass) {
        this.id = id;
        this.name = name;
        this.schoolClass = schoolClass;
    }

    public Pupil() {

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

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schoolClass='" + schoolClass + '\'' +
                '}';
    }
}
