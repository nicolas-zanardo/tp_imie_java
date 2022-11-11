package fr.imie.webapp.controller;

import fr.imie.webapp.model.Role;
import fr.imie.webapp.service.RoleService;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

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
    public ModelAndView saveRole(@ModelAttribute Role role) {
        if(!role.getName().isEmpty()) {
            role.setName(role.getName().toLowerCase().trim());
            roleService.saveRole(role);
        }
        return new ModelAndView("redirect:/manage-role");
    }

    // DELETE ROLE USER
    @GetMapping("/delete-role-user/{id}")
    public ModelAndView deleteRole(@PathVariable("id") final int id, Model model) {
        roleService.deleteRole(id);
        return new ModelAndView("redirect:/manage-role");
    }

    // LIST GET ALL ROLE USER
    private void listRoleUserModel(Model model) {
        Iterable<Role> listRole = roleService.getAllRoles();
        model.addAttribute("listRole", listRole);
    }
}
