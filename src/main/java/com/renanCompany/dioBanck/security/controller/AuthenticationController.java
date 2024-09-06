package com.renanCompany.dioBanck.security.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renanCompany.dioBanck.security.dto.AuthenticationDto;
import com.renanCompany.dioBanck.security.dto.RegisterDto;
import com.renanCompany.dioBanck.security.entities.User;
import com.renanCompany.dioBanck.security.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto data) {
	    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		return ResponseEntity.ok().body("Login successful");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDto data) {

	    if (this.userRepository.findByLogin(data.login()) != null) {
	        return ResponseEntity.badRequest().body("User already exists");
	    }
	    
	    String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
	    User newUser = new User(data.login(), encriptedPassword, data.role());
	    this.userRepository.save(newUser);
	    
	    return ResponseEntity.ok().build();
	}
}
