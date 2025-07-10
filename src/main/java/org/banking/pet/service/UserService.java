package org.banking.pet.service;

import jakarta.transaction.Transactional;
import org.banking.pet.model.BankAccount;
import org.banking.pet.model.User;
import org.banking.pet.repository.BankAccountRepository;
import org.banking.pet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    public UserService(UserRepository userRepository, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User create(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("User with this email already exists");
        }
        User savedUser = userRepository.save(user);

        BankAccount account = new BankAccount();
        account.setUser(savedUser);
        account.setBalance(BigDecimal.ZERO);
        bankAccountRepository.save(account);

        return savedUser;
    }

    @Transactional
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with this id does not exist");
        }

        bankAccountRepository.deleteByUserId(id);

        userRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String email, String name) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with this id does not exist");
        }
        User user = optionalUser.get();

        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> foundByEmail = userRepository.findByEmail(email);
            if (foundByEmail.isPresent()) {
                throw new IllegalStateException("User with this email already exists");
            }
            user.setEmail(email);
        }

        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }
//        userRepository.save(user);
    }
}
