package fr.imie.fomation.api.controller;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.RoleService;
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
class RoleControllerTest {

    @Autowired
    public RoleService roleUserService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD ROLE USER --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }

    @Order(1)
    @Test
    public void testAddRoleUser() throws Exception {
        mockMvc.perform(post("/api/add-role-user")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"roleName\": \"eleve_test\"\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testGetRoleUserById() throws Exception {
        mockMvc.perform(get("/api/role-user/1")).andExpect(status().isOk()).andExpect(jsonPath("roleName", is("eleve_test")));
    }

    @Order(3)
    @Test
    public void testPutRoleUser() throws Exception {
        mockMvc.perform(put("/api/update-role-user/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"roleName\": \"eleve\"\n" +
                "}")
        );
        mockMvc.perform(get("/api/role-user/1")).andExpect(status().isOk()).andExpect(jsonPath("roleName", is("eleve")));
    }

    @Order(4)
    @Test
    public void testUniqueRoleName() throws Exception {
        mockMvc.perform(post("/api/add-role-user").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"roleName\": \"eleve\"\n" +
                "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());
    }

    @Order(6)
    @Test
    public void testDeleteRoleUser() throws Exception {
        mockMvc.perform(delete("/api/delete-role-user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());
    }

}