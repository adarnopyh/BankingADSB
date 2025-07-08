package org.banking.pet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate birthday;
    private Integer age;

}