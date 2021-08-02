package com.example.springdemoproject;

import com.example.springdemoproject.data.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(initializers = {TeacherTests.Initializer.class})
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@AutoConfigureMockMvc
public class TeacherTests {

    @Autowired
    private MockMvc mockMvc;

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
}
