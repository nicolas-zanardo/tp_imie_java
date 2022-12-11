package fr.imie.webapp.controller;

import fr.imie.webapp.model.Role;
import fr.imie.webapp.model.User;
import fr.imie.webapp.service.RoleService;
import fr.imie.webapp.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.StreamSupport;

/**
 * @author Nicolas Zanardo
 */
@Data
@Controller
public class RoleController {

    private Boolean statusExist = false;
    private String statusName = null;
    private String statusMessage = null;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    // CREATE ROLE USER
    @GetMapping("/manage-roles-user")
    public String manageRole(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        model.addAttribute("isEdit", false);
        listRoleUserModel(model);
        return "manage-role";
    }

    // UPDATE ROLE USER BY ID
    @GetMapping("/manage-roles-user/{id}")
    public String getRoleById(@PathVariable("id") final  int id, Model model) {
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);
        model.addAttribute("isEdit", true);
        listRoleUserModel(model);
        return "manage-role";
    }

    // SAVE ROLE USER
    @PostMapping("save-role-user")
    public ModelAndView saveRole(@ModelAttribute Role role, RedirectAttributes redirectAttr) {
        statusExist = true;
        statusName = "warning";
        statusMessage = "Vous devez entrer un role utilisateur";
        if(!role.getName().isEmpty()) {
            Iterable<Role> findShameName = roleService.getRolesByName(role);
            if(StreamSupport.stream(findShameName.spliterator(), true).findFirst().isPresent()) {
                statusName = "error";
                statusMessage = "Le rôle utilisateur existe déjà";
            } else {
                role.setName(role.getName().toLowerCase().trim());
                roleService.saveRole(role);
                statusName = "success";
                statusMessage = "Le rôle utilisateur à bien été ajouté";
            }
        }
        redirectAttr.addFlashAttribute(statusName, statusMessage);
        return new ModelAndView("redirect:/manage-roles-user");
    }

    // DELETE ROLE USER
    @GetMapping("/delete-role-user/{id}")
    public ModelAndView deleteRole(@PathVariable("id") final int id, RedirectAttributes redirectAttr) {
        Iterable<User> usersByRole = userService.getUsersByRole(id);
        if(StreamSupport.stream(usersByRole.spliterator(), true).findFirst().isPresent()) {
            statusName = "error";
            statusMessage = "Le rôle utilisateur n'a pas pu être supprimé car des utilisateurs possède ce rôle";
        } else {
            statusName = "success";
            statusMessage = "Le rôle utilisateur a été supprimer";
            roleService.deleteRole(id);
        }
        redirectAttr.addFlashAttribute(statusName, statusMessage);
        return new ModelAndView("redirect:/manage-roles-user");
    }

    // LIST GET ALL ROLE USER
    private void listRoleUserModel(Model model) {
        Iterable<Role> listRole = roleService.getAllRoles();
        model.addAttribute("listRole", listRole);
    }
}
