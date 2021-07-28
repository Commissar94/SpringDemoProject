package com.example.springdemoproject;

import com.example.springdemoproject.data.ClassRoom;
import com.example.springdemoproject.data.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {ClassRoomTests.Initializer.class})
@AutoConfigureMockMvc

public class ClassRoomTests {

    @Autowired
    private MockMvc mockMvc;

    @ClassRule
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
     * Test get of all ClassRooms
     */
    @Test
    public void GetClassRoomsTest() throws Exception {
        this.mockMvc.perform(get("/api/classRoom"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test get ClassRoom of 7"A"
     */
    @Test
    public void GetSevenOfAClassRoomsTest() throws Exception {
        this.mockMvc.perform(get("/api/classRoom"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("7\\\"a\\\"")));
    }

    /**
     * Test get ClassRoom of 5"A" by param
     */
    @Test
    public void GetClassRoomByClassNameParamTest() throws Exception {
        String classRoom = "5\"a\"";
        mockMvc.perform(get("/api/classRoom/find")
                .param("classRoom", classRoom)
        ).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"));
    }


    @Test
    public void CreateClassRoom() throws Exception {

        ClassRoom classRoom = new ClassRoom();
        classRoom.setClassRoom("1");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(classRoom);

        mockMvc.perform(post("/api/classRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    /**
     * Test assign teacher to classRoom
     */
    @Test
    public void assignTeacherToClassRoomTest() throws Exception {

        String classRoomId = "7";
        String teacherId = "1";
        mockMvc.perform(put("/api/classRoom/{classRoomId}/teachers/{teacherId}", classRoomId, teacherId))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
