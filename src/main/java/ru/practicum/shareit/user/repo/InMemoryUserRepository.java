package ru.practicum.shareit.user.repo;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.TemporaryIdGenerator;
import ru.practicum.shareit.errors.exceptions.NotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final List<User> inMemoryUserList = new ArrayList<>();

    @Override
    public User createUser(User user) {
        user.setId(TemporaryIdGenerator.incrementUserId());
        inMemoryUserList.add(user);
        return user;
    }

    @Override
    public User updateUser(User updatedUser, Long userId) {
        var existingUser = getUserById(userId);

        updateUserFields(existingUser, updatedUser);

        return existingUser;
    }

    private void updateUserFields(User existingUser, User updatedUser) {
        String name = (updatedUser.getName() == null) ? existingUser.getName() : updatedUser.getName();
        String email = (updatedUser.getEmail() == null) ? existingUser.getEmail() : updatedUser.getEmail();
        existingUser.setName(name);
        existingUser.setEmail(email);
    }


    @Override
    public User getUserById(Long userId) {
        return inMemoryUserList.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException("User with ID " + userId + " not found."));
    }

    @Override
    public List<User> getAllUsers() {
        return inMemoryUserList;
    }

    @Override
    public void deleteUserById(Long userId) {
        inMemoryUserList.remove(getUserById(userId));
    }

}
