package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Room;
import fr.imie.fomation.api.repository.RoomRepository;
import java.util.Optional;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jason
 */
@Data
@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    public Optional<Room> getRoom(final Long id) {
        return roomRepository.findById(id);
    }
    
    public Iterable<Room> getRooms() {
        return roomRepository.findAll();
    }
    
    public void deleteRoom(final Long id) {
        roomRepository.deleteById(id);
    }
    
    public Room saveRoom(Room salle) {
        return roomRepository.save(salle);
    }

}
