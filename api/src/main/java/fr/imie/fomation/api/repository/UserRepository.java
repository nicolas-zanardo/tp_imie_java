package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.RoleUser;
import fr.imie.fomation.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nicolas Zanardo
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByRoleId(Long id_role);
}
