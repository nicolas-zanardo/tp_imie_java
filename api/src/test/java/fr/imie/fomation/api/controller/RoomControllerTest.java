package fr.imie.fomation.api.controller;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.RoomService;
import fr.imie.fomation.api.service.RoomTypeService;
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

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomControllerTest {

    @Autowired
    public RoomService roomService;
    @Autowired
    public RoomTypeService roomTypeService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD SALLE --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }

    @Order(1)
    @Test
    public void testAddTypeSalle() throws Exception {
        mockMvc.perform(post("/api/add-type-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"ordinaire\"\n" +
                        "}")
        );
        mockMvc.perform(post("/api/add-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"RM 10\",\n" +
                        "    \"nbrPlace\": 20,\n" +
                        "    \"roomType\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"ordinaire\"\n" +
                        "    }\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roomTypeService.getTypeRooms().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testGetSalleById() throws Exception {
        mockMvc.perform(get("/api/salle/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("RM 10")));
    }

    @Order(3)
    @Test
    public void testPutSalle() throws Exception {
        mockMvc.perform(put("/api/update-salle/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"RM TEST\",\n" +
                "    \"nbrPlace\": 20,\n" +
                "    \"roomType\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"ordinaire\"\n" +
                "    }\n" +
                "}")
        );
        mockMvc.perform(get("/api/salle/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("RM TEST")));
    }

    @Order(4)
    @Test
    public void testDeleteSalle() throws Exception {
        mockMvc.perform(delete("/api/delete-salle/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(roomService.getRooms().spliterator(), true).count());
    }

}