package com.example.user.repo;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.user.model.Apply;


public interface ApplyRepo extends JpaRepository<Apply, Integer>{
	
	@Query(value ="select * from applicants order by created_at desc",nativeQuery = true)
	List<Apply> findAllPageable(PageRequest pageRequest);
	
	@Query(value = "select count(*) from applicants",nativeQuery = true)
	Long getCount();
	
	@Query(value="select  count(*) from applicants where lower(jobtitle) LIKE CONCAT ('%', ?1 , '%')", nativeQuery=true)
	Long getSearchCount(String keyword);
	
	@Query("SELECT a FROM Apply a JOIN a.users u JOIN a.vaccant v " +
	           "WHERE LOWER(v.jobtitle) LIKE CONCAT('%', LOWER(?1), '%') " +
	           "OR LOWER(u.name) LIKE CONCAT('%', LOWER(?1), '%')")
	    List<Apply> findJobName(String jobName, Pageable pageable);

}
