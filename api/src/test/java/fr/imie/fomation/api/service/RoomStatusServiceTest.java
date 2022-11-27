package fr.imie.fomation.api.service;

import fr.imie.fomation.api._init_data.InitData;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomStatusServiceTest {

    @Autowired
    public RoomStatusService roomStatusService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD ROOM STATUS --");
        InitData.setup();
        System.out.println("-- setup() [ ok ] --");
    }

    @Order(1)
    @Test
    public void testRoomStatusAdd() throws Exception {
        mockMvc.perform(post("/api/add-status")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"init_TEST\"\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roomStatusService.getStatuses().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testRoomStatusGetById() throws Exception {
        mockMvc.perform(get("/api/status/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("init_TEST"))
        );
    }

    @Order(3)
    @Test
    public void testRoomStatusPut() throws Exception {
        mockMvc.perform(put("/api/update-status/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"init\"\n" +
                "}")
        );

        mockMvc.perform(get("/api/status/1"))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("name", is("init")));
    }

    @Order(4)
    @Test
    public void testUniqueNameRoomStatus() throws Exception {
        mockMvc.perform(post("/api/add-status").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"init\"\n" +
                "}"));
        Assertions.assertEquals(1, StreamSupport.stream(roomStatusService.getStatuses().spliterator(), true).count());
    }

    @Order(5)
    @Test
    public void testDeleteRoomStatus() throws Exception {
        mockMvc.perform(delete("/api/delete-status/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(roomStatusService.getStatuses().spliterator(), true).count());
    }

}