package ru.practicum.user;

import ru.practicum.User;

import java.util.List;

interface UserRepository {
    List<User> findAll();
    User save(User user);
}