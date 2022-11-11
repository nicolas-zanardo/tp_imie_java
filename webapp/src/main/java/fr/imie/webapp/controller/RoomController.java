package fr.imie.webapp.controller;

import fr.imie.webapp.model.Room;
import fr.imie.webapp.model.RoomFormData;
import fr.imie.webapp.model.RoomType;
import fr.imie.webapp.service.RoomService;
import fr.imie.webapp.service.RoomTypeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nicolas Zanardo
 */
@Data
@Controller
public class RoomController {

    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/manage-salle")
    public String manageRoom(Model model) {
        Room room = new Room();
        model.addAttribute("room", room);
        model.addAttribute("isEdit", false);
        listTypeRoomModel(model);
        listRoomModel(model);
        return "manage-room";
    }

    @GetMapping("/manage-salle/{id}")
    public String getRoom(@PathVariable("id") final int id, Model model) {
        Room room = roomService.getRoom(id);
        model.addAttribute("room", room);
        model.addAttribute("isEdit", true);
        model.addAttribute("idTypeRoom", room.getRoomType().getId());
        listTypeRoomModel(model);
        listRoomModel(model);
        return "manage-room";
    }


    @PostMapping("/save-salle")
    public ModelAndView saveRoom(RoomFormData roomFormData) {
        if(!roomFormData.getName().isEmpty() &&
                (roomFormData.getNbrPlace() > 0) &&
                (roomFormData.getRoomType() > 0)
        ) {
            RoomType roomType = roomTypeService.getTypeRoom(roomFormData.getRoomType());
            Room room = new Room();
            room.setId(roomFormData.getId());
            room.setRoomType(roomType);
            room.setNbrPlace(roomFormData.getNbrPlace());
            room.setName(roomFormData.getName().toLowerCase().trim());
            roomService.saveRoom(room);
        }
        return new ModelAndView("redirect:/manage-salle");
    }

    @GetMapping("/delete-salle/{id}")
    public ModelAndView deleteRoom(@PathVariable("id") final int id, Model model) {
        roomService.deleteRoom(id);
        return new ModelAndView("redirect:/manage-salle");
    }


    private void listTypeRoomModel(Model model) {
        Iterable<RoomType> listTypeRoom = roomTypeService.getTypeRooms();
        model.addAttribute("typeRoom", listTypeRoom);
    }

    private void listRoomModel(Model model) {
        Iterable<Room> listRoom = roomService.getRooms();
        model.addAttribute("listRoom", listRoom);
    }
}
