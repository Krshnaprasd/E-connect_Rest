package com.example.user.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.user.model.Apply;
import com.example.user.model.Users;
import com.example.user.repo.ApplyRepo;
import com.example.user.repo.UsersRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@RestController
@RequestMapping("/user/job")
public class ApplyController {
	
	  @Autowired
	    private ApplyRepo applyRepo;

	    @Autowired
	    private UsersRepo usersRepository;

	    @Autowired
	    private JavaMailSender mailSender;
	    
	    
	    @PostMapping("/apply/{userId}")
	    public ResponseEntity<?> applyForJob(
	        @PathVariable int userId, 
	        @RequestParam("email") String email, 
	        @RequestParam("phoneno") String phoneno,
	        @RequestParam("resume") MultipartFile resume, 
	        @RequestParam("expectedctc") String expectedctc,
	        @RequestParam("currentctc") String currentctc,
	        @RequestParam("preferredlocation") String preferredlocation,
	        @RequestParam("jobId") String jobId) throws IOException, MessagingException {

	        Optional<Users> optionalUser = usersRepository.findById(userId);
	        if (!optionalUser.isPresent()) {
	            // Return JSON error response
	            return ResponseEntity.badRequest().body(new ApiResponse(false, "User not found."));
	        }
	        
	        Users user = optionalUser.get();

	        // Process file (resume)
	        byte[] resumeBytes = resume.getBytes();

	        // Save the job application
	        Apply apply = new Apply();
	        apply.setUsers(user);
	        apply.setEmail(email);
	        apply.setPhoneno(phoneno);
	        apply.setResume(resumeBytes);  
	        apply.setExpectedctc(expectedctc);
	        apply.setCurrentctc(currentctc);
	        apply.setPreferredlocation(preferredlocation);

	        applyRepo.save(apply);

	        sendApplicationEmail(user, apply, resume);

	        // Return success response
	        return ResponseEntity.ok(new ApiResponse(true, "Job application submitted successfully!"));
	    }

	    // Custom response class for consistent structure
	    public class ApiResponse {
	        private boolean success;
	        private String message;

	        public ApiResponse(boolean success, String message) {
	            this.success = success;
	            this.message = message;
	        }

	        public boolean isSuccess() {
	            return success;
	        }

	        public String getMessage() {
	            return message;
	        }
	    }

	    private void sendApplicationEmail(Users user, Apply apply, MultipartFile resume) throws MessagingException, IOException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	        helper.setTo(apply.getEmail()); // Recipient
	        helper.setSubject("Job Application Confirmation");
	        helper.setText("Dear " + user.getName() + ",\n\n"
	                + "Thank you for applying for the job!\n"
	                + "We'll review your application and contact you to let you know the steps you can expect next. You can also follow the progress of your application in our job portal!\n\n"
	                + "Here are your application details:\n\n"
	                + "Mobile No: " + apply.getPhoneno() + "\n"
	                + "Expected CTC: " + apply.getExpectedctc() + "\n"
	                + "Current CTC: " + apply.getCurrentctc() + "\n"
	                + "Preferred Location: " + apply.getPreferredlocation() + "\n\n"
	                + "Best regards,\n"
	                + "E-Connect Solutions Pvt Ltd");

	        // Attach the resume file
	        helper.addAttachment("Resume.pdf", new ByteArrayResource(resume.getBytes()));

	        // Send the email
	        mailSender.send(mimeMessage);
	    }

}
