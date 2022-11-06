package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.RoleUser;
import fr.imie.fomation.api.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Nicolas Zanardo
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
