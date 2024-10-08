package com.example.user.controller;

import com.example.user.model.CheckInOut;
import com.example.user.model.Users;
import com.example.user.repo.CheckInOutRepo;
import com.example.user.repo.UsersRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/punch")
public class CheckInOutController {

    @Autowired
    private CheckInOutRepo checkInOutRepo;

    @Autowired
    private UsersRepo usersRepo;

//    @PostMapping("/checkin/{userId}")
//    public ResponseEntity<?> setCheckIn(@PathVariable int userId) {
//        Users user = usersRepo.findById(userId).get();
//        
//        CheckInOut checkInOut = new CheckInOut();
//        checkInOut.setCheckIn(LocalDateTime.now());
//        
//        user.getCheckInOut().add(checkInOut);
//        usersRepo.save(user);
//        
//       return ResponseEntity.status(HttpStatus.OK)
//				.body(checkInOut);
//    }
//
//    @PostMapping("/checkout/{checkInOutId}")
//    public ResponseEntity<?> setCheckOut( @PathVariable int  checkInOutId) {
//    	 CheckInOut checkInOut = checkInOutRepo.findById(checkInOutId).orElseThrow(() -> new RuntimeException("CheckInOut record not found"));
//
//         // Set the check-out time
//         checkInOut.setCheckOut(LocalDateTime.now());
//
//         // Save the updated check-out record
//         CheckInOut savedCheckInOut = checkInOutRepo.save(checkInOut);
//
//         return ResponseEntity.status(HttpStatus.OK).body(savedCheckInOut);
//                
//
//   
//    }
    
    @PostMapping("/checkin/{userId}")
    public ResponseEntity<?> setCheckIn(@PathVariable int userId) {
        Users user = usersRepo.findById(userId).orElse(null);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        // Get today's date
        LocalDate today = LocalDate.now();

        // Check if the user has already checked in today
        Optional<CheckInOut> existingCheckInOut = user.getCheckInOut().stream()
            .filter(cio -> cio.getCheckIn() != null && cio.getCheckIn().toLocalDate().equals(today))
            .findFirst();

        if (existingCheckInOut.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User has already checked in today.");
        }

        // Proceed with check-in
        CheckInOut checkInOut = new CheckInOut();
        checkInOut.setCheckIn(LocalDateTime.now());

        user.getCheckInOut().add(checkInOut);
        usersRepo.save(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(checkInOut);
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<?> setCheckOut(@PathVariable int userId) {
        Users user = usersRepo.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        // Get today's date
        LocalDate today = LocalDate.now();

        // Find today's check-in record
        Optional<CheckInOut> checkInOut = user.getCheckInOut().stream()
            .filter(cio -> cio.getCheckIn() != null && cio.getCheckIn().toLocalDate().equals(today))
            .findFirst();

        if (checkInOut.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No check-in record found for today.");
        }

        CheckInOut record = checkInOut.get();

        // Ensure that the check-out hasn't already occurred
        if (record.getCheckOut() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User has already checked out today.");
        }

        // Set the check-out time
        record.setCheckOut(LocalDateTime.now());

        // Save the updated check-out record
        checkInOutRepo.save(record);

        return ResponseEntity.status(HttpStatus.OK).body(record);
    }
    
    @GetMapping("/checkinout/{userId}")
    public ResponseEntity<?> getCheckInOutDetails(@PathVariable int userId) {
        Users user = usersRepo.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        // Fetch check-in and check-out details
        List<CheckInOut> checkInOutList = user.getCheckInOut();

        return ResponseEntity.status(HttpStatus.OK).body(checkInOutList);
    }

}

