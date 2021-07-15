package com.example.springdemoproject.controllers;

import com.example.springdemoproject.data.Teacher;
import com.example.springdemoproject.dto.TeacherData;
import com.example.springdemoproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * Method to find teacher by query (HQL)
     */
    @GetMapping("/find")
    public List<Teacher> findPupilQuery(@RequestParam String name) {
        return teacherService.findByNameQuery(name);
    }


    /**
     * Method to find teacher by name
     */

    @GetMapping
    public List<Teacher> findPupil(@RequestParam("name") String name) {
        return teacherService.findByNameSpecification(name);
    }


    /**
     * Method to get the teacher.
     */

    @GetMapping({"/{id}"})
    public Teacher getTeachersById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    /**
     * Method to create new teacher.
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherData createTeacher(@RequestBody TeacherData teacherData) {
        return teacherService.createTeacher(teacherData);
    }

    /**
     * Method to delete the teacher.
     */

    @DeleteMapping
    public void deleteTeacher(@RequestParam(value = "id") long id) {
        teacherService.deleteTeacher(id);
    }

    /**
     * Method to update the teacher.
     */

    @PutMapping
    public TeacherData updateTeacher(@RequestBody TeacherData teacherData, @RequestParam(value = "id") long id) throws Exception {
        return teacherService.updateTeacher(teacherData, id);
    }
}