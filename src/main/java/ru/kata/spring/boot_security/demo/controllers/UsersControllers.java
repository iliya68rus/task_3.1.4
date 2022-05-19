package ru.kata.spring.boot_security.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class UsersControllers {

    private UserServiceImpl userService;

    @Autowired
    public UsersControllers(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        User user = userService.getUserByLogin(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "/user";
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("logout", logout != null);
        return "login";

    }
}
