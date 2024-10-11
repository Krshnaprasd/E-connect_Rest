package com.example.user.model;

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
	
	 @Id
	@GeneratedValue
	private int applyid;
	private String email;
	private String phoneno;
	private byte[] resume;
	private String expectedctc;
	private String currentctc;
	private String preferredlocation;
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
	
	

}
