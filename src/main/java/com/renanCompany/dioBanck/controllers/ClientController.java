package com.renanCompany.dioBanck.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanCompany.dioBanck.DTO.request.ClientRequest;
import com.renanCompany.dioBanck.DTO.response.ClientResponse;
import com.renanCompany.dioBanck.entities.Client;
import com.renanCompany.dioBanck.mappers.ClientMapper;
import com.renanCompany.dioBanck.services.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private final ClientService clientService;
	private final ClientMapper mapper;

	public ClientController(ClientMapper mapper, ClientService clientService) {
        this.mapper = mapper;
        this.clientService = clientService;
    }
	
	@PostMapping
	public ResponseEntity<ClientResponse> save(@RequestBody ClientRequest clientRequest) {
	    Client client = mapper.toClient(clientRequest);
	    Client clientSaved = clientService.save(client);
	    ClientResponse response = mapper.toClientResponse(clientSaved);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<Set<ClientResponse>> findAll() {
	    Set<Client> clients = new HashSet<>(clientService.findAll());
	    
	    Set<ClientResponse> responses = clients.stream()
	                                           .map(mapper::toClientResponse)
	                                           .collect(Collectors.toSet());
	    
	    return ResponseEntity.status(HttpStatus.OK).body(responses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
	    Optional<Client> foundClient = clientService.findById(id);

	    if (foundClient.isPresent()) {
	        ClientResponse response = mapper.toClientResponse(foundClient.get());
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	@PutMapping
	public ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody Client client){
		Client foundClient = clientService.update(id, client);
		ClientResponse response = mapper.toClientResponse(foundClient);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id){
		clientService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
