package com.renanCompany.dioBanck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
