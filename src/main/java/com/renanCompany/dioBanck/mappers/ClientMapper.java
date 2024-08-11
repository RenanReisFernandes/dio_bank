package com.renanCompany.dioBanck.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.renanCompany.dioBanck.DTO.request.ClientRequest;
import com.renanCompany.dioBanck.DTO.response.ClientResponse;
import com.renanCompany.dioBanck.entities.Client;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientMapper {
	
	private final ModelMapper mapper;
	
	public Client toClient(ClientRequest clientRequest) {
		return null;
	}
	
	public ClientResponse toClientResponse(Client client) {
		return null;
	}
	
	public List<ClientResponse> toClientResponseList(List<Client> clientList){
		return null;
	}

}
