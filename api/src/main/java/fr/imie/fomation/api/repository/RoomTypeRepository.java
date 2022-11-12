package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nicolas Zanardo
 */
@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
}
