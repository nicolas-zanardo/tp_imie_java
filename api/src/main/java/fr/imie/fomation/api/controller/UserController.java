package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.RoleUser;
import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Nicolas Zanardo
 */
@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<User> getUsers() { return userService.getUsers(); }

    @GetMapping("/user/{id}")
    public User getUser (@PathVariable("id") final  Long id) {
        Optional<User> getUser = userService.getUserById(id);
        return getUser.orElse(null);
    }

    @PostMapping("/add-user")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/update-user/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> putUser = userService.getUserById(id);
        if (putUser.isPresent()) {
            User currentUser = putUser.get();
            String lastname = user.getLastName();
            String firstname = user.getFirstName();
            String login = user.getLogin();
            String password = user.getPassword();
            RoleUser roles = user.getRole();
            if (lastname != null) {
                currentUser.setLastName(lastname.trim().toLowerCase());
            }
            if (firstname != null) {
                currentUser.setFirstName(firstname.trim().toLowerCase());
            }
            if (login != null) {
                currentUser.setLogin(login.trim());
            }
            if (password != null) {
                currentUser.setPassword(password);
            }
            if (roles != null ) {
                currentUser.setRole(roles);
            }
            userService.saveUser(currentUser);
            return currentUser;
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable("id") final Long id) { userService.deleteUser(id); }
}
