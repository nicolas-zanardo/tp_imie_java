package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.RoleUser;
import fr.imie.fomation.api.repository.RoleUserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class RoleUserService {

    @Autowired
    private RoleUserRepository roleUserRepository;

    public Optional<RoleUser> getRoleUser(final Long id ) { return roleUserRepository.findById(id); }
    public Iterable<RoleUser> getRolesUser() { return roleUserRepository.findAll(); }
    public RoleUser saveRoleUser(RoleUser roleUser) {return roleUserRepository.save(roleUser);}
    public void deleteRoleUser(final Long id) { roleUserRepository.deleteById(id); }
    public List<RoleUser> findRoleUserByName(String roleName) { return roleUserRepository.findByRoleName(roleName); }
}
