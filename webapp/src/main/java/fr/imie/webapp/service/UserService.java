package fr.imie.webapp.service;

import fr.imie.webapp.model.User;
import fr.imie.webapp.repository.UserProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class UserService {

    @Autowired
    private UserProxy userProxy;

    // GET ALL USERS
    public Iterable<User> getUsers() { return userProxy.getUsers(); }
    // GET USER BY ID
    public User getUser(final int id) { return  userProxy.getUser(id); }
    // SAVE USER
    public void saveUser(User user) {
        User saveUser = null;
        if(saveUser.getId() == null) {
            saveUser = userProxy.createUser(user);
        } else {
            saveUser = userProxy.updateUser(user);
        }
    }
    // DELETE USER BY ID
    public void deleteUSer(final int id) { userProxy.deleteUser(id); }
}
