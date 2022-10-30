package fr.imie.webapp.controller;

import fr.imie.webapp.model.Salle;
import fr.imie.webapp.model.TypeSalle;
import fr.imie.webapp.service.SalleService;
import fr.imie.webapp.service.TypeSalleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class SalleController {

    @Autowired
    private TypeSalleService typeSalleService;
    @Autowired
    private SalleService salleService;

    @GetMapping("manage-salle")
    public String manageSalle(Model model) {
        Salle salle = new Salle();
        model.addAttribute("salle", salle);
        listTypeSalleModel(model);
        return "manage-salle";
    }


    @PostMapping("save-salle")
    public ModelAndView saveTypeSalle(Model model) {
        System.out.println(model);
//        if(!salle.getNom().isEmpty()) {
//            salle.setNom(salle.getNom().toLowerCase().trim());
//
//            salleService.saveSalle(salle);
//        }
        return new ModelAndView("redirect:/manage-salle");
    }


    private void listTypeSalleModel(Model model) {
        Iterable<TypeSalle> listTypeSales = typeSalleService.getTypeSalles();
        model.addAttribute("typeSalles", listTypeSales);
    }
}
