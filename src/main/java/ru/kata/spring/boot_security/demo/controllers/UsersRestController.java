package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@RequestMapping("/one")
@RestController
public class UsersRestController {
    private final UserService userService;

    @Autowired
    public UsersRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public User user(Principal principal) {
        return userService.getUserByLogin(principal.getName());
    }
}
