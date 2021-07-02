package com.example.springdemoproject.service;

import com.example.springdemoproject.data.Teacher;
import com.example.springdemoproject.dto.TeacherData;
import com.example.springdemoproject.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("teacherService")
public class DefaultTeacherService implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public TeacherData createTeacher(TeacherData teacher) {
        Teacher teacherModel = populateTeacherEntity(teacher);

        return populateTeacherData(teacherRepository.save(teacherModel));
    }

    @Override
    public TeacherData updateTeacher(TeacherData teacher, long id) throws Exception {
        Teacher teacherModel = populateTeacherEntity(teacher);
        teacherModel.setId(id);
        teacherRepository.findById(id).orElseThrow(() -> new Exception("No teachers with this is"));

        return populateTeacherData(teacherRepository.save(teacherModel));
    }

    @Override
    public boolean deleteTeacher(long teacherId) {
        teacherRepository.deleteById(teacherId);

        return true;
    }

    @Override
    public TeacherData getTeacherById(long teacherId) {
        return populateTeacherData(teacherRepository.findById(teacherId).orElseThrow(() -> new EntityNotFoundException("Teacher not found")));
    }

    private TeacherData populateTeacherData(Teacher teacher) {
        TeacherData teacherData = new TeacherData();
        teacherData.setId(teacher.getId());
        teacherData.setName(teacher.getName());
        teacherData.setSchoolClass(teacher.getSchoolClass());
        teacherData.setSpecialization(teacher.getSpecialization());

        return teacherData;
    }

    private Teacher populateTeacherEntity(TeacherData teacherData) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherData.getName());
        teacher.setSchoolClass(teacherData.getSchoolClass());
        teacher.setSpecialization(teacherData.getSpecialization());

        return teacher;
    }
}
