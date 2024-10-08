package com.example.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		
		@PutMapping("/updateAddress/{userid}/{addressid}")
		public ResponseEntity<?> updateAddress(
				 @PathVariable("userid") int userId,
				    @PathVariable("addressid") int addressId,
		    @RequestBody Address updatedAddress
		) {
			
			System.out.println("User ID: " + userId);
		    System.out.println("Bank ID: " + addressId);
			
		    Optional<Users> user = userRepo.findById(userId);
		    if (!user.isPresent()) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		    }

		    Optional<Address> address = addressRepo.findById(addressId);
		    if (!address.isPresent() || !user.get().getAddress().contains(address.get())) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found for this user");
		    }

		    Address existingAddress = address.get();
		    existingAddress.setAddress(updatedAddress.getAddress());
		    existingAddress.setCity(updatedAddress.getCity());
		    existingAddress.setState(updatedAddress.getState());
		    existingAddress.setLocation(updatedAddress.getLocation());
		    existingAddress.setPincode(updatedAddress.getPincode());

		    addressRepo.save(existingAddress);
		    return ResponseEntity.status(HttpStatus.OK).body(existingAddress);
		}


	}
	

