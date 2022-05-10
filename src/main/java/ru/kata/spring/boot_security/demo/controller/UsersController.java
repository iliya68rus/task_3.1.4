package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;


@Controller
public class UsersController {

    @Autowired
    private UserDAO userdao;

    @GetMapping("/")
    public String start() {
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String showUsers(Model model) {
        model.addAttribute("users", userdao.getAllUser());
        return "users/index";
    }

}
