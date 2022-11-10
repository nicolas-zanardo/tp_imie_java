package fr.imie.fomation.api.model;

import javax.persistence.*;
import lombok.Data;

/**
 * @author jason
 * @author Nicolas Zanardo => @ManyToOne
 */
@Data
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "nbr_place", nullable = false)
    private int nbrPlace;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_room_type", nullable = false)
    private RoomType roomType;
}
