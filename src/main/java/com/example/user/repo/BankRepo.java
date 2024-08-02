package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Bank;

@Repository
public interface BankRepo extends JpaRepository<Bank, Integer> {

}
