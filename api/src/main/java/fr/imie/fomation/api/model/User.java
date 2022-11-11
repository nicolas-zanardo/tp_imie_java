package fr.imie.fomation.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Nicolas Zanardo
 */
@Data
@Entity
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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
    @JoinColumn(name = "id_role")
    private Role role;

}
