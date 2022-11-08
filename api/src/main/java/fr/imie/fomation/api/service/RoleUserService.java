package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.RoleUser;
import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.repository.RoleUserRepository;
import fr.imie.fomation.api.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class RoleUserService {

    @Autowired
    private RoleUserRepository roleUserRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<RoleUser> getRoleUser(final Long id ) { return roleUserRepository.findById(id); }
    public Iterable<RoleUser> getRolesUser() { return roleUserRepository.findAll(); }
    public RoleUser saveRoleUser(RoleUser roleUser) {return roleUserRepository.save(roleUser);}
    public List<RoleUser> findRoleUserByName(String roleName) { return roleUserRepository.findByRoleName(roleName); }
    public void deleteRoleUser(final Long id) {
        Optional<RoleUser> roleUser = getRoleUser(id);
        if (!countUserByRole(roleUser)) {
            roleUserRepository.deleteById(id);
        }
    }

    /**
     * ITERABLE - FIND ALL USER BY ROLE
     *
     * @param roleUser Optional<RoleUser>
     * @return Iterable<User>
     */
    public Iterable<User> findAllUserByRole(Optional<RoleUser> roleUser) {
        return userRepository.findByRoleId(roleUser.get().getId());
    }

    /**
     * BOOLEAN - Retourne True si la function countUserByRole contient une salle
     * @param roleUser Optional<RoleUser>
     * @return boolean
     */
    public boolean countUserByRole(Optional<RoleUser> roleUser) {
        return StreamSupport.stream(findAllUserByRole(roleUser).spliterator(), true).findAny().isPresent();
    }
}
