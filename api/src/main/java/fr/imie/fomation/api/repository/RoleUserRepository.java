package fr.imie.fomation.api.repository;

import fr.imie.fomation.api.model.Classe;
import fr.imie.fomation.api.model.RoleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Nicolas Zanardo
 */
@Repository
public interface RoleUserRepository extends CrudRepository<RoleUser, Long> {
    List<RoleUser> findByRoleName(String roleName);
}
