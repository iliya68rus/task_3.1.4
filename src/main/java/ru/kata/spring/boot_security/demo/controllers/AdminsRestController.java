package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
@RequestMapping("/api")
@RestController

public class AdminsRestController {

    private final UserService userService;

    @Autowired
    public AdminsRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        userService.update(user.getId(), user);
        return user;
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.delete(id);
    }
}
