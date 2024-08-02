package com.example.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.Vaccant;
import com.example.user.repo.VaccantRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/job")
public class VaccantController {
	@Autowired
	private VaccantRepo vaccantRepo;
	
	@PostMapping("/set")
		public ResponseEntity<?> setuser(@RequestBody Vaccant vaccant) {
			
			Vaccant vacc = vaccantRepo.save(vaccant);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(vacc);
		}

}
