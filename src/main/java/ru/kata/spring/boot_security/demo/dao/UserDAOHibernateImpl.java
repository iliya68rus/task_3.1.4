package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDAOHibernateImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    @Transactional
    public User getUserByUsername(String email) {
        try {
            System.out.println("TEST: " + email);
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :login", User.class);
            User user = query.setParameter("login", email)
                    .getSingleResult();
            System.out.println("Answer: " + user.getName());
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
