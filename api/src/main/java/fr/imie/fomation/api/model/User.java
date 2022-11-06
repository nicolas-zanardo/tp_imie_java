package fr.imie.fomation.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nicolas Zanardo
 */
@Data
@Entity
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private Long id;

    @Column(name="lastname", nullable = false)
    private String lastName;

    @Column(name="firstname", nullable = false)
    private String firstName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_role", nullable = false)
    private RoleUser role;

}
