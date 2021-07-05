package com.example.springdemoproject.controllers;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.dto.ClassRoomData;
import com.example.springdemoproject.dto.TeacherData;
import com.example.springdemoproject.service.ClassRoomService;
import com.example.springdemoproject.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/classRoom")
public class SchoolClassController {

    @Resource(name = "classRoomService")
    private ClassRoomService schoolClassService;

//    @Autowired
//    private TeacherService teacherService;

    @Resource(name = "teacherService")
    private TeacherService teacherService;

    /**
     * Method to get all classRooms.
     */
    @GetMapping
    List<ClassRoom> getClassRooms(){
        return schoolClassService.getAll();
    }

    /**
     * Method to get the class by classRoom value.
     */

@GetMapping("/find")
public List<ClassRoom> getClassByClassRoom(@RequestParam(value = "classRoom") String classRoom){
    return schoolClassService.findByAttribute(classRoom);
}
    /**
     * Method to get the class by id.
     */

    @GetMapping({"/{id}"})
    public ClassRoomData getClassById(@PathVariable Long id) {
        return schoolClassService.getClassRoomById(id);
    }

    /**
     * Method to assign teacher to classRoom.
     */

    @PutMapping("/{classId}/teachers/{teacherId}")
    ClassRoomData assignTeacherToClass(@PathVariable Long classId,
                                       @PathVariable Long teacherId) {
        ClassRoomData classData = schoolClassService.getClassRoomById(classId);
        TeacherData teacher = teacherService.getTeacherById(teacherId);
        classData.setTeacher(teacher);
        return schoolClassService.createClassRoom(classData);
    }
}
