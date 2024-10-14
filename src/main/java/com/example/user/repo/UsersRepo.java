package com.example.user.repo;



import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.user.model.Address;
//import com.example.user.model.Address;
import com.example.user.model.Bank;
import com.example.user.model.Users;
import com.example.user.model.Vaccant;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	@Query(value= "select * from users where  name = :name AND password = :password", nativeQuery = true)
	Users findUser(String name, String password);

	@Query(value ="select * from users",nativeQuery = true)
	List<Users> findAllPageable(PageRequest pageRequest);
	
	@Query(value = "select count(*) from Users",nativeQuery = true)
	Long getCount();
	
	@Query(value="select  count(*) from users where lower(name) LIKE CONCAT ('%', ?1 , '%')", nativeQuery=true)
	Long getSearchCount(String keyword);
	
	@Query(value="select * from users where lower(name) LIKE CONCAT ('%', ?1 , '%')", nativeQuery=true)
	List<Users> findName(String keyword, PageRequest of);
	
	 Users findByUserid(int userid);


	
}
