package com.example.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.user.model.Users;
import com.example.user.model.Vaccant;
import com.example.user.repo.VaccantRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/job")
public class VaccantController {
	@Autowired
	private VaccantRepo vaccantRepo;
	
	@PostMapping("/set")
		public ResponseEntity<?> setjobs(@RequestBody Vaccant vaccant) {
			
			Vaccant vacc = vaccantRepo.save(vaccant);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(vacc);
		}
	
	@GetMapping("/get")
	public ResponseEntity<?> getjobs() {
		
		
		List<Vaccant> vacc = vaccantRepo.findAll();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(vacc);
	}

	   @GetMapping("/{jobid}")
	    public ResponseEntity<?> getJobById(@PathVariable int jobid) {
	        Optional<Vaccant> job = vaccantRepo.findById(jobid);
	        
	        if (job.isPresent()) {
	            return ResponseEntity.ok(job.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
	        }
	    }
	   
	   @GetMapping("/get/{page}/{count}")
	   public ResponseEntity<?> getjobs(@PathVariable("page") int page, @PathVariable("count") int count) {
	      List<Vaccant> vac = vaccantRepo.findAllPageable(PageRequest.of(page, count));
	      return ResponseEntity.status(HttpStatus.OK).body(vac);
	   }
	   
	   @GetMapping("/get/count")
		public ResponseEntity<?> getBooksCount() {
			Long count = vaccantRepo.getCount();
			return ResponseEntity.status(HttpStatus.OK).body(count);
		}
	   
	   @PostMapping("/search/count/{keyword}")
		public ResponseEntity<?> getSearchCategoryCount(@PathVariable final String keyword ) {
			Long count = vaccantRepo.getSearchCount(keyword);
			return ResponseEntity.status(HttpStatus.OK).body(count);
	}

	   @PostMapping("/search/{page}/{count}/{keyword}")
	   public ResponseEntity<?> getSearchCategoryName(@PathVariable("page") int page, @PathVariable("count") int count, @PathVariable final String keyword) {
	      List<Vaccant> vaccant = vaccantRepo.findJobName(keyword, PageRequest.of(page, count));
	      return ResponseEntity.status(HttpStatus.OK).body(vaccant);
	   }
	   
	   
}
