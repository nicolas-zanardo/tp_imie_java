package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.RoleUser;
import fr.imie.fomation.api.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Nicolas Zanardo
 */
@RestController
@RequestMapping(path = "/api")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    // GET ALL ROLE USERS
    @GetMapping("/role-users")
    public Iterable<RoleUser> getRoleUsers() {
        Iterator<RoleUser> roles = roleUserService.getRolesUser().iterator();
        while (roles.hasNext()) {
            System.out.println(roles.next().toString());
        }
        return roleUserService.getRolesUser(); }

    // GET ROLE USER BY ID
    @GetMapping("/role-user/{id}")
    public RoleUser getRoleUserById(@PathVariable("id") final  Long id) {
        Optional<RoleUser> roleUser = roleUserService.getRoleUser(id);
        return roleUser.orElse(null);
    }

    // ADD ROLE USER
    @PostMapping("/add-role-user")
    public RoleUser createRoleUSer(@RequestBody RoleUser roleUser) {
        if (roleUserService.findRoleUserByName(roleUser.getRoleName()).isEmpty()) {
            return roleUserService.saveRoleUser(roleUser);
        }
        return new RoleUser();
    }

    // UPDATE ROLE USER
    @PutMapping("/update-role-user/{id}")
    public RoleUser updateRoleUser(@PathVariable("id") final Long id, @RequestBody RoleUser roleUser) {
        Optional<RoleUser> role = roleUserService.getRoleUser(id);
        if(role.isPresent()) {
            RoleUser currentRole = role.get();
            String roleName = roleUser.getRoleName();
            if(roleName != null) {
                currentRole.setRoleName(roleName);
            }
            roleUserService.saveRoleUser(currentRole);
            return  currentRole;
        } else {
            return  null;
        }
    }

    // DELETE ROLE USER
    @DeleteMapping("/delete-role-user/{id}")
    public void deleteRoleUser(@PathVariable("id") final Long id) {
        roleUserService.deleteRoleUser(id);
    }

}
