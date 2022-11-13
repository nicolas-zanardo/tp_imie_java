package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Room;
import fr.imie.fomation.api.service.RoomService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * @author jason
     * @return RoomService
     */
    @GetMapping("/salles")
    public Iterable<Room> getRooms() {
        return roomService.getRooms();
    }

    /**
     * @author jason
     * @param room Room
     * @return RoomService
     */
    @PostMapping("/add-salle")
    public Room createSalle(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    /**
     * @author jason
     * @param id Long
     * @return Room
     */
    @GetMapping("/salle/{id}")
    public Room getRoom (@PathVariable("id") final Long id) {
        Optional<Room> salle = roomService.getRoom(id);
        return salle.orElse(null);
    }

    /**
     * UPDATE - SALLE
     * @author jason
     * @author Nicolas Zanardo - Correction des setter
     * @param id Long
     * @param room Room
     * @return Room
     */
    @PutMapping("/update-salle/{id}")
    public Room updateSalle(@PathVariable("id") final Long id, @RequestBody Room room) {
        Optional<Room> s = roomService.getRoom(id);
        if(s.isPresent()) {
            Room currentRoom = s.get();
            if(room.getName() != null) {
                currentRoom.setName(room.getName());
            }
            if(room.getRoomType() != null) {
                currentRoom.setRoomType(room.getRoomType());
            }
            currentRoom.setNbrPlace(room.getNbrPlace());
            roomService.saveRoom(currentRoom);
            return currentRoom;
        } else {
            return null;
        }
    }

    /**
     * @author json
     * @param id Long
     */
    @DeleteMapping("/delete-salle/{id}")
    public void deleteSalle(@PathVariable("id") final Long id) {
        roomService.deleteRoom(id);
    }
}
