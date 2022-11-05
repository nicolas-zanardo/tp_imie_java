package fr.imie.fomation.api.model;

import javax.persistence.*;
import lombok.Data;

/**
 * @author jason
 * @author Nicolas Zanardo => @ManyToOne
 */
@Data
@Entity
@Table(name = "salle")
public class Salle {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column (name = "nom", nullable = false)
    private String nom;


    @Column (name = "nombre_places", nullable = false)
    private int nombrePlaces;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_type_salle", nullable = false)
    private TypeSalle typeSalle;
}
