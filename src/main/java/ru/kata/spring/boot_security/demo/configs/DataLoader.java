package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    public void run(ApplicationArguments args) {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        Role user2 = new Role("ROLE_USER");

        List<Role> adminList = new ArrayList<>();
        adminList.add(admin);
        adminList.add(user);

        List<Role> userList = new ArrayList<>();
        userList.add(user2);

        User ivan = new User("admin@mail.ru", "admin", "Ivan", "Pushkin", (byte)65, adminList);
        User petr = new User("user@mail.ru", "user", "Petr","Ylanov", (byte)24, userList);

//        userService.saveUser(ivan);
//        userService.saveUser(petr);
    }
}
