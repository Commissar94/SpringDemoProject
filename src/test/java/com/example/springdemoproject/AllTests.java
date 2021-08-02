package com.example.springdemoproject;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.data.Pupil;
import com.example.springdemoproject.data.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(initializers = {AllTests.Initializer.class})
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@AutoConfigureMockMvc
public class AllTests {


    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer()
            .withDatabaseName("school")
            .withUsername("root")
            .withPassword("1234");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {


        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLContainer.getUsername(),
                    "spring.datasource.password=" + mySQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test create, update and delete of Pupil
     */
    @Test
    public void createUpdateDeletePupilTest() throws Exception {

        String id = "1";

        Pupil pupil = new Pupil();
        pupil.setName("TestPupil");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pupil);

        mockMvc.perform(post("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        Pupil pupilForUpdate = new Pupil();
        pupilForUpdate.setName("UpdatedTestPupil");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(pupilForUpdate);

        this.mockMvc.perform(put("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2)
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());


        mockMvc.perform(delete("/api/pupils")
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test create 2 Pupils and get one of them by name Pupil (HQL)
     */
    @Test
    public void getPupilHQLTest() throws Exception {

        Pupil pupil = new Pupil();
        pupil.setName("Vladimir");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pupil);

        mockMvc.perform(post("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        Pupil pupil2 = new Pupil();
        pupil2.setName("Dmitriy");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(pupil2);

        mockMvc.perform(post("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());


        String name = "Vladimir";
        this.mockMvc.perform(get("/api/pupils/find")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test create 2 Pupils and get one of them by name (Specification)
     */
    @Test
    public void getPupilSpecificationTest() throws Exception {

        Pupil pupil = new Pupil();
        pupil.setName("Vladimir");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pupil);

        mockMvc.perform(post("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        Pupil pupil2 = new Pupil();
        pupil2.setName("Dmitriy");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(pupil2);

        mockMvc.perform(post("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());


        String name = "Dmitriy";
        this.mockMvc.perform(get("/api/pupils")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test get of Teacher (HQL)
     */
    @Test
    public void getTeacherHQLTest() throws Exception {

        String name = "Valeriy";

        Teacher teacher = new Teacher();
        teacher.setName("Valeriy");
        teacher.setSpecialization("TestSpec");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(teacher);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        Teacher teacher2 = new Teacher();
        teacher2.setName("Andrey");
        teacher2.setSpecialization("TestSpec");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(teacher2);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());


        this.mockMvc.perform(get("/api/teachers/find")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test get of Teacher by name (Specification)
     */
    @Test
    public void getTeacherSpecificationTest() throws Exception {

        String name = "Valeriy";

        Teacher teacher = new Teacher();
        teacher.setName("Valeriy");
        teacher.setSpecialization("TestSpec");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(teacher);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        Teacher teacher2 = new Teacher();
        teacher2.setName("Andrey");
        teacher2.setSpecialization("TestSpec");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(teacher2);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(get("/api/teachers")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test create, update and delete of Teacher
     */
    @Test
    public void createUpdateDeleteTeacherTest() throws Exception {

        String id = "1";

        Teacher teacher = new Teacher();
        teacher.setName("TestTeacher");
        teacher.setSpecialization("TestSpec");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(teacher);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        Teacher teacherForUpdate = new Teacher();
        teacherForUpdate.setName("UpdatedTestTeacher");
        teacherForUpdate.setSpecialization("UpTestTest");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(teacherForUpdate);

        this.mockMvc.perform(put("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2)
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/teachers")
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test create 2 ClassRooms and get all of these ClassRooms
     */
    @Test
    public void getClassRoomsTest() throws Exception {

        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassRoom("6");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(classRoom);

        mockMvc.perform(post("/api/classRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        ClassRoom classRoom2 = new ClassRoom();
        classRoom.setClassRoom("7");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(classRoom2);

        mockMvc.perform(post("/api/classRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());


        this.mockMvc.perform(get("/api/classRoom"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test create and get ClassRoom of 5"A" by param
     */
    @Test
    public void getClassRoomByClassNameParamTest() throws Exception {
        String classRoomName = "5\"a\"";

        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassRoom("5\"a\"");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(classRoom);

        mockMvc.perform(post("/api/classRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());


        mockMvc.perform(get("/api/classRoom/find")
                .param("classRoom", classRoomName)
        ).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }

    /**
     * Test Create ClassRoom and assign teacher to the ClassRoom
     */
    @Test
    public void createClassRoomAndAssignTeacherToClassRoomTest() throws Exception {


        Teacher teacher = new Teacher();
        teacher.setName("TestTeacher");
        teacher.setSpecialization("TestSpec");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(teacher);

        mockMvc.perform(post("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());

        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassRoom("1");
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow2 = mapper2.writer().withDefaultPrettyPrinter();
        String json2 = ow2.writeValueAsString(classRoom);

        mockMvc.perform(post("/api/classRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json2))
                .andDo(print())
                .andExpect(status().isCreated());

        mockMvc.perform(put("/api/classRoom/{classRoomId}/teachers/{teacherId}", 1, 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
}