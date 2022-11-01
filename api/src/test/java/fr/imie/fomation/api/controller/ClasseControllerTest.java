package fr.imie.fomation.api.controller;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.ClasseService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClasseControllerTest {

    @Autowired
    public ClasseService classService;

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
    public void testAddClasse() throws Exception {
        mockMvc.perform(post("/api/add-classe")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"nom\": \"MS2I\",\n" +
                        "    \"nombrePersonnes\": 28\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(classService.getClasses().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testGetTypeSalleById() throws Exception {
        mockMvc.perform(get("/api/classe/1")).andExpect(status().isOk()).andExpect(jsonPath("nom", is("MS2I")));
    }

    @Order(3)
    @Test
    public void testPutTypeSalle() throws Exception {
        mockMvc.perform(put("/api/update-classe/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"nom\": \"MS2ID\",\n" +
                "    \"nombrePersonnes\": 28\n" +
                "}")
        );
        mockMvc.perform(get("/api/classe/1")).andExpect(status().isOk()).andExpect(jsonPath("nom", is("MS2ID")));
    }

    @Order(6)
    @Test
    public void testDeleteTypeSalle() throws Exception {
        mockMvc.perform(delete("/api/delete-classe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(classService.getClasses().spliterator(), true).count());
    }

}