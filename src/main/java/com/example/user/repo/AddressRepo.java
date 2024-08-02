package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

}
