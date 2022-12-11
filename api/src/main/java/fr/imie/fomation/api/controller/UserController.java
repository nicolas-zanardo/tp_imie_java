package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Role;
import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
    public Iterable<User> getUsers() {return userService.getUsers(); }

    @GetMapping("/user/{id}")
    public User getUser (@PathVariable("id") final  Long id) {
        Optional<User> getUser = userService.getUserById(id);
        return getUser.orElse(null);
    }

    @PostMapping("/add-user")
    public User createUser(@RequestBody User user) {
        int error = 0; // Count number of error form
        Iterable<User> getUserByLogin = userService.getUserByLogin(user.getLogin());

        // ADD USER
        if (user.getId() == null) {
            if(StreamSupport.stream(getUserByLogin.spliterator(), true).findFirst().isPresent()) {
                error++;
            }
        } else { // UPDATE USER
            for (User userByLogin: getUserByLogin) {
                if (!userByLogin.getId().equals(user.getId())) { // Check is different id
                    // find error
                    if (Objects.equals(userByLogin.getLogin(), user.getLogin())) {
                        error++;
                    }
                }
            }
        }
        if(error == 0) {
            return userService.saveUser(user);
        } else {
            return new User();
        }

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
            Role roles = user.getRole();
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

    @GetMapping("/get-user-by-login/{login}")
    public Iterable<User> getUserByLogin (@PathVariable("login") final  String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping("/get-user-by-role/{roleId}")
    public Iterable<User> getUserByRole (@PathVariable("roleId") final Long roleId) {
        return userService.getUserByRole(roleId);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable("id") final Long id) { userService.deleteUser(id); }
}
