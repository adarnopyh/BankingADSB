package org.banking.pet.service;

import org.banking.pet.model.BankAccount;
import org.banking.pet.model.User;
import org.banking.pet.repository.BankAccountRepository;
import org.banking.pet.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Janis Biezais");
        testUser.setEmail("janis@example.com");
        testUser.setBirthday(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testFindAllUsers() {
        List<User> users = List.of(testUser);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userService.create(testUser);

        assertEquals(testUser.getEmail(), result.getEmail());
        verify(bankAccountRepository).save(any(BankAccount.class));
    }

    @Test
    void testDeleteUser_NotFoundThrows() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalStateException.class, () -> userService.delete(1L));
        assertEquals("User with this id does not exist", ex.getMessage());
    }

    @Test
    void testUpdateUser_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());

        userService.update(1L, "new@example.com", "NewPerson");

        assertEquals("new@example.com", testUser.getEmail());
        assertEquals("NewPerson", testUser.getName());
    }

    @Test
    void testUpdateUser_NotFoundThrows() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalStateException.class, () -> userService.update(1L, "x", "y"));
        assertEquals("User with this id does not exist", ex.getMessage());
    }

    @Test
    void testUpdateUser_EmailAlreadyExistsThrows() {
        User anotherUser = new User();
        anotherUser.setEmail("taken@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.findByEmail("taken@example.com")).thenReturn(Optional.of(anotherUser));

        Exception ex = assertThrows(IllegalStateException.class, () -> userService.update(1L, "taken@example.com", null));
        assertEquals("User with this email already exists", ex.getMessage());
    }

}