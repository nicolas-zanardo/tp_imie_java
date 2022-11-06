package fr.imie.webapp.controller;

import fr.imie.webapp.model.RoleUser;
import fr.imie.webapp.model.User;
import fr.imie.webapp.model.UserFormData;
import fr.imie.webapp.service.RoleUserService;
import fr.imie.webapp.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nicolas Zanardo
 */
@Data
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleUserService roleUserService;

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("isEdit", false);
        listUserModel(model);
        listRoleModel(model);
        return "manage-users";
    }

    @GetMapping("/manage-users/{id}")
    public String getUser(@PathVariable("id") final int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("isEdit", true);
        model.addAttribute("user", user);
        model.addAttribute("idRoleUSer", user.getRoleUser().getId());
        listUserModel(model);
        listRoleModel(model);
        return "manage-users";
    }

    @PostMapping("/save-user")
    public ModelAndView saveUser(UserFormData userFormData) {
        if(!userFormData.getFirstName().isEmpty() &&
                !userFormData.getLastName().isEmpty() &&
                !userFormData.getLogin().isEmpty() &&
                userFormData.getRoleUser() > 0
        ) {
            RoleUser roleUser = roleUserService.getRoleUser(userFormData.getRoleUser());
            User user = new User();
            user.setId(userFormData.getId());
            user.setPassword(userFormData.getPassword());
            user.setLogin(userFormData.getLogin());
            user.setFirstName(userFormData.getFirstName());
            user.setLastName(userFormData.getLastName());
            user.setRoleUser(roleUser);
            userService.saveUser(user);
        }
        return new ModelAndView("redirect:/manage-users");
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView deleteUser(@PathVariable("id") final int id, Model model) {
        userService.deleteUSer(id);
        return new ModelAndView("redirect:/manage-users");
    }

    private void listUserModel(Model model) {
        Iterable<User> listUsers = userService.getUsers();
        model.addAttribute("listUsers", listUsers);
    }

    private void listRoleModel(Model model) {
        Iterable<RoleUser> listRole = roleUserService.getAllRolesUser();
        model.addAttribute("listRole", listRole);
    }

}
