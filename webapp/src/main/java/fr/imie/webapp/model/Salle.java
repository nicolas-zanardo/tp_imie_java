package fr.imie.webapp.model;

import lombok.Data;


@Data
public class Salle {

    private Integer id;
    private String nom;
    private int nombrePlaces;
    private TypeSalle typeSalle;

}
