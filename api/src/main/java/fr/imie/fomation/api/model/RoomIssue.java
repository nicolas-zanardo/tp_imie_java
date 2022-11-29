package fr.imie.fomation.api.model;

import lombok.Data;
import javax.persistence.*;


/**
 *
 * @author jason
 */

@Data
@Entity
@Table(name = "room_issue")

public class RoomIssue {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_room", nullable = true)
    private Room room;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_room_status", nullable = false)
    private RoomStatus roomStatus;

}
