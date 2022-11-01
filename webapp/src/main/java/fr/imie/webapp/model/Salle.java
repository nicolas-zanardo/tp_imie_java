package fr.imie.webapp.model;

import lombok.Data;


@Data
public class Salle {

    private Integer id;
    private String nom;
    private Integer nombrePlaces;
    private TypeSalle typeSalle;

}
