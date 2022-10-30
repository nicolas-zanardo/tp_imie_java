package fr.imie.webapp.controller;

import fr.imie.webapp.model.TypeSalle;
import fr.imie.webapp.service.TypeSalleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Data
@Controller
public class TypeSalleController {

    @Autowired
    private TypeSalleService typeSalleService;

    @GetMapping("manage-type-salle")
    public String manageTypeSalle(Model model) {
        TypeSalle typeSalle = new TypeSalle();
        model.addAttribute("typeSalle", typeSalle);
        listTypeSalleModel(model);
        return "manage-type-salle";
    }


    @GetMapping("manage-type-salle/{id}")
    public String getTypeSalle(@PathVariable("id") final int id, Model model) {
        TypeSalle typeSalle = typeSalleService.getTypeSalle(id);
        model.addAttribute("typeSalle", typeSalle);
        listTypeSalleModel(model);
        return "manage-type-salle";
    }

    /**
     * Save TypeSalle
     * @param typeSalle TypeSalle
     * @return ModelAndView
     */
    @PostMapping("save-type-salle")
    public ModelAndView saveTypeSalle(@ModelAttribute TypeSalle typeSalle) {
        if(!typeSalle.getNom().isEmpty()) {
            typeSalle.setNom(typeSalle.getNom().toLowerCase().trim());
            typeSalleService.saveTypeSalle(typeSalle);
        }
        return new ModelAndView("redirect:/manage-type-salle");
    }

    @GetMapping("delete-type-salle/{id}")
    public ModelAndView deleteTypeSalle(@PathVariable("id") final int id, Model model) {
        typeSalleService.deleteTypeSalle(id);
        return new ModelAndView("redirect:/manage-type-salle");
    }

    /**
     * @param model Model
     */
    private void listTypeSalleModel(Model model) {
        Iterable<TypeSalle> listTypeSales = typeSalleService.getTypeSalles();
        model.addAttribute("typeSalles", listTypeSales);
    }
}
