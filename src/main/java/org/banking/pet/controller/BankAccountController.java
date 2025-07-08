package org.banking.pet.controller;

import org.banking.pet.dto.BankOperationRequest;
import org.banking.pet.dto.TransferRequest;
import org.banking.pet.model.BankAccount;
import org.banking.pet.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{userId}")
    public BankAccount getAccount(@PathVariable Long userId) {
        return bankAccountService.getAccountForUser(userId);
    }

    @PostMapping("/{userId}/deposit")
    public void deposit(@PathVariable Long userId, @RequestBody BankOperationRequest request) {
        bankAccountService.deposit(userId, request.getAmount());
    }

    @PostMapping("/{userId}/withdraw")
    public void withdraw(@PathVariable Long userId, @RequestBody BankOperationRequest request) {
        bankAccountService.withdraw(userId, request.getAmount());
    }

    @PostMapping("/{userId}/transfer")
    public void transfer(@PathVariable Long userId, @RequestBody TransferRequest request) {
        bankAccountService.transfer(userId, request);
    }
}
