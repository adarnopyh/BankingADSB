package org.banking.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.banking.pet.model.User;
import org.banking.pet.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        List<User> users = userService.findAll();
        log.debug("Users retrieved: {}", users);
        return users;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        log.info("Creating user: {}", user);
        return userService.create(user);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        log.info("Deleting user by Id: {}", id);
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(
            @PathVariable(name = "id") Long id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name
    ) {
        userService.update(id, email, name);
    }
}