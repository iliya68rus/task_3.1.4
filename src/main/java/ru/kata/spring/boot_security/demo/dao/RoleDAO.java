package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getAllRole();

    public Role getRole(String userRole);

    public Role getRole(long roleId);

    public void addRole(Role role);
}
