package fr.imie.webapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
class TypeSalleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTypeSalle() throws Exception {
        mockMvc.perform(get("/manage-type-salle"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("manage-type-salle"))
                .andExpect(content().string(containsString("Type de salle")));
    }
}