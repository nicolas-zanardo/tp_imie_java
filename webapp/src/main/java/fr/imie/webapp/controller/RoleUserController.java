package fr.imie.webapp.controller;

import fr.imie.webapp.model.RoleUser;
import fr.imie.webapp.service.RoleUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nicolas Zanardo
 */
@Data
@Controller
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    // CREATE ROLE USER
    @GetMapping("/manage-roles-user")
    public String manageRoleUser(Model model) {
        RoleUser roleUser = new RoleUser();
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("isEdit", false);
        listRoleUserModel(model);
        return "manage-roles-user";
    }

    // UPDATE ROLE USER BY ID
    @GetMapping("/manage-roles-user/{id}")
    public String getRoleUSerById(@PathVariable("id") final  int id, Model model) {
        RoleUser roleUser = roleUserService.getRoleUser(id);
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("isEdit", true);
        listRoleUserModel(model);
        return "manage-roles-user";
    }

    // SAVE ROLE USER
    @PostMapping("save-role-user")
    public ModelAndView saveRoleUSer(@ModelAttribute RoleUser roleUser) {
        if(!roleUser.getRoleName().isEmpty()) {
            roleUser.setRoleName(roleUser.getRoleName().toLowerCase().trim());
            roleUserService.saveRoleUser(roleUser);
        }
        return new ModelAndView("redirect:/manage-roles-user");
    }

    // DELETE ROLE USER
    @GetMapping("/delete-role-user/{id}")
    public ModelAndView deleteRoleUser(@PathVariable("id") final int id, Model model) {
        roleUserService.deleteRoleUser(id);
        return new ModelAndView("redirect:/manage-roles-user");
    }

    // LIST GET ALL ROLE USER
    private void listRoleUserModel(Model model) {
        Iterable<RoleUser> listRolesUser = roleUserService.getAllRolesUser();
        model.addAttribute("rolesUser", listRolesUser);
    }
}
