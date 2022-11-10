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
        uniqueConstraints={@UniqueConstraint(columnNames={"name"})}
)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name= "name", nullable = false)
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
