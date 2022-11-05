package fr.imie.webapp.model;

import lombok.Data;

/**
 * @author Nicolas Zanardo
 */
@Data
public class SalleFormData {

    private Integer id;
    private String nom;
    private int nombrePlaces;
    private int typeSalle;

}
