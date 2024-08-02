package com.example.user.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.user.model.Address;
//import com.example.user.model.Address;
import com.example.user.model.Bank;
import com.example.user.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	@Query(value= "select * from Users where  name = :name AND password = :password", nativeQuery = true)
	Users findUser(String name, String password);


	


	
}
