package fr.imie.webapp.model;

import lombok.Data;

/**
 * @author Nicolas Zanardo
 */
@Data
public class Salle {
    private Integer id;
    private String nom;
    private Integer nombrePlaces;
    private TypeSalle typeSalle;
}
