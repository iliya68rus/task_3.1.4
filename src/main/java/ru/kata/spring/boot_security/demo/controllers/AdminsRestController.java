package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/api")
@RestController

public class AdminsRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminsRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        userService.editUser(user);
        return user;
    }

    @PostMapping("/users")
    public User newUser(@RequestBody @Valid User user) {
        if (user.getRoles().size() == 0) {
            return user;
        }
        if (userService.getUserByUsername(user.getEmail()) == User.NOBODY) {
            userService.saveUser(user);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
