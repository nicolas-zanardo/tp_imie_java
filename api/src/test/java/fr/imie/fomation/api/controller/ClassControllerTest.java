package fr.imie.fomation.api.controller;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.ClassService;
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
class ClassControllerTest {

    @Autowired
    public ClassService classService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD CLASSE --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }

    @Order(1)
    @Test
    public void testAddClass() throws Exception {
        mockMvc.perform(post("/api/add-classe")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"MS2I\",\n" +
                        "    \"nbrPeople\": 28\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(classService.getClasses().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testGetClassById() throws Exception {
        mockMvc.perform(get("/api/classe/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("MS2I")));
    }

    @Order(3)
    @Test
    public void testPutClass() throws Exception {
        mockMvc.perform(put("/api/update-classe/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"MS2ID\",\n" +
                "    \"nbrPeople\": 28\n" +
                "}")
        );
        mockMvc.perform(get("/api/classe/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("MS2ID")));
    }

    @Order(6)
    @Test
    public void testDeleteClass() throws Exception {
        mockMvc.perform(delete("/api/delete-classe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(classService.getClasses().spliterator(), true).count());
    }

}