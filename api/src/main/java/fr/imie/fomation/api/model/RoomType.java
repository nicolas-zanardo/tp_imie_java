package fr.imie.fomation.api.model;


import lombok.Data;

import javax.persistence.*;

/**
 * @author Nicolas Zanardo
 */
@Data
@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

}
