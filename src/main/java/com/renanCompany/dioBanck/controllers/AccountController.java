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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanCompany.dioBanck.entities.Account;
import com.renanCompany.dioBanck.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/account")
@Tag(name = "API DIO BANK / ACCOUNT")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Operation(summary = "criation of account", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "insertions succssed"),
			@ApiResponse(responseCode = "422", description = "Invalid requirement"),
			@ApiResponse(responseCode = "400", description = "Invalid params"),
			@ApiResponse(responseCode = "401", description = "user not found"),
			@ApiResponse(responseCode = "500", description = "server broke")
			
	})	
	@PostMapping
	public ResponseEntity<Account> save(@RequestBody Account account){
		Account accountSaved = accountService.save(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(accountSaved);
	}
	
	@Operation(summary = "find all clients", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "insertions succssed"),
			@ApiResponse(responseCode = "422", description = "Invalid requirement"),
			@ApiResponse(responseCode = "400", description = "Invalid params"),
			@ApiResponse(responseCode = "401", description = "user not found"),
			@ApiResponse(responseCode = "500", description = "server broke")
			
	})	
	@GetMapping
	public ResponseEntity<List<Account>> findAll(){
		List<Account> list = accountService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}
	
	@Operation(summary = "find clients by id", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "insertions succssed"),
			@ApiResponse(responseCode = "422", description = "Invalid requirement"),
			@ApiResponse(responseCode = "400", description = "Invalid params"),
			@ApiResponse(responseCode = "401", description = "user not found"),
			@ApiResponse(responseCode = "500", description = "server broke")
			
	})	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Account>> findById(@PathVariable Long id){
		Optional<Account> optAccount = accountService.findById(id);
		if(optAccount.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(optAccount);
	}
	
	@Operation(summary = "find clients by account number", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "insertions succssed"),
			@ApiResponse(responseCode = "422", description = "Invalid requirement"),
			@ApiResponse(responseCode = "400", description = "Invalid params"),
			@ApiResponse(responseCode = "401", description = "user not found"),
			@ApiResponse(responseCode = "500", description = "server broke")
			
	})	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Optional<Account>> findByAccountNumber(@PathVariable Long accountNumber){
		Optional<Account> optAccount = accountService.findByAccountNumber(accountNumber);
		if(optAccount.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(optAccount);
	}
	
	@Operation(summary = "delete clients by id", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "insertions succssed"),
			@ApiResponse(responseCode = "422", description = "Invalid requirement"),
			@ApiResponse(responseCode = "400", description = "Invalid params"),
			@ApiResponse(responseCode = "401", description = "user not found"),
			@ApiResponse(responseCode = "500", description = "server broke")
			
	})	
	@DeleteMapping("/id")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		accountService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
