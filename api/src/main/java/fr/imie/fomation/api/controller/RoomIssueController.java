package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.RoomIssue;
import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.service.RoomIssueService;
import fr.imie.fomation.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class RoomIssueController {

    @Autowired
    private RoomIssueService RoomIssueService;

    @Autowired
    private UserService userService;

    @GetMapping("/room-issues")
    public Iterable<RoomIssue> getRoomIssues() {
        /**
         * Simulation d'une connection formateur sur son espace
         */
        // Recupérer un formateur

        Iterable<User> formateurs = userService.getUserByRoleName("teacher");
        for (User user : formateurs
             ) {
            System.out.println(user.getId());
        }

        // inserer l'id de ce formateur dans l'html
        return RoomIssueService.getRoomIssues();
    }



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
            if (roomIssue.getRoom() != null) {
                currentRoomIssue.setRoom(roomIssue.getRoom());
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
