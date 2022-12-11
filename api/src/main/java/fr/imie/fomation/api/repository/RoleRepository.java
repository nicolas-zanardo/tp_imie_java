package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Role;
import fr.imie.fomation.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Nicolas Zanardo
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByName(String roleName);
}
