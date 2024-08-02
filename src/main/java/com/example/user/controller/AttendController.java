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
import com.example.user.model.Users;
import com.example.user.repo.AttendRepo;
import com.example.user.repo.UsersRepo;


@RestController
@RequestMapping("/attend")
public class AttendController {

	@Autowired
	private AttendRepo attendRepo;
	
	
	@Autowired
	public UsersRepo userRepo;
	
	@PostMapping("/set/{userid}")
	public ResponseEntity<?> setuser(@RequestBody Attendance attendance, @PathVariable int userid) {
	
		Users users = userRepo.findById(userid).get();
		users.getAttendance().add(attendance);
		userRepo.save(users);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(attendance);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getUser() {
		
		
		List<Attendance> attend = attendRepo.findAll();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(attend);
	}
}
