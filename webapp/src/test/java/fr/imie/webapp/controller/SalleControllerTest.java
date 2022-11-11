package fr.imie.webapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
class SalleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTypeSalle() throws Exception {
        mockMvc.perform(get("/manage-salle"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("manage-room"))
                .andExpect(content().string(containsString("Salle")));
    }

}