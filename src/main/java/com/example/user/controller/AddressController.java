package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.Address;
import com.example.user.model.Users;
import com.example.user.repo.AddressRepo;

import com.example.user.repo.UsersRepo;


@RestController
@RequestMapping("/address")
public class AddressController {

		
		@Autowired
		public AddressRepo addressRepo;
		
		@Autowired
		public UsersRepo userRepo;
		
		@PostMapping("/set/{userid}")
		public ResponseEntity<?> setuser(@RequestBody Address address , @PathVariable int userid) {
		
			
			Users users = userRepo.findById(userid).get();
			users.getAddress().add(address);
			userRepo.save(users);

			return ResponseEntity.status(HttpStatus.OK)
					.body(address);
		}
		
		@GetMapping("/get")
		public ResponseEntity<?> getUser() {
			
			
			List<Address> address = addressRepo.findAll();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(address);
		}

	}
	

