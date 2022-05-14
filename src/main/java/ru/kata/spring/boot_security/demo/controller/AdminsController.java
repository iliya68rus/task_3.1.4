package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

//    @GetMapping("/test")
//    public String test(Model model) {
//        model.addAttribute("roles", roleService.getAllRole());
////        List<Role> roles = new ArrayList(List.of("ADMIN"));
////        User user = new User(1L,"user", "user", "1234", "ILIYA", "KOROSTELEV", (byte)12, roles);
////        userService.saveUser(user);
//        return "users/test";
//    }

//    @GetMapping("/hello")
//    public String helloUsers(Model model, Principal principal) {
//        User user = userService.getUserByUsername(principal.getName());
//        model.addAttribute("user", user);
//        model.addAttribute("users", userService.getAllUser());
//        model.addAttribute("newUser", new User());
//        model.addAttribute("roles", new Role());
//        return "users/hello";
//    }

    @GetMapping("/users")
    public String showUsers(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "users/index";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
//        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String showUsersById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.editUser(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

//    private void getUserRoles(User user) {
//        user.setRoles(user.getRoles().stream()
//                .map(role -> roleService.getRole(role.getName()))
//                .collect(Collectors.toList()));
//    }

}
