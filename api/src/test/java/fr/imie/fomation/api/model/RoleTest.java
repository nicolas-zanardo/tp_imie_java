package fr.imie.fomation.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleTest {


    @Test
    void testToString() {
        Role formateur = new Role();
        formateur.setName("formateur");
        Assertions.assertEquals(formateur.toString(), "ROLE_FORMATEUR");
    }

    @Test
    void testToStringWithAccent() {
        Role eleve = new Role();
        eleve.setName("éléve");
        Assertions.assertEquals(eleve.toString(), "ROLE_ELEVE");
    }

    @Test
    void stripAccentsAndSpace() {
        Role eleve = new Role();
        eleve.setName("éléve");
        Assertions.assertEquals("ELEVE", eleve.stripAccentsAndSpace(eleve.getName()));
    }
}