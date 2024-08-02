package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Attendance;
import com.example.user.model.Salary;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, Integer> {

}
