package fr.imie.webapp.service;

import fr.imie.webapp.model.RoleUser;
import fr.imie.webapp.repository.RoleUserProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class RoleUserService {

    @Autowired
    private RoleUserProxy roleUserProxy;

    // CREATE ROLE USER
    public void saveRoleUser(RoleUser roleUser) {
        RoleUser saveRoleUser = null;
        if(roleUser.getId() == null) {
            saveRoleUser = roleUserProxy.createRoleUser(roleUser);
        } else {
            saveRoleUser = roleUserProxy.updateRoleUser(roleUser);
        }
    }

    // GET ALL ROLE USER
    public Iterable<RoleUser> getAllRolesUser() { return roleUserProxy.getAllRoleUser(); }

    // GET ROLE USER BY ID
    public RoleUser getRoleUser(final int id) { return  roleUserProxy.getRoleUSerById(id); }

    // DELETE ROLE USER BY ID
    public  void deleteRoleUser(final int id) { roleUserProxy.deleteRoleUser(id); }

}
