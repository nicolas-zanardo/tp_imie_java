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
    private String name;

    public String toString() {
        return "ROLE_" + stripAccentsAndSpace(name);
    }

    public String stripAccentsAndSpace(String name)
    {
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        name = name.replaceAll("\\s+","_");
        return name.toUpperCase();
    }

}
