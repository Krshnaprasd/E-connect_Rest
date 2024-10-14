package com.example.user.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.user.model.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Applicants")
public class Apply {
	
	 @ManyToOne
	 @JoinColumn(name = "user_id", nullable = false)
	 private Users users;
	
	 @ManyToOne
	 @JoinColumn(name = "job_id", nullable = false)
	 private Vaccant vaccant;
	 
	 @Id
	@GeneratedValue
	private int applyid;
	private String email;
	private String phoneno;
	private byte[] resume;
	private String expectedctc;
	private String currentctc;
	private String preferredlocation;
	
	@jakarta.persistence.Column(name = "created_at", nullable = false,updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@jakarta.persistence.Column(name = "updated_at",updatable = false)
	@CreationTimestamp
	private LocalDateTime updatedAt;
	
	
	
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public int getApplyid() {
		return applyid;
	}
	public void setApplyid(int applyid) {
		this.applyid = applyid;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public String getExpectedctc() {
		return expectedctc;
	}
	public void setExpectedctc(String expectedctc) {
		this.expectedctc = expectedctc;
	}
	public String getCurrentctc() {
		return currentctc;
	}
	public void setCurrentctc(String currentctc) {
		this.currentctc = currentctc;
	}
	public String getPreferredlocation() {
		return preferredlocation;
	}
	public void setPreferredlocation(String preferredlocation) {
		this.preferredlocation = preferredlocation;
	}
	public Vaccant getVaccant() {
		return vaccant;
	}
	public void setVaccant(Vaccant vaccant) {
		this.vaccant = vaccant;
	}
	
	

}
