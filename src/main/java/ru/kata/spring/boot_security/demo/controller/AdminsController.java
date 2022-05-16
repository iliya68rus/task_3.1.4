package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

//    @GetMapping("/users/new")
//    public String newUser(Principal principal, Model model) {
//        User admin = userService.getUserByUsername(principal.getName());
//
////        model.addAttribute("admin", admin);
//        model.addAttribute("adminEmail", admin.getEmail());
//        model.addAttribute("adminRoles", admin.getRoleName());
//        model.addAttribute("users", userService.getAllUser());
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.getAllRole());
//        return "users/hello";
//    }

    @GetMapping("/users")
    public String showUsers(Model model, Principal principal) {
        User admin = userService.getUserByUsername(principal.getName());
        model.addAttribute("adminEmail", admin.getEmail());
        model.addAttribute("adminRoles", admin.getRoleName());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "users/index";
    }

    @GetMapping("/")
    public String showAdminLikeUser(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "users/user-admin";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             Principal principal, Model model, @RequestParam(value = "role_id") long roleId) {

        User admin = userService.getUserByUsername(principal.getName());

        model.addAttribute("adminEmail", admin.getEmail());
        model.addAttribute("adminRoles", admin.getRoleName());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRole());

        if (userService.getUserByUsername(user.getEmail()) != null) {
            bindingResult.addError(new FieldError("email", "email",
                    String.format("User with email \"%s\" is already exist!", user.getEmail())));
            System.err.println("ОШИБКА ТАКОЙ ПОЛЬЗОВАТЕЛЬ УЖЕ ЕСТЬ");
            return "users/edit";
        }
        
        Role role = roleService.getRole(roleId);
//        Role role = roleService.getRole("ROLE_ADMIN");
        System.err.println(role);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(role);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String showUsersById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "role_id") long roleId) {
        Role role = roleService.getRole(roleId);
        System.err.println(role);
        user.setRole(role);
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

}
