package fr.imie.webapp.model;

import lombok.Data;

/**
 * @author Nicolas Zanardo
 */
@Data
public class Room {
    private Integer id;
    private String name;
    private Integer nbrPlace;
    private RoomType roomType;
}
