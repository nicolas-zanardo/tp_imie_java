package fr.imie.webapp.service;

import fr.imie.webapp.model.RoomType;
import fr.imie.webapp.repository.RoomTypeProxy;
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
    private RoomTypeProxy roomTypeProxy;

    /**
     * CREATE type Salle
     * @param roomType RoomType
     */
   public void saveTypeRoom(RoomType roomType) {
       RoomType savedRoomType = null;
       if(roomType.getId() == null) {
           System.out.println(roomType);
           savedRoomType = roomTypeProxy.createTypeRoom(roomType);
       } else {
           savedRoomType = roomTypeProxy.updateTypeRoom(roomType);
       }
   }

    /**
     *  Get ALL type Salle
     * @return TypeSalleProxy
     */
    public Iterable<RoomType> getTypeRooms() {
       return roomTypeProxy.getTypeRooms();
   }

    /**
     * Get type salle
     * @param id int
     * @return typeSalleProxy
     */
   public RoomType getTypeRoom(final int id) {
        return roomTypeProxy.getTypeRoom(id);
   }

    /**
     * DELETE - type Salle
     *
     * @param id int
     * @return void
     */
    public void deleteTypeRoom(final int id) { roomTypeProxy.deleteTypeRoom(id); }
    
}
