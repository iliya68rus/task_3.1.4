package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class UserDAOHibernateImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public void editUser(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserByUsername(String username) {
        System.out.println("TEST: " + username);
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.login = :login", User.class);
        User user = query.setParameter("login", username)
                .getSingleResult();
        System.out.println("Answer: " + user.getName());

        return user;
    }

}
