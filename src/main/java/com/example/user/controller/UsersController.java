

package com.example.user.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.Address;
import com.example.user.model.Bank;
//import com.example.user.model.Address;
//import com.example.user.model.Bank;
import com.example.user.model.Users;
import com.example.user.repo.AddressRepo;
import com.example.user.repo.BankRepo;
////import com.example.user.repo.AddressRepo;
//import com.example.user.repo.BankRepo;
import com.example.user.repo.UsersRepo;


@RestController
@RequestMapping("/user")
public class UsersController {
	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private BankRepo bankRepo;
	
	@PostMapping("/set")
	public ResponseEntity<?> setuser(@RequestBody Users users) {
	
		Users use = usersRepo.save(users);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(users);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getUser() {
		
		
		List<Users> use = usersRepo.findAll();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(use);
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> validateUser(@RequestBody Users user) {
		
		
		Users use = usersRepo.findUser(user.getName(), user.getPassword());
		
		if(use!=null) {
					return ResponseEntity.status(HttpStatus.OK)
						.body(use);
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Data not found");
				}
				
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateStudent(@RequestBody Users users, @PathVariable int id ){
		Users user = usersRepo.findById(id).get();
		
		usersRepo.save(users);
 		return ResponseEntity.status(HttpStatus.OK)
		.body(user);
}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		
		Users use = usersRepo.findById(id).get();
		
		usersRepo.delete(use);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(use);
	}
}
