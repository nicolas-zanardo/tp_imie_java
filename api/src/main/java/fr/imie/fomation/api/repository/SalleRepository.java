package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Salle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author jason
 */
@Repository
public interface SalleRepository extends CrudRepository<Salle, Long> {
    List<Salle> findByTypeSalleId(Long typeSalle_id);
}
