package fr.imie.webapp.controller;

import fr.imie.webapp.model.Class;
import fr.imie.webapp.service.ClassService;
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
 * @author Nicolas Zanardo
 */
@Data
@Controller
public class ClassController {

    @Autowired
    private ClassService classeService;

    @GetMapping("/manage-class")
    public String manageRoomType(Model model) {
        Class classRoom = new Class();
        model.addAttribute("classRoom", classRoom);
        model.addAttribute("isEdit", false);
        listClassModel(model);
        return "manage-class";
    }

    @GetMapping("/manage-class/{id}")
    public String getRoomType(@PathVariable("id") final int id, Model model) {
        Class classRoom = classeService.getClass(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("classRoom", classRoom);
        listClassModel(model);
        return "manage-class";
    }

    @PostMapping("/save-class")
    public ModelAndView saveRoomType(@ModelAttribute Class classRoom) {
        if(!classRoom.getName().isEmpty()) {
            classRoom.setName(classRoom.getName().toLowerCase().trim());
            classRoom.setNbrPeople(classRoom.getNbrPeople());
            classeService.saveClass(classRoom);
        }
        return new ModelAndView("redirect:/manage-class");
    }

    @GetMapping("/delete-class/{id}")
    public ModelAndView deleteRoomType(@PathVariable("id") final int id, Model model) {
        classeService.deleteClass(id);
        return new ModelAndView("redirect:/manage-class");
    }


    private void listClassModel(Model model) {
        Iterable<Class> listClasses = classeService.getClasses();
        model.addAttribute("listClasses", listClasses);
    }
}
