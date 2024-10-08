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
	
	@PutMapping("/updateBank/{userid}/{bankid}")
	public ResponseEntity<?> updateBank(
			@PathVariable("userid") int userId,
		    @PathVariable("bankid") int bankId,
	    @RequestBody Bank updatedBank
	) {
		
		System.out.println("User ID: " + userId);
	    System.out.println("Bank ID: " + bankId);
		
	    Optional<Users> user = userRepo.findById(userId);
	    if (!user.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }

	    Optional<Bank> bank = bankRepo.findById(bankId);
	    if (!bank.isPresent() || !user.get().getBank().contains(bank.get())) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank details not found for this user");
	    }

	    Bank existingBank = bank.get();
	    existingBank.setBank(updatedBank.getBank());
	    existingBank.setAccountno(updatedBank.getAccountno());
	    existingBank.setIfsccode(updatedBank.getIfsccode());
	    existingBank.setBranch(updatedBank.getBranch());
	    existingBank.setPF(updatedBank.getPF());
	    existingBank.setESI(updatedBank.getESI());

	    bankRepo.save(existingBank);
	    return ResponseEntity.status(HttpStatus.OK).body(existingBank);
	}

}
