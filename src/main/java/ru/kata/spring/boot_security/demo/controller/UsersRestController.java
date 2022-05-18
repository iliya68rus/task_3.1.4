package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("/test")
//    public String test() {
//        String s = "TEST";
//        return s;
//    }

    @GetMapping("/users")
    public List<User> getUsers() {
        System.err.println("USERS ПРОВЕРКА");
        List<User> userList = userService.getAllUser();
        return userList;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    private User editUser(@RequestBody User user) {
        userService.editUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    private String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "User with ID=" + id + " was deleted";
    }

}
