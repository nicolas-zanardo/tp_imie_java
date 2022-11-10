package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Role;
import fr.imie.fomation.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Nicolas Zanardo
 */
@RestController
@RequestMapping(path = "/api")
public class RoleController {

    @Autowired
    private RoleService roleUserService;

    // GET ALL ROLE USERS
    @GetMapping("/role-users")
    public Iterable<Role> getRoleUsers() {
        // DEBUG -- TEST STRING OUTPUT ex: ROLE_FORMATION
//        Iterator<RoleUser> roles = roleUserService.getRolesUser().iterator();
//        while (roles.hasNext()) {
//            System.out.println(roles.next().toString());
//        }
        return roleUserService.getRolesUser(); }

    // GET ROLE USER BY ID
    @GetMapping("/role-user/{id}")
    public Role getRoleUserById(@PathVariable("id") final  Long id) {
        Optional<Role> roleUser = roleUserService.getRoleUser(id);
        return roleUser.orElse(null);
    }

    // ADD ROLE USER
    @PostMapping("/add-role-user")
    public Role createRoleUSer(@RequestBody Role roleUser) {
        if (roleUserService.findRoleUserByName(roleUser.getRoleName()).isEmpty()) {
            return roleUserService.saveRoleUser(roleUser);
        }
        return new Role();
    }

    // UPDATE ROLE USER
    @PutMapping("/update-role-user/{id}")
    public Role updateRoleUser(@PathVariable("id") final Long id, @RequestBody Role roleUser) {
        Optional<Role> role = roleUserService.getRoleUser(id);
        if(role.isPresent()) {
            Role currentRole = role.get();
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
