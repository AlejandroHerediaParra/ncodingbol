package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dataTransferObeject.LoginDto;
import com.example.demo.dataTransferObeject.SingUpDto;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( 
					loginDto.getEmail(), loginDto.getPassword()));			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			User user = userRepository.findByEmail(loginDto.getEmail()).get();
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SingUpDto singUpDto) {
		
		//verify if user exists into DB
		if(userRepository.existsByEmail(singUpDto.getEmail())) {
			return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
		}
		
		// create user
		User user = new User();
		user.setName(singUpDto.getName());
		user.setEmail(singUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(singUpDto.getPassword()));

		userRepository.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
