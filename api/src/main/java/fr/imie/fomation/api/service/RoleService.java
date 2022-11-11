package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Role;
import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.repository.RoleRepository;
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
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Role> getRoleUser(final Long id ) { return roleRepository.findById(id); }
    public Iterable<Role> getRolesUser() { return roleRepository.findAll(); }
    public Role saveRoleUser(Role roleUser) {return roleRepository.save(roleUser);}
    public List<Role> findRoleUserByName(String roleName) { return roleRepository.findByName(roleName); }
    public void deleteRoleUser(final Long id) {
        Optional<Role> roleUser = getRoleUser(id);
        if (!roleHaveUser(roleUser)) {
            roleRepository.deleteById(id);
        }
    }

    /**
     * ITERABLE - FIND ALL USER BY ROLE
     *
     * @param roleUser Optional<RoleUser>
     * @return Iterable<User>
     */
    public Iterable<User> findAllUserByRole(Optional<Role> roleUser) {
        return userRepository.findByRoleId(roleUser.get().getId());
    }

    /**
     * BOOLEAN - Retourne True si la function countUserByRole contient une salle
     * @param roleUser Optional<RoleUser>
     * @return boolean
     */
    public boolean roleHaveUser(Optional<Role> roleUser) {
        return StreamSupport.stream(findAllUserByRole(roleUser).spliterator(), true).findAny().isPresent();
    }
}
