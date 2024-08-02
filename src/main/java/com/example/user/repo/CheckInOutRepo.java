package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.CheckInOut;

@Repository
public interface CheckInOutRepo extends JpaRepository<CheckInOut, Integer> {

}
