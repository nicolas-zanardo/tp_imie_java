package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Role;
import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.service.RoleService;
import fr.imie.fomation.api.service.UserService;
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
    @Autowired
    private UserService userService;

    // GET ALL ROLE USERS
    @GetMapping("/role-users")
    public Iterable<Role> getRoleUsers() { return roleUserService.getRolesUser(); }

    @GetMapping("/role-users-by-name/{name}")
    public Iterable<Role> getRolUserByName(@PathVariable("name") final  String name) {
        return roleUserService.findRoleUserByName(name);
    }

    // GET ROLE USER BY ID
    @GetMapping("/role-user/{id}")
    public Role getRoleUserById(@PathVariable("id") final  Long id) {
        Optional<Role> roleUser = roleUserService.getRoleUser(id);
        return roleUser.orElse(null);
    }

    // ADD ROLE USER
    @PostMapping("/add-role-user")
    public Optional<Role> createRoleUSer(@RequestBody Role roleUser) {
        Optional<Role> role = Optional.empty();
        if (roleUserService.findRoleUserByName(roleUser.getName()).isEmpty()) {
            role = Optional.ofNullable(roleUserService.saveRoleUser(roleUser));
        }
       return role;
    }

    // UPDATE ROLE USER
    @PutMapping("/update-role-user/{id}")
    public Role updateRoleUser(@PathVariable("id") final Long id, @RequestBody Role roleUser) {
        Optional<Role> role = roleUserService.getRoleUser(id);
        if(role.isPresent()) {
            Role currentRole = role.get();
            String roleName = roleUser.getName();
            if(roleName != null) {
                currentRole.setName(roleName);
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
