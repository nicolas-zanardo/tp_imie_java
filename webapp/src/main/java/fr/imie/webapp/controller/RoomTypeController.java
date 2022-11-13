package fr.imie.webapp.controller;

import fr.imie.webapp.model.RoomType;
import fr.imie.webapp.service.RoomTypeService;
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
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/manage-type-salle")
    public String manageTypeRoom(Model model) {
        RoomType typeRoom = new RoomType();
        model.addAttribute("typeRoom", typeRoom);
        model.addAttribute("isEdit", false);
        listTypeRoomModel(model);
        return "manage-room-type";
    }


    @GetMapping("/manage-type-salle/{id}")
    public String getTypeRoom(@PathVariable("id") final int id, Model model) {
        RoomType typeRoom = roomTypeService.getTypeRoom(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("typeRoom", typeRoom);
        listTypeRoomModel(model);
        return "manage-room-type";
    }

    /**
     * Save TypeSalle
     * @param roomType TypeSalle
     * @return ModelAndView
     */
    @PostMapping("/save-type-salle")
    public ModelAndView saveTypeRoom(@ModelAttribute RoomType roomType) {
        if(!roomType.getName().isEmpty()) {
            roomType.setName(roomType.getName().toLowerCase().trim());
            roomTypeService.saveTypeRoom(roomType);
        }
        return new ModelAndView("redirect:/manage-type-salle");
    }

    @GetMapping("/delete-type-salle/{id}")
    public ModelAndView deleteTypeRoom(@PathVariable("id") final int id, Model model) {
        roomTypeService.deleteTypeRoom(id);
        return new ModelAndView("redirect:/manage-type-salle");
    }

    /**
     * @param model Model
     */
    private void listTypeRoomModel(Model model) {
        Iterable<RoomType> listTypeRoom = roomTypeService.getTypeRooms();
        model.addAttribute("listTypeRoom", listTypeRoom);
    }
}
