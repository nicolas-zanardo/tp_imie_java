package fr.imie.webapp.controller;

import fr.imie.webapp.model.*;
import fr.imie.webapp.service.RoomIssueService;
import fr.imie.webapp.service.RoomService;
import fr.imie.webapp.service.RoomStatusService;
import fr.imie.webapp.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class RoomIssueController {

    @Autowired
    private RoomStatusService roomStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomIssueService roomIssueService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/manage-room-issue")
    public String manageRoomIssue(Model model) {
        RoomIssue roomIssue = new RoomIssue();
        model.addAttribute("roomIssue", roomIssue);
        model.addAttribute("isEdit", false);

        // 1 recupere l'user
        // model.addAttribute("idFormateur", user.getId());

        /**
         * Methode
         */
        listRoomModel(model);
        listRoomIssuesModel(model);
        listUserModel(model);
        listRoomStatusModel(model);
        return "manage-room-issue";
    }

    @GetMapping("/manage-room-issue/{id}")
    public String getRoomIssue(@PathVariable("id") final int id, Model model) {
        RoomIssue roomIssue = roomIssueService.getRoomIssue(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("roomIssue", roomIssue);
        model.addAttribute("idRoom", roomIssue.getRoom().getId());
        model.addAttribute("idUser", roomIssue.getUser().getId());
        model.addAttribute("idRoomStatus", roomIssue.getRoomStatus().getId());

        /**
         * Methode
         */

        listRoomModel(model);
        listRoomIssuesModel(model);
        listUserModel(model);
        listRoomStatusModel(model);
        return  "manage-room-issue";
    }

    @PostMapping("/save-room-issue")
    public ModelAndView saveRoomIssue(RoomIssueFormData roomIssueFormData) {
        System.out.println(roomIssueFormData);
        if (!roomIssueFormData.getName().isEmpty() &&
                (roomIssueFormData.getRoom() > 0) &&
                (roomIssueFormData.getUser() > 0) &&
                (roomIssueFormData.getRoomStatus() > 0)
        ) {
            Room room = roomService.getRoom(roomIssueFormData.getRoom());
            User user = userService.getUser(roomIssueFormData.getUser());
            RoomStatus roomStatus = roomStatusService.getStatus(roomIssueFormData.getRoomStatus());
            System.out.println(room);
            RoomIssue roomIssue = new RoomIssue();
            roomIssue.setId(roomIssueFormData.getId());
            roomIssue.setRoom(room);
            roomIssue.setUser(user);
            roomIssue.setRoomStatus(roomStatus);
            roomIssue.setName(roomIssueFormData.getName().toLowerCase().trim());
            roomIssueService.saveRoomIssue(roomIssue);
        }
        return new ModelAndView("redirect:/manage-room-issue");
    }

    @GetMapping("/delete-room-issue/{id}")
    public ModelAndView deleteRoomIssue(@PathVariable("id") final int id, Model model) {
        roomIssueService.deleteRoomIssue(id);
        return new ModelAndView("redirect:/manage-room-issue");
    }

    private void listRoomIssuesModel(Model model) {
        Iterable<RoomIssue> ListRoomIssue = roomIssueService.getRoomIssues();
        model.addAttribute("listRoomIssue", ListRoomIssue);
    }

    private void listRoomModel(Model model) {
        Iterable<Room> listRoom = roomService.getRooms();
        model.addAttribute("listRoom", listRoom);
    }

    private void listUserModel(Model model) {
        Iterable<User> listUser = userService.getUsers();
        model.addAttribute("listUser", listUser);
    }

    private void listRoomStatusModel(Model model) {
        Iterable<RoomStatus> roomStatuses = roomStatusService.getStatuses();
        model.addAttribute("listRoomStatus", roomStatuses);
    }

    // METHODE
}
