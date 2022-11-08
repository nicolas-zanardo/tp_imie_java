package fr.imie.fomation.api.model;

import lombok.Data;

import javax.persistence.*;
import java.text.Normalizer;

/**
 * @author Nicolas Zanardo
 */
@Data
@Entity
@Table(
        name= "role",
        uniqueConstraints={@UniqueConstraint(columnNames={"role_name"})}
)
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role", nullable = false)
    private Long id;

    @Column(name= "role_name", nullable = false)
    private String roleName;

    public String toString() {
        return "ROLE_" + stripAccentsAndSpace(roleName);
    }

    public String stripAccentsAndSpace(String roleName)
    {
        roleName = Normalizer.normalize(roleName, Normalizer.Form.NFD);
        roleName = roleName.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        roleName = roleName.replaceAll("\\s+","_");
        return roleName.toUpperCase();
    }

}
