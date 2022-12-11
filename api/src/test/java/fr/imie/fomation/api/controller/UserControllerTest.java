package fr.imie.fomation.api.controller;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.RoleService;
import fr.imie.fomation.api.service.UserService;
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
class UserControllerTest {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleUserService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD USER --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }

    @Order(1)
    @Test
    public void testUserAdd() throws Exception {
        mockMvc.perform(post("/api/add-role-user")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"name\": \"formateur_test\"\n" +
                        "}")
        );
        Assertions.assertEquals(1, StreamSupport.stream(roleUserService.getRolesUser().spliterator(), true).count());

        mockMvc.perform(post("/api/add-user")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"lastName\": \"mike_test\",\n" +
                        "    \"firstName\": \"fulong_test\",\n" +
                        "    \"login\": \"login_test\",\n" +
                        "    \"password\": \"password_test\",\n" +
                        "    \"role\": {\n" +
                                " \"id\": 1, \n" +
                                " \"name\": \"formateur_test\"\n" +
                            "}\n" +
                        "}")
        );
        Assertions.assertEquals(1,
                StreamSupport.stream(userService.getUsers().spliterator(), true).count());
    }

    @Order(2)
    @Test
    public void testUserGetById() throws Exception {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is("fulong_test")));
    }

    @Order(3)
    @Test
    public void testUserPut() throws Exception {
        mockMvc.perform(put("/api/update-user/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"lastName\": \"mike\",\n" +
                        "    \"firstName\": \"fulong\",\n" +
                        "    \"login\": \"login\",\n" +
                        "    \"password\": \"password\"\n" +
                        "}"));
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is("fulong"))
                );
    }

    @Order(4)
    @Test
    public void testLoginIsUniq() throws Exception {
        mockMvc.perform(post("/api/add-user")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"lastName\": \"uniqLastName_test\",\n" +
                        "    \"firstName\": \"uniqFistName_test\",\n" +
                        "    \"login\": \"login\",\n" +
                        "    \"password\": \"uniqPassword_test\",\n" +
                        "    \"role\": {\n" +
                        " \"id\": 1, \n" +
                        " \"name\": \"formateur_test\"\n" +
                        "}\n" +
                        "}")
        );
        Assertions.assertEquals(1,
                StreamSupport.stream(userService.getUsers().spliterator(), true).count());
    }

    @Order(5)
    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api//delete-user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, StreamSupport.stream(userService.getUsers().spliterator(), true).count());
    }


}