package fr.imie.fomation.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.RoomTypeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.StreamSupport;

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomTypeControllerTest {


    @Autowired
    public RoomTypeService roomTypeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD TYPE SALLE --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }

    @Order(1)
    @Test
    public void testAddRoomType() throws Exception {
        mockMvc.perform(post("/api/add-type-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"informatique TEST\"\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roomTypeService.getTypeRooms().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testGetRoomTypeById() throws Exception {
        mockMvc.perform(get("/api/type-salle/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("informatique TEST")));
    }

    @Order(3)
    @Test
    public void testPutRoomType() throws Exception {
        mockMvc.perform(put("/api/type-salle/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"informatique\"\n" +
                "}")
        );
        mockMvc.perform(get("/api/type-salle/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("informatique")));
    }

    @Order(4)
    @Test
    public void testCanDeletedRoomType() throws Exception {
        mockMvc.perform(post("/api/add-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"RM 10\",\n" +
                        "    \"nbrPlace\": 20,\n" +
                        "    \"roomType\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"informatique\"\n" +
                        "    }\n" +
                        "}")
        );
        Assertions.assertTrue(roomTypeService.countRoomByType(roomTypeService.getTypeRoom(1L)));
    }

    @Order(5)
    @Test
    public void testCantDeletedRoomType() throws Exception {
        mockMvc.perform(delete("/api/delete-salle/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertFalse(roomTypeService.countRoomByType(roomTypeService.getTypeRoom(1L)));
    }

    @Order(6)
    @Test
    public void testDeleteRoomType() throws Exception {
        mockMvc.perform(delete("/api/type-salle-delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(roomTypeService.getTypeRooms().spliterator(), true).count());
    }

}