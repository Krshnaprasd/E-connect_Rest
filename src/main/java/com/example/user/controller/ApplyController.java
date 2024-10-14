package com.example.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.example.user.model.Apply;
import com.example.user.model.Users;
import com.example.user.model.Vaccant;
import com.example.user.repo.ApplyRepo;
import com.example.user.repo.UsersRepo;
import com.example.user.repo.VaccantRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@RestController
@RequestMapping("/user/job")
public class ApplyController {
	
		@Autowired
		private VaccantRepo vaccantRepo;
	
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
	        @RequestParam("jobId") int jobId) throws IOException, MessagingException {

	        Optional<Users> optionalUser = usersRepository.findById(userId);
	        if (!optionalUser.isPresent()) {
	            // Return JSON error response
	            return ResponseEntity.badRequest().body(new ApiResponse(false, "User not found."));
	        }
	        
	        Optional<Vaccant> optionalVaccant = vaccantRepo.findById(jobId);  // Fetch job by jobId
	        if (!optionalVaccant.isPresent()) {
	            return ResponseEntity.badRequest().body(new ApiResponse(false, "Job not found."));
	        }

	        Users user = optionalUser.get();
	        Vaccant vaccant = optionalVaccant.get();

	        // Process file (resume)
	        byte[] resumeBytes = resume.getBytes();

	        // Save the job application
	        Apply apply = new Apply();
	        apply.setUsers(user);
	        apply.setVaccant(vaccant); 
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
	    
	    
	    @PostMapping("/select/{applyId}")
	    public ResponseEntity<?> selectApplicant(@PathVariable int applyId) throws MessagingException {
	        Optional<Apply> optionalApply = applyRepo.findById(applyId);
	        if (!optionalApply.isPresent()) {
	            return ResponseEntity.badRequest().body(new ApiResponse(false, "Application not found."));
	        }

	        Apply apply = optionalApply.get();
	        sendSelectionEmail(apply);
	        return ResponseEntity.ok(new ApiResponse(true, "Applicant selected and Mail sent."));
	    }

	    @PostMapping("/reject/{applyId}")
	    public ResponseEntity<?> rejectApplicant(@PathVariable int applyId) throws MessagingException {
	        Optional<Apply> optionalApply = applyRepo.findById(applyId);
	        if (!optionalApply.isPresent()) {
	            return ResponseEntity.badRequest().body(new ApiResponse(false, "Application not found."));
	        }

	        Apply apply = optionalApply.get();
	        sendRejectionEmail(apply);
	        return ResponseEntity.ok(new ApiResponse(true, "Applicant rejected and Mail sent."));
	    }

	    private void sendSelectionEmail(Apply apply) throws MessagingException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	        helper.setTo(apply.getEmail());
	        helper.setSubject("Job Application Update");
	        helper.setText("Dear " + apply.getUsers().getName() + ",\n\n"
	                + "Congratulations! You have been selected for the job position.\n"
	                + "We will contact you soon with further steps.\n\n"
	                + "Best regards,\n"
	                + "E-Connect Solutions Pvt Ltd");

	        mailSender.send(mimeMessage);
	    }

	    private void sendRejectionEmail(Apply apply) throws MessagingException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	        helper.setTo(apply.getEmail());
	        helper.setSubject("Job Application Update");
	        helper.setText("Dear " + apply.getUsers().getName() + ",\n\n"
	                + "We regret to inform you that you have not been selected for the job position at this time.\n"
	                + "We appreciate your interest in the role and encourage you to apply again in the future.\n\n"
	                + "Best regards,\n"
	                + "E-Connect Solutions Pvt Ltd");

	        mailSender.send(mimeMessage);
	    }
	    
	    @GetMapping("/resume/download/{applyid}")
	    public ResponseEntity<byte[]> downloadResume(@PathVariable int applyid) {
	        // Fetch the applicant from the repository
	        Apply applicant = applyRepo.findById(applyid)
	                                  .orElseThrow(() -> new RuntimeException("Applicant not found"));
	        
	        byte[] resumeData = applicant.getResume();  // Assuming `resume` is a byte[] field in the Applicant entity
	        
	        // Create the response headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);  // Assuming the resume is a PDF
	        headers.setContentDispositionFormData("attachment", "resume.pdf");  // Set filename

	        // Return the resume data
	        return new ResponseEntity<>(resumeData, headers, HttpStatus.OK);
	    }
	    
	    
	    
	    @GetMapping("/get/{page}/{count}")
		   public ResponseEntity<?> getjobs(@PathVariable("page") int page, @PathVariable("count") int count) {
		      List<Apply> app = applyRepo.findAllPageable(PageRequest.of(page, count));
		      return ResponseEntity.status(HttpStatus.OK).body(app);
		   }
		   
		   @GetMapping("/get/count")
			public ResponseEntity<?> getjobsCount() {
				Long count = applyRepo.getCount();
				return ResponseEntity.status(HttpStatus.OK).body(count);
			}
		   
		   @PostMapping("/search/count/{keyword}")
			public ResponseEntity<?> getSearchCategoryCount(@PathVariable final String keyword ) {
				Long count = applyRepo.getSearchCount(keyword);
				return ResponseEntity.status(HttpStatus.OK).body(count);
		   }

		   @PostMapping("/search/{page}/{count}/{keyword}")
		   public ResponseEntity<?> getSearchCategoryName(@PathVariable("page") int page, @PathVariable("count") int count, @PathVariable final String keyword) {
		       List<Apply> applies = applyRepo.findJobName(keyword, PageRequest.of(page, count));
		       return ResponseEntity.status(HttpStatus.OK).body(applies);
		   }
		   
		   
		   
		   @GetMapping("/apply/resume/{applyId}")
		   public ResponseEntity<byte[]> getResume(@PathVariable int applyId) {
		       Optional<Apply> applyOptional = applyRepo.findById(applyId);
		       if (applyOptional.isPresent()) {
		           byte[] resumeBytes = applyOptional.get().getResume(); 
		           return ResponseEntity.ok()
		                   .contentType(MediaType.APPLICATION_PDF) 
		                   .body(resumeBytes);
		       }
		       return ResponseEntity.notFound().build(); 
		   }

}
