package fr.imie.fomation.api.repository;


import fr.imie.fomation.api.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *  @author raouf
 */
@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
    List<Status> findAllById(Long status_id);
}

