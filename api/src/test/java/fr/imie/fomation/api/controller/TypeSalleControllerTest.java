package fr.imie.fomation.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.TypeSalleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.StreamSupport;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TypeSalleControllerTest {


    @Autowired
    public TypeSalleService typeSalleService;

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
    public void testAddTypeSalle() throws Exception {
        mockMvc.perform(post("/api/add-type-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"nom\": \"informatique TEST\"\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(typeSalleService.getTypeSalles().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testGetTypeSalleById() throws Exception {
        mockMvc.perform(get("/api/type-salle/1")).andExpect(status().isOk()).andExpect(jsonPath("nom", is("informatique TEST")));
    }

    @Order(3)
    @Test
    public void testPutTypeSalle() throws Exception {
        mockMvc.perform(put("/api/type-salle/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"nom\": \"informatique\"\n" +
                "}")
        );
        mockMvc.perform(get("/api/type-salle/1")).andExpect(status().isOk()).andExpect(jsonPath("nom", is("informatique")));
    }

    @Order(4)
    @Test
    public void testCanDeletedTypeSalle() throws Exception {
        mockMvc.perform(post("/api/add-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"nom\": \"RM 10\",\n" +
                        "    \"nombrePlaces\": 20,\n" +
                        "    \"typeSalle\": {\n" +
                        "        \"id\": 1,\n" +
                        "        \"nom\": \"informatique\"\n" +
                        "    }\n" +
                        "}")
        );
        Assertions.assertEquals(true, typeSalleService.findTypeSalleUsedBySalle(typeSalleService.getTypeSalle(1L)));
    }

    @Order(5)
    @Test
    public void testCantDeletedTypeSalle() throws Exception {
        mockMvc.perform(delete("/api/delete-salle/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(false, typeSalleService.findTypeSalleUsedBySalle(typeSalleService.getTypeSalle(1L)));
    }

    @Order(6)
    @Test
    public void testDeleteTypeSalle() throws Exception {
        mockMvc.perform(delete("/api/type-salle-delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(typeSalleService.getTypeSalles().spliterator(), true).count());
    }

}