package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.RoomIssue;
import fr.imie.fomation.api.repository.RoomIssueRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author jason
 */

@Data
@Service

public class RoomIssueService {

    @Autowired
    private RoomIssueRepository roomIssueRepository;

    public Optional<RoomIssue> getRoomIssue(final Long id) { return roomIssueRepository.findById(id);}

    public Iterable<RoomIssue> getRoomIssues() { return roomIssueRepository.findAll();}

    public void deleteRoomIssue(final Long id) { roomIssueRepository.deleteById(id);}

    public RoomIssue saveRoomIssue(RoomIssue roomIssue) { return roomIssueRepository.save(roomIssue);}
}
