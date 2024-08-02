package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Attendance;

@Repository
public interface AttendRepo extends JpaRepository<Attendance, Integer> {

}
