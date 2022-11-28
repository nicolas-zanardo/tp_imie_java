package fr.imie.fomation.api.controller;


import fr.imie.fomation.api._init_data.InitData;
import fr.imie.fomation.api.service.RoomStatusService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("StatusControllerTest")
@DisplayName("unit testing status controller " )
public class StatusControllerTest {

    @Autowired
    RoomStatusService roomStatusService;
    @Autowired
    private MockMvc mockMvc;

    StatusControllerTest(){
    }
    @BeforeAll
    public static void setup() {
        System.out.println("-- CRUD USER --");
        InitData.setup();
        System.out.println("-- setup() [ OK ] --");
    }





}
