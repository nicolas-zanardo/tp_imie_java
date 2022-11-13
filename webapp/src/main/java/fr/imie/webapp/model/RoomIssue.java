package fr.imie.webapp.model;

import lombok.Data;

@Data
public class RoomIssue {
    private Integer id;
    private String name;
    private Salle salle;
}
