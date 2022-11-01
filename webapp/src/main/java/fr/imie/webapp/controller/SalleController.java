package fr.imie.webapp.controller;

import fr.imie.webapp.model.Salle;
import fr.imie.webapp.model.SalleFormData;
import fr.imie.webapp.model.TypeSalle;
import fr.imie.webapp.service.SalleService;
import fr.imie.webapp.service.TypeSalleService;
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
public class SalleController {

    @Autowired
    private TypeSalleService typeSalleService;
    @Autowired
    private SalleService salleService;

    @GetMapping("/manage-salle")
    public String manageSalle(Model model) {
        Salle salle = new Salle();
        model.addAttribute("salle", salle);
        listTypeSalleModel(model);
        ListSallesModel(model);
        return "manage-salle";
    }

    @GetMapping("/manage-salle/{id}")
    public String getSalle(@PathVariable("id") final int id, Model model) {
        Salle salle = salleService.getSalle(id);
        model.addAttribute("salle", salle);
        listTypeSalleModel(model);
        ListSallesModel(model);
        return "manage-salle";
    }


    @PostMapping("/save-salle")
    public ModelAndView saveSalle(SalleFormData salleFormData) {
        if(!salleFormData.getNom().isEmpty() &&
                salleFormData.getNombrePlaces() > 0 &&
                salleFormData.getTypeSalle() > 0
        ) {
            Salle salle = new Salle();
            salle.setTypeSalle(typeSalleService.getTypeSalle(salleFormData.getTypeSalle()));
            salle.setNombrePlaces(salleFormData.getNombrePlaces());
            salle.setNom(salleFormData.getNom().toLowerCase().trim());
            salleService.saveSalle(salle);
        }
        return new ModelAndView("redirect:/manage-salle");
    }

    @GetMapping("/delete-salle/{id}")
    public ModelAndView deleteSalle(@PathVariable("id") final int id, Model model) {
        salleService.deleteSalle(id);
        return new ModelAndView("redirect:/manage-salle");
    }


    private void listTypeSalleModel(Model model) {
        Iterable<TypeSalle> listTypeSalles = typeSalleService.getTypeSalles();
        model.addAttribute("typeSalles", listTypeSalles);
    }

    private void ListSallesModel(Model model) {
        Iterable<Salle> ListSalles = salleService.getSalles();
        model.addAttribute("salles", ListSalles);
    }
}
