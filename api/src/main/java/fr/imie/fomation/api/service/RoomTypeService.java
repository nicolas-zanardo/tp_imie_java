package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Room;
import fr.imie.fomation.api.model.RoomType;
import fr.imie.fomation.api.repository.RoomRepository;
import fr.imie.fomation.api.repository.RoomTypeRepository;
import java.util.Optional;
import java.util.stream.StreamSupport;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;
    @Autowired
    private RoomRepository roomRepository;

    /**
     * @author json
     * @param id Long
     * @return RoomType
     */
    public Optional<RoomType> getTypeRoom(final Long id) {
        return roomTypeRepository.findById(id);
    }

    /**
     * @author json
     * @return RoomType
     */
    public Iterable<RoomType> getTypeRooms() {
        return roomTypeRepository.findAll();
    }


    /**
     * @author Nicolas Zanardo
     * INSERT - Insert type salle
     * @param roomType RoomType
     * @return typeSalleRepository.save(typeSalle)
     */
    public RoomType saveRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    /**
     * @author Nicolas Zanardo
     * DELETE - Type Salle
     * @param id int
     */
    public void deleteRoomType(final Long id) {
        Optional<RoomType> typeSalle = getTypeRoom(id);
        if(!typeRoomHaveRoom(typeSalle)) {
            roomTypeRepository.deleteById(id);
        }
    }

    /**
     * @author Nicolas Zanardo
     * ITERABLE - FIND All SALLE BY TYPE
     * @param roomType Optional<RoomType>
     * @return Iterable<Salle>
     */
    public Iterable<Room> findAllRoomByType(Optional<RoomType> roomType) {
        return roomRepository.findAllById(roomType.get().getId());
    }

    /**
     * @author Nicolas Zanardo
     * BOOLEAN - Retourne True si la function countSalleByType contient une salle
     * @param roomType Optional<RoomType>
     * @return boolean
     */
    public boolean typeRoomHaveRoom(Optional<RoomType> roomType) {
        return StreamSupport.stream(findAllRoomByType(roomType).spliterator(), true).findAny().isPresent();
    }

}
