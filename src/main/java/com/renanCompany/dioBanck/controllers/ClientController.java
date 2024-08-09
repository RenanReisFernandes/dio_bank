package com.renanCompany.dioBanck.controllers;

import java.util.List;
import java.util.Optional;

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

import com.renanCompany.dioBanck.entities.Client;
import com.renanCompany.dioBanck.services.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Client> save(@RequestBody Client client){
		Client createdClient = clientService.save(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> foundClient = clientService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(foundClient);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Client>> findById(@PathVariable Long id){
		Optional<Client> foundClient = clientService.findById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(foundClient);
	}
	
	@PutMapping
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){
		Client foundClient = clientService.update(id, client);
		return ResponseEntity.status(HttpStatus.OK).body(foundClient);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id){
		clientService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
