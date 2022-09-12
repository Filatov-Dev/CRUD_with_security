package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<User> showAllUsers() {
        return entityManager.createQuery("select users from User users", User.class).getResultList();
    }

    @Override
    public User showUserInfo(int id) {return entityManager.find(User.class,id);}

    @Override
    public void saveUser(User user) {entityManager.merge(user);}

    @Override
    public void deleteUser(int id) {;
        entityManager.createQuery("delete from User user where user.id =: userId")
                .setParameter("userId", id)
                .executeUpdate();
    }

    @Override
    public void updateUserInfo(int id, User user) {
        User updatedUser = entityManager.find(User.class, id);
        updatedUser.setUsername(user.getUsername());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(updatedUser.getAge());

    }

    @Override
    public User findByName(String name) {
        return entityManager.createQuery("select user from User user where user.username =: name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
