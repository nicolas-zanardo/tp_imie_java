package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.RoomIssue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author jason
 */

@Repository
public interface RoomIssueRepository extends CrudRepository<RoomIssue, Long> {

    List<RoomIssue> findByRoomId(Long Salle_id);
}
