package com.renanCompany.dioBanck.controllers;

import com.renanCompany.dioBanck.DTO.request.ClientRequest;
import com.renanCompany.dioBanck.DTO.response.ClientResponse;
import com.renanCompany.dioBanck.entities.Client;
import com.renanCompany.dioBanck.mappers.ClientMapper;
import com.renanCompany.dioBanck.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientController clientController;

    private Client client;
    private ClientRequest clientRequest;
    private ClientResponse clientResponse;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("Test Client");

        clientRequest = new ClientRequest();
        clientRequest.setName("Test Client");

        clientResponse = new ClientResponse();
        clientResponse.setName("Test Client");
    }

    @Test
    void testSave() {
        when(clientMapper.toClient(clientRequest)).thenReturn(client);
        when(clientService.save(client)).thenReturn(client);
        when(clientMapper.toClientResponse(client)).thenReturn(clientResponse);

        ResponseEntity<ClientResponse> response = clientController.save(clientRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clientResponse, response.getBody());
        verify(clientMapper, times(1)).toClient(clientRequest);
        verify(clientService, times(1)).save(client);
        verify(clientMapper, times(1)).toClientResponse(client);
    }

    @Test
    void testFindAll() {
        Set<Client> clients = new HashSet<>();
        clients.add(client);

        Set<ClientResponse> responses = clients.stream()
                .map(clientMapper::toClientResponse)
                .collect(Collectors.toSet());

        when(clientService.findAll()).thenReturn((List<Client>) clients);
        when(clientMapper.toClientResponse(client)).thenReturn(clientResponse);

        ResponseEntity<Set<ClientResponse>> response = clientController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
        verify(clientService, times(1)).findAll();
        verify(clientMapper, times(1)).toClientResponse(client);
    }

    @Test
    void testFindById_Found() {
        when(clientService.findById(1L)).thenReturn(Optional.of(client));
        when(clientMapper.toClientResponse(client)).thenReturn(clientResponse);

        ResponseEntity<ClientResponse> response = clientController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientResponse, response.getBody());
        verify(clientService, times(1)).findById(1L);
        verify(clientMapper, times(1)).toClientResponse(client);
    }

    @Test
    void testFindById_NotFound() {
        when(clientService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ClientResponse> response = clientController.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clientService, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        when(clientService.update(1L, client)).thenReturn(client);
        when(clientMapper.toClientResponse(client)).thenReturn(clientResponse);

        ResponseEntity<ClientResponse> response = clientController.update(1L, client);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientResponse, response.getBody());
        verify(clientService, times(1)).update(1L, client);
        verify(clientMapper, times(1)).toClientResponse(client);
    }

    @Test
    void testDelete() {
        doNothing().when(clientService).delete(1L);

        ResponseEntity<Void> response = clientController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(clientService, times(1)).delete(1L);
    }
}