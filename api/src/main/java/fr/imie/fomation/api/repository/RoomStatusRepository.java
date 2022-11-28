package fr.imie.fomation.api.repository;


import fr.imie.fomation.api.model.RoomStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *  @author raouf
 */
@Repository
public interface RoomStatusRepository extends CrudRepository<RoomStatus, Long> {

    RoomStatus findByName(String name);
    List<RoomStatus> findAllById(Long status_id);
}

