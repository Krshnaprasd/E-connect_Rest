

package com.example.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.user.model.Vaccant;
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
	
	 @GetMapping("/get/{userid}")
	    public ResponseEntity<Users> getUserById(@PathVariable int userid) {
	        Users user = usersRepo.findByUserid(userid);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	@GetMapping("/get/{page}/{count}")
	   public ResponseEntity<?> getjobs(@PathVariable("page") int page, @PathVariable("count") int count) {
	      List<Users> use = usersRepo.findAllPageable(PageRequest.of(page, count));
	      return ResponseEntity.status(HttpStatus.OK).body(use);
	   }
	   
	   @GetMapping("/get/count")
		public ResponseEntity<?> getBooksCount() {
			Long count = usersRepo.getCount();
			return ResponseEntity.status(HttpStatus.OK).body(count);
		}
	   
	   @PostMapping("/search/count/{keyword}")
		public ResponseEntity<?> getSearchCategoryCount(@PathVariable final String keyword ) {
			Long count = usersRepo.getSearchCount(keyword);
			return ResponseEntity.status(HttpStatus.OK).body(count);
	   }

	   @PostMapping("/search/{page}/{count}/{keyword}")
	   public ResponseEntity<?> getSearchName(@PathVariable("page") int page, @PathVariable("count") int count, @PathVariable final String keyword) {
	      List<Users> use = usersRepo.findName(keyword, PageRequest.of(page, count));
	      return ResponseEntity.status(HttpStatus.OK).body(use);
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
	
	@PutMapping("/updateuser/{userid}")
	public ResponseEntity<?> updateUser(@PathVariable int userid, @RequestBody Users users) {
		
		 Optional<Users> optionalUser = usersRepo.findById(userid);
		 
	    if (!optionalUser.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }

	    Users existingUser = optionalUser.get();
	    existingUser.setName(users.getName());
	    existingUser.setEmail(users.getEmail());
	    existingUser.setDesignation(users.getDesignation());
	    existingUser.setPhoneno(users.getPhoneno());
	    existingUser.setPassword(users.getPassword());
	    
	    usersRepo.save(existingUser);
	    return ResponseEntity.status(HttpStatus.OK).body(existingUser);
	}

	
	
	@DeleteMapping("/deleteuser/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") int userId) {
	    Optional<Users> user = usersRepo.findById(userId);
	    if (!user.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }

	    // Delete the user, which will also delete the associated addresses and banks
	    usersRepo.delete(user.get());

	    return ResponseEntity.status(HttpStatus.OK).body("User and associated records deleted successfully");
	}
	
	
}


