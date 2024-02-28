package ru.practicum.shareit.user.repo;

import ru.practicum.shareit.user.model.User;

import java.util.List;

 public interface UserRepository {
     User createUser(User user);

     User updateUser(User user, Long userId);

     User getUserById(Long userId);

     List<User> getAllUsers();

     void deleteUserById(Long userId);
}
