package fr.imie.webapp.controller;


import fr.imie.webapp.model.Status;
import fr.imie.webapp.service.StatusService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author raouf
 */

@Data
@Controller
public class StatusController {

    @Autowired
    private StatusService statusService;


    @GetMapping("/manage-statuses")
    public String manageStatus(Model model){
        Status status = new Status();
        model.addAttribute("status", status);
        model.addAttribute("isEdit", false);
        listStatusModel(model);
        return "manageStatus";
    }

    @GetMapping("/manage-status/{id}")
    public String getStatus(@PathVariable("id") final int id, Model model) {
        Status status = statusService.getStatus(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("status", status);
        listStatusModel(model);

        return "manage-status";
    }

    @PostMapping("/save-status")
    public ModelAndView saveStatus(@ModelAttribute Status status){
        if(!status.getName().isEmpty()){
            status.setName(status.getName().toLowerCase().trim());
            statusService.saveStatus(status);

        }
        return  new ModelAndView("redirect:/manage-statuses");
    }


    @GetMapping("/delete-status/{id}")
    public ModelAndView deleteStatus(@PathVariable("id") final int id, Model model){
        statusService.deleteStatus(id);
        return new ModelAndView("redirect:/manage-statuses");
    }
    private void    listStatusModel(Model model){

        Iterable<Status> listStatus = statusService.getStatuses();
        model.addAttribute("statuses", listStatus);
    }
}
