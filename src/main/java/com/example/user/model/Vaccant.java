package com.example.user.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Jobs") 
public class Vaccant {

	@Id
	@GeneratedValue
	private int jobid;
	private String jobtitle;
	private String jobdescription;
	private String experience;
	private String openings;
	private String location;
	private String expirydate;
	
	@jakarta.persistence.Column(name = "created_at", nullable = false,updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@jakarta.persistence.Column(name = "updated_at",updatable = false)
	@CreationTimestamp
	private LocalDateTime updatedAt;
	
	
	
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		jobid = jobid;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getJobdescription() {
		return jobdescription;
	}
	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getOpenings() {
		return openings;
	}
	public void setOpenings(String openings) {
		this.openings = openings;
	}
	
	
	
	
	
	
}
