package com.renanCompany.dioBanck.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanCompany.dioBanck.entities.Account;
import com.renanCompany.dioBanck.services.AccountService;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	public ResponseEntity<Account> save(@RequestBody Account account){
		Account accountSaved = accountService.save(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(accountSaved);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> findAll(){
		List<Account> list = accountService.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Account>> findById(@PathVariable Long id){
		Optional<Account> optAccount = accountService.findById(id);
		if(optAccount.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(optAccount);
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Optional<Account>> findByAccountNumber(@PathVariable Long accountNumber){
		Optional<Account> optAccount = accountService.findByAccountNumber(accountNumber);
		if(optAccount.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(optAccount);
	}

}
