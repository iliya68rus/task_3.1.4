package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    public void run(ApplicationArguments args) {

        if (roleService.getRole("ROLE_USER") == Role.NOBODY) {
            roleService.addRole(new Role("ROLE_USER"));
        }

        if (roleService.getRole("ROLE_ADMIN") == Role.NOBODY) {
            roleService.addRole(new Role("ROLE_ADMIN"));
        }

        if (userService.getUserByUsername("admin@mail.ru") == User.NOBODY) {
            User ivan = new User("admin@mail.ru", encoder.encode("admin")
                    , "Ivan", "Pushkin", (byte)65);
            ivan.setRole(roleService.getRole("ROLE_ADMIN"));
            ivan.setRole(roleService.getRole("ROLE_USER"));
            userService.saveUser(ivan);
        }

        if (userService.getUserByUsername("user@mail.ru") == User.NOBODY) {
            User petr = new User("user@mail.ru", encoder.encode("user")
                    , "Petr","Ylanov", (byte)24);
            petr.setRole(roleService.getRole("ROLE_USER"));
            userService.saveUser(petr);
        }
    }
}
