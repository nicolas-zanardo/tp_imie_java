package fr.imie.webapp.controller;

import fr.imie.webapp.model.TypeSalle;
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
public class ResponsableGestionController {

    @Autowired
    private TypeSalleService typeSalleService;

    @GetMapping("manage-type-salle")
    public String manageTypeSalle(Model model) {
        TypeSalle typeSalle = new TypeSalle();
        model.addAttribute("typeSalle", typeSalle);
        listTypeSalleModel(model);
        return "manage-type-salle";
    }

    @PostMapping("save-type-salle")
    public ModelAndView saveTypeSalle(@ModelAttribute TypeSalle typeSalle) {
        if(typeSalle.getId() != null) {
            System.out.println("TODO update" + typeSalle.getId());
        }
        typeSalleService.saveTypeSalle(typeSalle);
        return new ModelAndView("redirect:/manage-type-salle");
    }


    /**
     * @param model Model
     * @return Model
     */
    private Model listTypeSalleModel(Model model) {
        Iterable<TypeSalle> listTypeSale = typeSalleService.getTypeSalles();
        return model.addAttribute("typeSalles", listTypeSale);
    }
}
