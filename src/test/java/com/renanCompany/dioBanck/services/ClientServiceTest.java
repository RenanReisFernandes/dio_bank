package com.renanCompany.dioBanck.services;

import com.renanCompany.dioBanck.entities.Client;
import com.renanCompany.dioBanck.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setCpf("12345678901");
        client.setAddress("123 Street");
    }

    @Test
    void testSaveNewClient() {
        when(clientRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.save(client);

        assertNotNull(savedClient);
        assertEquals(client.getName(), savedClient.getName());
        verify(clientRepository, times(1)).findByCpf(anyString());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testSaveExistingClientThrowsException() {
        when(clientRepository.findByCpf(anyString())).thenReturn(Optional.of(client));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientService.save(client);
        });

        assertEquals("Cliente já cadastrado!", exception.getMessage());
        verify(clientRepository, times(1)).findByCpf(anyString());
        verify(clientRepository, times(0)).save(any(Client.class));
    }

    @Test
    void testFindAllClients() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        assertFalse(clientService.findAll().isEmpty());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdClientExists() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        Optional<Client> foundClient = clientService.findById(1L);

        assertTrue(foundClient.isPresent());
        assertEquals(client.getName(), foundClient.get().getName());
        verify(clientRepository, times(1)).findById(anyLong());
    }

    @Test
    void testFindByIdClientDoesNotExist() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Client> foundClient = clientService.findById(1L);

        assertFalse(foundClient.isPresent());
        verify(clientRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateClient() {
        Client updatedClient = new Client();
        updatedClient.setName("Jane Doe");
        updatedClient.setCpf("98765432100");
        updatedClient.setAddress("456 Avenue");

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        Client result = clientService.update(1L, updatedClient);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    void testUpdateClientNotFoundThrowsException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientService.update(1L, client);
        });

        assertEquals("Client not found!", exception.getMessage());
        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientRepository, times(0)).save(any(Client.class));
    }

    @Test
    void testDeleteClientExists() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));

        clientService.delete(1L);

        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeleteClientNotFoundThrowsException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clientService.delete(1L);
        });

        assertEquals("Client not found!", exception.getMessage());
        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientRepository, times(0)).deleteById(anyLong());
    }
}