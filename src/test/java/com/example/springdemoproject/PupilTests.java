package com.example.springdemoproject;

import com.example.springdemoproject.data.Pupil;
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

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PupilTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test creation of Pupil
     */
    @Test
    @Order(1)
    public void NewPupilTest() throws Exception {

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

    }

    /**
     * Test delete of Pupil
     */
    @Test
    @Order(2)
    public void DeletePupilTest() throws Exception {

        String id = "17";

        mockMvc.perform(delete("/api/pupils")
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test get of Pupil (HQL)
     */
    @Test
    public void GetPupilHQLTest() throws Exception {

        String name = "Vladimir";
        this.mockMvc.perform(get("/api/pupils/find")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test get of Pupil by name (Specification)
     */
    @Test
    public void GetPupilSpecificationTest() throws Exception {

        String name = "Vladimir";
        this.mockMvc.perform(get("/api/pupils")
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk());
    }


    /**
     * Test update of Pupil
     */
    @Test
    public void UpdatePupilTest() throws Exception {

        String id = "3";
        Pupil pupil = new Pupil();
        pupil.setName("TestPupil");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pupil);

        this.mockMvc.perform(put("/api/pupils")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .param("id", id))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
