package fr.imie.webapp.model;

import lombok.Data;

/**
 * @author Nicolas Zanardo
 */
@Data
public class User {
    private Integer id;
    private String lastName;
    private String firstName;
    private String login;
    private String password;
    private RoleUser role;
}
