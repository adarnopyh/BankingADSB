package org.banking.pet.repository;

import org.banking.pet.model.BankAccount;
import org.banking.pet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByUser(User user);
    void deleteByUserId(Long userId);

}
