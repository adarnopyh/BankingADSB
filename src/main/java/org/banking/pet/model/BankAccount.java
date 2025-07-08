package org.banking.pet.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        if (balance.compareTo(amount) < 0)
            throw new IllegalArgumentException("Insufficient funds");
        balance = balance.subtract(amount);
    }
}
