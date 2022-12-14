package fr.imie.webapp.controller;

import fr.imie.webapp.model.RoomStatus;
import fr.imie.webapp.service.RoomStatusService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author raouf
 */

@Data
@Controller
public class RoomStatusController {

    private  Boolean statusExiste = false;
    @Autowired
    private RoomStatusService statusService;

    @GetMapping("/manage-status")
    public String manageStatus(Model model){

        RoomStatus status = new RoomStatus();

        model.addAttribute("status", status);
        model.addAttribute("isEdit", false);
        model.addAttribute("addNew", true);
        model.addAttribute("statusExiste",  statusExiste);
        listStatusModel(model);

        return "manage-status";
    }

    @GetMapping("/manage-status/{id}")
    public String getStatus(@PathVariable("id") final int id, Model model) {
        RoomStatus status = statusService.getStatus(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("status", status);
        listStatusModel(model);

        return "manage-status";
    }

    @PostMapping("/save-status")
    public ModelAndView saveStatus(@ModelAttribute RoomStatus status,  RedirectAttributes redirAttrs){
        if(!status.getName().isEmpty()){
            status.setName(status.getName().toLowerCase().trim());
            Iterable<RoomStatus>  list = statusService.getStatusProxy().getStatuses();
            for (RoomStatus li: list) {
                if(li.getName().equals(status.getName())){
                    statusExiste = true;
                    redirAttrs.addFlashAttribute("error", "Ce status existe d??j??.");

                   return new ModelAndView("redirect:/manage-status");
                    //return  new ModelAndView("redirect:/manage-status");
                }
            }
            statusService.saveStatus(status);
            redirAttrs.addFlashAttribute("success", "Le status est ajouter avec succ??s.");

        }
        return  new ModelAndView("redirect:/manage-status");
    }




    @GetMapping("/delete-status/{id}")
    public ModelAndView deleteStatus(@PathVariable("id") final int id, Model model){
        statusService.deleteStatus(id);
        return new ModelAndView("redirect:/manage-status");
    }
    private void    listStatusModel(Model model){

        Iterable<RoomStatus> listStatus = statusService.getStatuses();
        model.addAttribute("listStatus", listStatus);
    }
}
