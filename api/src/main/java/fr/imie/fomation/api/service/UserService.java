package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.User;
import fr.imie.fomation.api.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    // GET ALL USER
    public Iterable<User> getUsers() {

        return  userRepository.findAll(); }

    // GET USER BY ID
    public Optional<User> getUserById(final Long id) { return userRepository.findById(id); }

    // SAVE USER
    public User saveUser(User user) { return userRepository.save(user); }

    //DELETE USER
    public void deleteUser(final Long id) { userRepository.deleteById(id); }

    //Get USER BY ROLE
    public Iterable<User> getUserByRoleName(String name) { return userRepository.findFirst1ByRole_Name(name); }
}
