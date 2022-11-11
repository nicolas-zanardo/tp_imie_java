package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.RoomType;
import fr.imie.fomation.api.service.RoomTypeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    /**
     * Get All - type salle
     * @author Nicolas Zanardo
     * @return RoomTypeService
     */
    @GetMapping("/type-salles")
    public Iterable<RoomType> getRoomsType() {
        return roomTypeService.getTypeRooms();
    }

    /**
     * Create - new type salle
     * @author Nicolas Zanardo
     * @param roomType RoomType
     * @return RoomTypeService
     */
    @PostMapping("/add-type-salle")
    public RoomType createRoomType(@RequestBody RoomType roomType) {
        return roomTypeService.saveRoomType(roomType);
    }

    /**
     * Get By id - type salle
     * @author Nicolas Zanardo
     * @param id long
     * @return RoomType | null
     */
    @GetMapping("/type-salle/{id}")
    public RoomType getRoomTypeById (@PathVariable("id") final Long id) {
        Optional<RoomType> typeSalle = roomTypeService.getTypeRoom(id);
        return typeSalle.orElse(null);
    }

    /**
     * Put Edit - type salle
     * @author Nicolas Zanardo
     * @param id Long
     * @param roomType RoomType
     * @return RoomType | null
     */
    @PutMapping("/type-salle/{id}")
    public RoomType updateRoomType(@PathVariable("id") final Long id, @RequestBody RoomType roomType) {
        Optional<RoomType> t = roomTypeService.getTypeRoom(id);
        if(t.isPresent()) {
            RoomType currentRoomType = t.get();
            String name = roomType.getName();
            if(name != null) {
                currentRoomType.setName(name);
            }
            roomTypeService.saveRoomType(currentRoomType);
            return currentRoomType;
        } else {
            return null;
        }
    }

    /**
     * DELETE - type Salle
     * @author Nicolas Zanardo
     * @param id Long
     */
    @DeleteMapping("/type-salle-delete/{id}")
    public void deleteRoomType(@PathVariable("id") final Long id) {
        roomTypeService.deleteRoomType(id);
    }

}
