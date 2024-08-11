package com.renanCompany.dioBanck.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;

import com.renanCompany.dioBanck.entities.Account;
import com.renanCompany.dioBanck.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Account account = new Account();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account result = accountService.save(account);

        assertNotNull(result);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testFindAll_Success() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> result = accountService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_NoAccountFound() {
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(NoSuchElementException.class, () -> {
            accountService.findAll();
        });
    }

    @Test
    void testFindById_Success() {
        Account account = new Account();
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.findById(1L);

        assertTrue(result.isPresent());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NoAccountFound() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            accountService.findById(1L);
        });
    }

    @Test
    void testFindByAccountNumber_Success() {
        Account account = new Account();
        when(accountRepository.findByaccountNumber(anyLong())).thenReturn(Optional.of(account));

        Optional<Account> result = accountService.findByAccountNumber(12345L);

        assertTrue(result.isPresent());
        verify(accountRepository, times(1)).findByaccountNumber(12345L);
    }

    @Test
    void testFindByAccountNumber_NoAccountFound() {
        when(accountRepository.findByaccountNumber(anyLong())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            accountService.findByAccountNumber(12345L);
        });
    }

    @Test
    void testDelete() {
        doNothing().when(accountRepository).deleteById(anyLong());

        accountService.delete(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }
}