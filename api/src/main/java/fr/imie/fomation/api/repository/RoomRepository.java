package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jason
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    /**
     * @author Nicolas Zanardo
     */
    List<Room> findAllById(Long typeSalle_id);
}
