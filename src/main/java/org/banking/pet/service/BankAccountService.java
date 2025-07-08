package org.banking.pet.service;

import jakarta.transaction.Transactional;
import org.banking.pet.dto.TransferRequest;
import org.banking.pet.exception.ApiException;
import org.banking.pet.exception.ErrorCode;
import org.banking.pet.model.BankAccount;
import org.banking.pet.model.User;
import org.banking.pet.repository.BankAccountRepository;
import org.banking.pet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    public BankAccount getAccountForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("User not found"));
        return bankAccountRepository.findByUser(user).orElseThrow(
                () -> new IllegalStateException("Bank account not found for user"));
    }

    @Transactional
    public void deposit(Long userId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException(ErrorCode.DEPOSIT_INVALID);
        }

        BankAccount account = getAccountForUser(userId);
        account.deposit(amount);
        bankAccountRepository.save(account);
    }

    @Transactional
    public void withdraw(Long userId, BigDecimal amount) {
        BankAccount account = getAccountForUser(userId);
        account.withdraw(amount);
        bankAccountRepository.save(account);
    }

    @Transactional
    public void transfer(Long sourceUserId, TransferRequest request) {
        BankAccount source = getAccountForUser(sourceUserId);
        BankAccount target = getAccountForUser(request.getTargetUserId());
        source.withdraw(request.getAmount());
        target.deposit(request.getAmount());
        bankAccountRepository.save(source);
        bankAccountRepository.save(target);
    }
}