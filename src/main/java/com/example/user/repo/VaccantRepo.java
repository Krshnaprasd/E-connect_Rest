package com.example.user.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.user.model.Vaccant;

@Repository
public interface VaccantRepo extends JpaRepository<Vaccant, Integer> {
	
	@Query(value ="select * from jobs order by created_at desc",nativeQuery = true)
	List<Vaccant> findAllPageable(PageRequest pageRequest);
	
	@Query(value = "select count(*) from jobs",nativeQuery = true)
	Long getCount();
	
	@Query(value="select  count(*) from jobs where lower(jobtitle) LIKE CONCAT ('%', ?1 , '%')", nativeQuery=true)
	Long getSearchCount(String keyword);
	
	@Query(value="select * from jobs where lower(jobtitle) LIKE CONCAT ('%', ?1 , '%')", nativeQuery=true)
	List<Vaccant> findJobName(String keyword, PageRequest of);

}
