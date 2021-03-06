package com.example.springdemoproject.service;

import com.example.springdemoproject.data.Teacher;
import com.example.springdemoproject.dto.TeacherData;
import com.example.springdemoproject.repository.TeacherRepository;
import com.example.springdemoproject.specification.TeacherSpecificationFindByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

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
    public Teacher getTeacherById(long teacherId) {
      //  return teacherRepository.findById(teacherId).get();
        return teacherRepository.findById(teacherId).orElse(null);
    }

    @Override
    public List<Teacher> findByNameQuery(String name) {
        return teacherRepository.findByNameQuery(name);
    }

    @Override
    public List<Teacher> findByNameSpecification(String name) {
        Specification<Teacher> spec = Specification.where(new TeacherSpecificationFindByName(name));
        return teacherRepository.findAll(spec);
    }

    private TeacherData populateTeacherData(Teacher teacher) {
        TeacherData teacherData = new TeacherData();
        teacherData.setId(teacher.getId());
        teacherData.setName(teacher.getName());
        teacherData.setSpecialization(teacher.getSpecialization());

        return teacherData;
    }

    private Teacher populateTeacherEntity(TeacherData teacherData) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherData.getName());
        teacher.setSpecialization(teacherData.getSpecialization());

        return teacher;
    }
}
