package org.banking.pet.controller;

import jakarta.validation.Valid;
import org.banking.pet.dto.UserRegistrationDto;
import org.banking.pet.model.Role;
import org.banking.pet.model.User;
import org.banking.pet.repository.RoleRepository;
import org.banking.pet.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class RegistrationRestController {

    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder encoder;

    public RegistrationRestController(UserRepository users,
                                      RoleRepository roles,
                                      PasswordEncoder encoder) {
        this.users = users;
        this.roles = roles;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegistrationDto dto) {
        if (users.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setBirthday(dto.getBirthday());

        Role userRole = roles.findByName("USER")
                .orElseGet(() -> roles.save(new Role(null, "USER")));
        user.setRoles(Set.of(userRole));

        users.save(user);
        return ResponseEntity.ok("Registered");
    }
}
