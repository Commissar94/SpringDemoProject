package com.example.springdemoproject.controllers;

import com.example.springdemoproject.data.Teacher;
import com.example.springdemoproject.dto.TeacherData;
import com.example.springdemoproject.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Resource(name = "teacherService")
    private TeacherService teacherService;

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