package fr.imie.webapp.controller;

import fr.imie.webapp.model.Classe;
import fr.imie.webapp.service.ClasseService;
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
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    @GetMapping("/manage-classes")
    public String manageTypeSalle(Model model) {
        Classe classe = new Classe();
        model.addAttribute("classe", classe);
        model.addAttribute("isEdit", false);
        listClasseModel(model);
        return "manage-classes";
    }

    @GetMapping("/manage-classe/{id}")
    public String getTypeSalle(@PathVariable("id") final int id, Model model) {
        Classe classe = classeService.getClasse(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("classe", classe);
        listClasseModel(model);
        return "manage-classes";
    }

    @PostMapping("/save-classe")
    public ModelAndView saveTypeSalle(@ModelAttribute Classe classe) {
        if(!classe.getNom().isEmpty()) {
            classe.setNom(classe.getNom().toLowerCase().trim());
            classe.setNombrePersonnes(classe.getNombrePersonnes());
            classeService.saveClasse(classe);
        }
        return new ModelAndView("redirect:/manage-classes");
    }

    @GetMapping("/delete-classe/{id}")
    public ModelAndView deleteTypeSalle(@PathVariable("id") final int id, Model model) {
        classeService.deleteClasse(id);
        return new ModelAndView("redirect:/manage-classes");
    }


    private void listClasseModel(Model model) {
        Iterable<Classe> listClasses = classeService.getClasses();
        model.addAttribute("classes", listClasses);
    }
}
