package fr.imie.webapp.controller;

import fr.imie.webapp.model.RoomIssue;
import fr.imie.webapp.model.RoomIssueFormData;
import fr.imie.webapp.model.Salle;
import fr.imie.webapp.service.RoomIssueService;
import fr.imie.webapp.service.SalleService;
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
    private RoomIssueService roomIssueService;

    @Autowired
    private SalleService salleService;

    @GetMapping("/manage-room-issue")
    public String manageRoomIssue(Model model) {
        RoomIssue roomIssue = new RoomIssue();
        model.addAttribute("roomIssue", roomIssue);
        model.addAttribute("isEdit", false);
        listRoomIssuesModel(model);
        return "manage-room-issue";
    }

    @GetMapping("/manage-room-issue/{id}")
    public String getRoomIssue(@PathVariable("id") final int id, Model model) {
        RoomIssue roomIssue = roomIssueService.getRoomIssue(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("roomIssue", roomIssue);
        listRoomIssuesModel(model);
        return  "manage-room-issue";
    }

    @PostMapping("/save-room-issue")
    public ModelAndView saveRoomIssue(RoomIssueFormData roomIssueFormData) {
        if (!roomIssueFormData.getName().isEmpty() &&
                (roomIssueFormData.getSalle() > 0)
        ) {
            Salle salle = salleService.getSalle(roomIssueFormData.getSalle());
            RoomIssue roomIssue = new RoomIssue();
            roomIssue.setId(roomIssueFormData.getId());
            roomIssue.setSalle(salle);
            roomIssue.setName(roomIssueFormData.getName().toLowerCase().trim());
            roomIssueService.saveRoomIssue(roomIssue);
        }
        return new ModelAndView("redirect:/manage-room-issue");
    }

    @GetMapping("/delete-room-issue/{id}")
    public ModelAndView deleteRooomIssue(@PathVariable("id") final int id, Model model) {
        roomIssueService.deleteRoomIssue(id);
        return new ModelAndView("redirect:/manage-room-issue");
    }

    private void listRoomIssuesModel(Model model) {
        Iterable<RoomIssue> ListRoomIssues = roomIssueService.getRoomIssues();
        model.addAttribute("room-issues", ListRoomIssues);
    }

    private void listSalleModel(Model model) {
        Iterable<Salle> ListSalles = salleService.getSalles();
        model.addAttribute("salles", ListSalles);
    }
}
