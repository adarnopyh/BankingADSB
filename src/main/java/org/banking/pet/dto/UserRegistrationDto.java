package org.banking.pet.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRegistrationDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be ≥ 8 characters")
    private String password;

    @NotNull
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

}
