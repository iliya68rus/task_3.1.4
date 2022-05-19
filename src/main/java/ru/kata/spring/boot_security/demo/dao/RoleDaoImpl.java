package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }
    @Override
    public Set<Role> getRoleById(Integer[] role_id) {
        Set<Role> roleResult = new HashSet<>();
        if (role_id == null) {
           roleResult.add(entityManager.find(Role.class, 1));
        } else {
            for (int id : role_id) {
                TypedQuery<Role> q = entityManager.createQuery("select role from Role role where role.id = :id", Role.class);
                q.setParameter("id", id);
                Role result = q.getResultList().stream().filter(role -> role.getId() == id).findAny().orElse(null);
                roleResult.add(result);
            }
        }
        return roleResult;
    }
}
