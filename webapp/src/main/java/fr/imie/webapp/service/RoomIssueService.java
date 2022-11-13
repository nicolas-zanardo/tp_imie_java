package fr.imie.webapp.service;

import fr.imie.webapp.model.RoomIssue;
import fr.imie.webapp.repository.RoomIssueProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class RoomIssueService {

    @Autowired
    private RoomIssueProxy roomIssueProxy;

    public void  saveRoomIssue(RoomIssue roomIssue) {
        RoomIssue savedRoomIssue = null;
        if (roomIssue.getId() == null) {
            savedRoomIssue = roomIssueProxy.createRoomIssue(roomIssue);
        } else {
            savedRoomIssue = roomIssueProxy.createRoomIssue(roomIssue);
        }
    }

    public Iterable<RoomIssue> getRoomIssues() { return roomIssueProxy.getRoomIssues();}

    public RoomIssue getRoomIssue(final int id) { return roomIssueProxy.getRoomIssue(id);}

    public void deleteRoomIssue(final int id) { roomIssueProxy.deleteRoomIssue(id);}
}
