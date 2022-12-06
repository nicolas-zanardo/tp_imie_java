package fr.imie.webapp.model;

import lombok.Data;

@Data
public class RoomIssueFormData {
    private Integer id;
    private String name;
    private int room;
    private int user;
    private int roomStatus;
}
