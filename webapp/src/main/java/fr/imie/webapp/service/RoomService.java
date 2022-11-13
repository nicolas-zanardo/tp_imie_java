package fr.imie.webapp.service;

import fr.imie.webapp.model.Room;
import fr.imie.webapp.repository.RoomProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class RoomService {

    @Autowired
    private RoomProxy roomProxy;

    public Room getRoom(final int id)  {
        return  roomProxy.getRoom(id);
    }

    public void saveRoom(Room room) {
        Room saveRoom = null;
        if(room.getId() == null) {
            saveRoom = roomProxy.createRoom(room);
        } else {
            saveRoom = roomProxy.updateRoom(room);
        }
    }

    public void deleteRoom(final int id) { roomProxy.deleteRoom(id); }

    public Iterable<Room> getRooms() {
        return roomProxy.getRooms();
    }
}
