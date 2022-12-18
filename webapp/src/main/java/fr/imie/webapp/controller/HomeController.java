package fr.imie.webapp.controller;

import fr.imie.webapp.model.User;
import fr.imie.webapp.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Amina
 */
@Data
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String manageUsers(Model model) {

        Iterable<User> listUsers = userService.getUsers();
        model.addAttribute("listUsers", listUsers);

        return "home";
    }

}
