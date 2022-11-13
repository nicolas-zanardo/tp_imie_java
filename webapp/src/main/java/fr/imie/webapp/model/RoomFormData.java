package fr.imie.webapp.model;

import lombok.Data;

/**
 * @author Nicolas Zanardo
 */
@Data
public class RoomFormData {
    private Integer id;
    private String name;
    private int nbrPlace;
    private int roomType;

}
