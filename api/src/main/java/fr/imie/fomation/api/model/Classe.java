package fr.imie.fomation.api.model;

import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author jason
 */
@Data
@Entity
@Table(name= "classe")
public class Classe {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name= "nom", nullable = false)
    private String nom;
    
    @Column(name= "nombre_personnes", nullable = false)
    private int nombrePersonnes;
}
