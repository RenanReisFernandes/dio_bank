package com.renanCompany.dioBanck.controllers;

import com.renanCompany.dioBanck.entities.Account;
import com.renanCompany.dioBanck.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    private Account account;
    private List<Account> accountList;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(1L);
        account.setAccountNumber(123456L);

        accountList = Arrays.asList(account);
    }

    @Test
    void testSave() {
        when(accountService.save(account)).thenReturn(account);

        ResponseEntity<Account> response = accountController.save(account);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account, response.getBody());
        verify(accountService, times(1)).save(account);
    }

    @Test
    void testFindAll() {
        when(accountService.findAll()).thenReturn(accountList);

        ResponseEntity<List<Account>> response = accountController.findAll();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(accountList, response.getBody());
        verify(accountService, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        when(accountService.findById(1L)).thenReturn(Optional.of(account));

        ResponseEntity<Optional<Account>> response = accountController.findById(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
       assertTrue(response.getBody().isPresent());
        assertEquals(account, response.getBody().get());
        verify(accountService, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(accountService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Account>> response = accountController.findById(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(accountService, times(1)).findById(1L);
    }

    @Test
    void testFindByAccountNumber_Found() {
        when(accountService.findByAccountNumber(123456L)).thenReturn(Optional.of(account));

        ResponseEntity<Optional<Account>> response = accountController.findByAccountNumber(123456L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertTrue(response.getBody().isPresent());
        assertEquals(account, response.getBody().get());
        verify(accountService, times(1)).findByAccountNumber(123456L);
    }

    @Test
   void testFindByAccountNumber_NotFound() {
        when(accountService.findByAccountNumber(123456L)).thenReturn(Optional.empty());

        ResponseEntity<Optional<Account>> response = accountController.findByAccountNumber(123456L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
        verify(accountService, times(1)).findByAccountNumber(123456L);
    }

    @Test
    void testDelete() {
        doNothing().when(accountService).delete(1L);

        ResponseEntity<Void> response = accountController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(accountService, times(1)).delete(1L);
    }
}