package fr.imie.fomation.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class TypeSalleControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testAddTypeSalle() throws Exception {
        mockMvc.perform(post("/add-type-salle")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"nom\": \"informatique test\"\n" +
                        "}")
        );
    }
}