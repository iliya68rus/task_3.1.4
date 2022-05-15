package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void run(ApplicationArguments args) {


        if (roleService.getRole("ROLE_ADMIN") == null) {
            roleService.addRole(new Role("ROLE_ADMIN"));
        }

        if (roleService.getRole("ROLE_USER") == null) {
            roleService.addRole(new Role("ROLE_USER"));
        }

        if (userService.getUserByUsername("admin@mail.ru") == null) {
            User ivan = new User("admin@mail.ru", "admin", "Ivan", "Pushkin", (byte)65);
            ivan.setRole(roleService.getRole("ROLE_ADMIN"));
            ivan.setRole(roleService.getRole("ROLE_USER"));
            userService.saveUser(ivan);
        }

        if (userService.getUserByUsername("user@mail.ru") == null) {
            User petr = new User("user@mail.ru", "user", "Petr","Ylanov", (byte)24);
            petr.setRole(roleService.getRole("ROLE_USER"));
            userService.saveUser(petr);
        }
    }
}
