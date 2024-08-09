package com.renanCompany.dioBanck.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renanCompany.dioBanck.entities.Client;
import com.renanCompany.dioBanck.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		Optional<Client> optClient = clientRepository.findByCpf(client.getCpf());
		
		if(optClient.isPresent()) {
			if(!optClient.get().getId().equals(client.getId())) {
				throw new RuntimeException("Cliente já cadastrado!");
			}
		}
		return client;
	}
	
	public List<Client> findAll(){
		List<Client> foundLint = clientRepository.findAll();
		return foundLint;
	}
	
	public Optional<Client> findById(Long id){
		return clientRepository.findById(id);
	}

}
