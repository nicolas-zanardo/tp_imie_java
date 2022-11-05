package fr.imie.fomation.api.model;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Nicolas Zanardo
 */
@Data
@Entity
@Table(name = "type_salle")
public class TypeSalle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="nom", nullable = false)
    private String nom;

}
