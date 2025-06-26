package ru.practicum.user;

import ru.practicum.User;

import java.util.List;

interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
}