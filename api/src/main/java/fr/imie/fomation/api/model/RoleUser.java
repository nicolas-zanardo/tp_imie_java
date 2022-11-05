package fr.imie.fomation.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Nicolas Zanardo
 */
@Data
@Entity
@Table(name= "ROLE")
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name= "ROLE_NAME", nullable = false)
    private String roleName;
}
