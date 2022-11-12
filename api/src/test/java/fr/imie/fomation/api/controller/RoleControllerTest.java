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
    public void testRoleUserAdd() throws Exception {
        mockMvc.perform(post("/api/add-role-user")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"eleve_test\"\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testRoleUserGetById() throws Exception {
        mockMvc.perform(get("/api/role-user/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("eleve_test")));
    }

    @Order(3)
    @Test
    public void testRoleUserPut() throws Exception {
        mockMvc.perform(put("/api/update-role-user/1").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"eleve\"\n" +
                "}")
        );
        mockMvc.perform(get("/api/role-user/1")).andExpect(status().isOk()).andExpect(jsonPath("name", is("eleve")));
    }

    @Order(4)
    @Test
    public void testRoleNameUnique() throws Exception {
        mockMvc.perform(post("/api/add-role-user").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"name\": \"eleve\"\n" +
                "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());
    }

    @Order(5)
    @Test
    public void testRoleCantBeDelete() throws Exception {
        mockMvc.perform((post("/api/add-user")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"lastName\": \"mustor\",\n" +
                        "    \"firstName\": \"mike\",\n" +
                        "    \"login\": \"log_mustor\",\n" +
                        "    \"password\": \"Aa123456789*2\",\n" +
                        "    \"role\": \n" +
                        "        {\n" +
                        "            \"id\":1,\n" +
                        "            \"name\": \"eleve\"\n" +
                        "        }\n" +
                        "}"))
        );
    Assertions.assertTrue(roleUserService.roleHaveUser(roleUserService.getRoleUser(1L)));
    }

    @Order(6)
    @Test
    public void testRoleCanDeleted() throws Exception {
        mockMvc.perform(delete("/api/delete-user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertFalse(roleUserService.roleHaveUser(roleUserService.getRoleUser(1L)));
    }


    @Order(7)
    @Test
    public void testDeleteRoleUser() throws Exception {
        mockMvc.perform(delete("/api/delete-role-user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());
    }

}