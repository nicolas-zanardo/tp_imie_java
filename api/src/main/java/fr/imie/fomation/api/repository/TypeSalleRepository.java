package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.TypeSalle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSalleRepository extends CrudRepository<TypeSalle, Long> {
}
