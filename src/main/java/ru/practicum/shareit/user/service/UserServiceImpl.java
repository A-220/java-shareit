package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.errors.exceptions.DuplicateEmailException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repo.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (isDuplicateEmail(user)) {
            throw new DuplicateEmailException("User with this email already exist");
        }
        var u = userRepository.createUser(user);
        log.info("User with id {} successfully created", u.getId());
        return u;
    }

    @Override
    public User updateUser(User user, Long userId) {
        if (isDuplicateEmail(user, userId)) {
            throw new DuplicateEmailException("User with this email already exist");
        }
        var u = userRepository.updateUser(user, userId);
        log.info("User with id {} successfully updated", u.getId());
        return u;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteUserById(userId);
        log.info("User with id {} successfully deleted", userId);
    }

    private boolean isDuplicateEmail(User user) {
        return getAllUsers().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    private boolean isDuplicateEmail(User user, Long id) {
        return getAllUsers().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()) &&
                        !u.getId().equals(id));
    }
}
