package fr.imie.fomation.api.controller;

import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
    private MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD USER --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }

}