package com.example.springdemoproject.controllers;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.data.Teacher;
import com.example.springdemoproject.service.ClassRoomService;
import com.example.springdemoproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classRoom")
public class SchoolClassController {

    @Autowired
    private ClassRoomService schoolClassService;

    @Autowired
    private TeacherService teacherService;

    /**
     * Method to get all classRooms.
     */
    @GetMapping
    List<ClassRoom> getClassRooms() {
        return schoolClassService.getAll();
    }

    /**
     * Method to get the class by classRoom value.
     */

    @GetMapping("/find")
    public List<ClassRoom> getClassByClassRoom(@RequestParam(value = "classRoom") String classRoom) {
        return schoolClassService.findByAttribute(classRoom);
    }

    /**
     * Method to get the class by id.
     */

    @GetMapping({"/{id}"})
    public ClassRoom getClassById(@PathVariable Long id) {
        return schoolClassService.getClassRoomById(id);
    }


    /**
     * Method to assign teacher to classRoom.
     */

    @PutMapping("/{classId}/teachers/{teacherId}")
    ClassRoom assignTeacherToClass(@PathVariable Long classId,
                                       @PathVariable Long teacherId) {
        ClassRoom classRoom = schoolClassService.getClassRoomById(classId);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        classRoom.setTeacher(teacher);
        return schoolClassService.createClassRoom(classRoom);
    }
}
