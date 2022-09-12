package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public List<User> showAllUsers();
    public User showUserInfo(int id);
    public void saveUser(User user);
    public void deleteUser(int id);
    public void updateUserInfo(int id, User user);
    public User findByName(String name);
}
