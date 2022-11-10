package fr.imie.fomation.api.model;

import org.junit.Assert;
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


//    @Test
//    void testToString() {
//        Role formateur = new Role();
//        formateur.setRoleName("formateur");
//        Assert.assertEquals(formateur.toString(), "ROLE_FORMATEUR");
//    }
//
//    @Test
//    void testToStringWithAccent() {
//        Role eleve = new Role();
//        eleve.setRoleName("éléve");
//        Assert.assertEquals(eleve.toString(), "ROLE_ELEVE");
//    }

//    @Test
//    void stripAccentsAndSpace() {
//        Role eleve = new Role();
//        eleve.setRoleName("éléve");
//        Assert.assertEquals("ELEVE", eleve.stripAccentsAndSpace(eleve.getRoleName()));
//    }
}