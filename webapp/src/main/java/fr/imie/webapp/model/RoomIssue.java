package fr.imie.webapp.model;

import lombok.Data;

@Data
public class RoomIssue {
    private Integer id;
    private String name;
    private Room room;
    private User user;
    private RoomStatus roomStatus;
}
