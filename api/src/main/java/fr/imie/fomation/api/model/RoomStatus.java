package fr.imie.fomation.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author raouf
 */

@Data
@Entity
@Table(name= "room_status")
public class RoomStatus {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column(name ="id", nullable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;


}
