package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private UserDAO userdao;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userdao.getAllUser());
        return "users/index";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userdao.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String showUsersById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userdao.getUserById(id));
        return "users/show";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userdao.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userdao.editUser(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userdao.deleteUser(id);
        return "redirect:/users";
    }

}
