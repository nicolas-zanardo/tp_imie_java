package fr.imie.fomation.api.model;

import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author jason
 */
@Data
@Entity
@Table(name= "learning_class")
public class Class {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name= "name", nullable = false)
    private String name;
    
    @Column(name= "nbr_people", nullable = false)
    private int nbrPeople;
}
