package fr.imie.fomation.api.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nicolas Zanardo
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleUserTest {


    @Test
    void testToString() {
        RoleUser formateur = new RoleUser();
        formateur.setRoleName("formateur");
        Assert.assertEquals(formateur.toString(), "ROLE_FORMATEUR");
    }

    @Test
    void testToStringWithAccent() {
        RoleUser eleve = new RoleUser();
        eleve.setRoleName("éléve");
        Assert.assertEquals(eleve.toString(), "ROLE_ELEVE");
    }

    @Test
    void stripAccentsAndSpace() {
        RoleUser eleve = new RoleUser();
        eleve.setRoleName("éléve");
        Assert.assertEquals("ELEVE", eleve.stripAccentsAndSpace(eleve.getRoleName()));
    }
}