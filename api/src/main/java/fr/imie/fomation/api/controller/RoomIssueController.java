package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.RoomIssue;
import fr.imie.fomation.api.service.RoomIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class RoomIssueController {

    @Autowired
    private RoomIssueService RoomIssueService;

    @GetMapping("/room-issues")
    public Iterable<RoomIssue> getRoomIssues() { return RoomIssueService.getRoomIssues();}

    @PostMapping("/add-room-issue")
    public RoomIssue createRoomIssue(@RequestBody RoomIssue roomIssue) { return RoomIssueService.saveRoomIssue(roomIssue); }

    @GetMapping("/room-issue/{id}")
    public RoomIssue getRoomIssue (@PathVariable("id") final Long id) {
        Optional<RoomIssue> roomIssue = RoomIssueService.getRoomIssue(id);
        return roomIssue.orElse(null);
    }

    @PutMapping("/update-room-issue/{id}")
    public RoomIssue updateRoomIssue(@PathVariable("id") final Long id, @RequestBody RoomIssue roomIssue) {
        Optional<RoomIssue> r = RoomIssueService.getRoomIssue(id);
        if(r.isPresent()) {
            RoomIssue currentRoomIssue = r.get();
            if (roomIssue.getName() != null) {
                currentRoomIssue.setName(roomIssue.getName());
            }
            if (roomIssue.getSalle() != null) {
                currentRoomIssue.setSalle(roomIssue.getSalle());
            }
            RoomIssueService.saveRoomIssue(currentRoomIssue);
            return currentRoomIssue;
        } else {
            return null ;
        }
    }

    @DeleteMapping("/delete-room-issue/{id}")
    public void deleteRoomIssue(@PathVariable("id") final Long id) { RoomIssueService.deleteRoomIssue(id);}

}
