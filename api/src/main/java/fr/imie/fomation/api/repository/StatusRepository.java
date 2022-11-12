package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *  @author raouf
 */
@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}
