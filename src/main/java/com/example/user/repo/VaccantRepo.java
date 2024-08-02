package com.example.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user.model.Vaccant;

@Repository
public interface VaccantRepo extends JpaRepository<Vaccant, Integer> {

}
