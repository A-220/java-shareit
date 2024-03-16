package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.errors.ErrorsHandler;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    private final ErrorsHandler errorsHandler;

    @PostMapping
    public UserDto createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        errorsHandler.throwValidationExceptionIfErrorsExist(bindingResult);
        return UserDto.toDto(userService.createUser(user));
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody User user,
                              @PathVariable Long userId) {
        return UserDto.toDto(userService.updateUser(user, userId));
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return UserDto.toDto(userService.getUserById(userId));
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
