package fr.imie.webapp.controller;

import fr.imie.webapp.model.Role;
import fr.imie.webapp.model.User;
import fr.imie.webapp.model.UserFormData;
import fr.imie.webapp.service.RoleService;
import fr.imie.webapp.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 * @author Nicolas Zanardo
 */
@Data
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleUserService;

    private String statusName = null;
    private String statusMessage = null;
    private boolean haveRole = true;

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        Iterable<Role> findRole = roleUserService.getAllRoles();
        if(StreamSupport.stream(findRole.spliterator(), true).findFirst().isEmpty()) {
            haveRole = false;
            model.addAttribute("noRole",
                    "Vous devez ajouter un rôle user avant de créer un utilisateur");
        }
        User user = new User();
        model.addAttribute("haveRole", haveRole);
        model.addAttribute("user", user);
        model.addAttribute("isEdit", false);
        listUserModel(model);
        listRoleModel(model);
        return "manage-users";
    }

    @GetMapping("/manage-users/{id}")
    public String getUser(@PathVariable("id") final int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("haveRole", haveRole);
        model.addAttribute("isEdit", true);
        model.addAttribute("user", user);
        model.addAttribute("idRoleUSer", user.getRole().getId());
        listUserModel(model);
        listRoleModel(model);
        return "manage-users";
    }

    @PostMapping("/save-user")
    public ModelAndView saveUserFormData(UserFormData userFormData, RedirectAttributes redirectAttr) {
        if(!userFormData.getFirstName().isEmpty() &&
                !userFormData.getLastName().isEmpty() &&
                !userFormData.getLogin().isEmpty() &&
                userFormData.getRole() > 0
        ) {
            int error = 0; // Count number of error form
            Iterable<User> getUserByLogin = userService.getUsersByLogin(userFormData.getLogin());

            // ADD USER
            if (userFormData.getId() == null) {
                if(StreamSupport.stream(getUserByLogin.spliterator(), true).findFirst().isPresent()) {
                    error++;
                }
            } else { // UPDATE USER
                for (User userByLogin: getUserByLogin) {
                    if (!userByLogin.getId().equals(userFormData.getId())) { // Check is different id
                        // find error
                        if (Objects.equals(userByLogin.getLogin(), userFormData.getLogin())) {
                            error++;
                        }
                    }
                }
            }
            // Send User for Update or Add
            if(error == 0) {
                statusName = "success";
                statusMessage = "l'utilisateur a bien été modifié";
                User user = new User();
                user.setId(userFormData.getId());
                user.setPassword(userFormData.getPassword());
                user.setLogin(userFormData.getLogin());
                user.setFirstName(userFormData.getFirstName());
                user.setLastName(userFormData.getLastName());
                user.setRole(roleUserService.getRole(userFormData.getRole()));
                userService.saveUser(user);
            } else {
                statusName = "warning";
                statusMessage = "Un utilisateur possède le même login";
            }

            redirectAttr.addFlashAttribute(statusName, statusMessage);
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
        Iterable<Role> listRole = roleUserService.getAllRoles();
        model.addAttribute("listRole", listRole);
    }

}
