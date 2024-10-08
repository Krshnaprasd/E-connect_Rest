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

import com.example.user.model.Attendance;
import com.example.user.model.Salary;
import com.example.user.model.Users;
import com.example.user.repo.SalaryRepo;
import com.example.user.repo.UsersRepo;

@RestController
@RequestMapping("/salary")
public class SalaryController {
	
	@Autowired
	private SalaryRepo salaryRepo;
	
	@Autowired
	public UsersRepo userRepo;
	
	@PostMapping("/set/{userid}")
	public ResponseEntity<?> setuser(@RequestBody Salary salary, @PathVariable int userid) {
	
		Users users = userRepo.findById(userid).get();
		users.getSalary().add(salary);
		userRepo.save(users);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(salary);
	}
	
	
	@GetMapping("/get/{userid}")
	public ResponseEntity<?> getUserSalary(@PathVariable int userid) {
	    Users user = userRepo.findById(userid).orElse(null);
	    
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("User not found");
	    }

	    List<Salary> salaryList = user.getSalary(); 
	    
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(salaryList);
	}

}
