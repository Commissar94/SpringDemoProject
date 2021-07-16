package com.example.springdemoproject;

import com.example.springdemoproject.controllers.PupilController;
import com.example.springdemoproject.controllers.SchoolClassController;
import com.example.springdemoproject.controllers.TeacherController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringDemoProjectApplicationTests {


    @Autowired
    private PupilController pupilController;

    @Autowired
    private TeacherController teacherController;

    @Autowired
    private SchoolClassController schoolClassController;


    @Test
    public void ControllersAreNotNullTest() throws Exception {
        assertThat(pupilController).isNotNull();
        assertThat(teacherController).isNotNull();
        assertThat(schoolClassController).isNotNull();
    }

}
