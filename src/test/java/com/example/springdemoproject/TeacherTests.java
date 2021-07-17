package com.example.springdemoproject;

import com.example.springdemoproject.data.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test creation of Teacher
     */
    @Test
    @Order(1)
    public void NewTeacherTest() throws Exception {

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
    }

    /**
     * Test delete of Teacher
     */
    @Test
    @Order(2)
    public void DeleteTeacherTest() throws Exception {
        String id = "7";

        mockMvc.perform(delete("/api/teachers")
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test get of Teacher (HQL)
     */
    @Test
    public void GetTeacherHQLTest() throws Exception {

        String name = "Valeriy";
        this.mockMvc.perform(get("/api/teachers/find")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test get of Teacher by name (Specification)
     */
    @Test
    public void GetTeacherSpecificationTest() throws Exception {

        String name = "Valeriy";
        this.mockMvc.perform(get("/api/teachers")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test update of Teacher
     */
    @Test
    public void UpdateTeacherTest() throws Exception {

        String id = "4";
        Teacher teacher = new Teacher();
        teacher.setName("TestTeacher");
        teacher.setSpecialization("TestTest");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(teacher);

        this.mockMvc.perform(put("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
