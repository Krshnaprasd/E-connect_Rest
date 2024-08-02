package com.example.user.controller;

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

import com.example.user.model.Attendance;
import com.example.user.model.Bank;
import com.example.user.model.Users;
import com.example.user.repo.BankRepo;
import com.example.user.repo.UsersRepo;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	public BankRepo bankRepo;
	
	@Autowired
	public UsersRepo userRepo;
	
	@PostMapping("/set/{userid}")
	public ResponseEntity<?> setuser(@RequestBody Bank bank , @PathVariable int userid) {
	
//		Bank bankdetail = bankRepo.save(bank);
		
		Users users = userRepo.findById(userid).get();
		users.getBank().add(bank);
		
		userRepo.save(users);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(bank);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getUser() {
		
		
		List<Bank> bank = bankRepo.findAll();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(bank);
	}

}
