package com.example.springdemoproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassRoomTests {

    @Autowired
    private MockMvc mockMvc;

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
