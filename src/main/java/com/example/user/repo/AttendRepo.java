package com.example.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Attendance;
import com.example.user.model.Users;

@Repository
public interface AttendRepo extends JpaRepository<Attendance, Integer> {
	
	   List<Attendance> findByUser(Users user); // Method to find attendance by user ID
	
}
