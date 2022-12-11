package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Role;
import fr.imie.fomation.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Nicolas Zanardo
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByRoleId(Long id_role);
    List<User> findFirst1ByRole_Name(String name);
    List<User> findByLogin(String login);

}


