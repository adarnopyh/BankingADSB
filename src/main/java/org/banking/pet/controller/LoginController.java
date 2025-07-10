package org.banking.pet.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        log.info("Login attempt with email: {}", email);

        // TODO: Add real authentication logic here
        if (email.equals("admin@example.com") && password.equals("admin")) {
            return ResponseEntity.ok("✅ Login successful for " + email);
        } else {
            return ResponseEntity.status(401).body("❌ Invalid credentials");
        }
    }
}
