package fr.imie.webapp.service;

import fr.imie.webapp.model.Role;
import fr.imie.webapp.repository.RoleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class RoleService {

    @Autowired
    private RoleProxy roleUserProxy;

    // CREATE ROLE USER
    public void saveRole(Role roleUser) {
        Role saveRoleUser = null;
        if(roleUser.getId() == null) {
            saveRoleUser = roleUserProxy.createRole(roleUser);
        } else {
            saveRoleUser = roleUserProxy.updateRole(roleUser);
        }
    }

    // GET ALL ROLE USER
    public Iterable<Role> getAllRoles() { return roleUserProxy.getAllRole(); }

    // GET ALL ROLE BY NAME
    public Iterable<Role> getRolesByName(Role role) { return roleUserProxy.getRoleByName(role); }

    // GET ROLE USER BY ID
    public Role getRole(final int id) { return  roleUserProxy.getRoleById(id); }

    // DELETE ROLE USER BY ID
    public  void deleteRole(final int id) { roleUserProxy.deleteRole(id); }

}
