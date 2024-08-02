package com.example.user.model;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue
	private int userid;
	private String name;
	private String designation;
	private String email;
	private String phoneno;
	private String password;
	private String confirmpassword;
	
	@ManyToMany( cascade = CascadeType.ALL)
	private List<Address> Address;
	
	@ManyToMany( cascade = CascadeType.ALL)
	private List<Bank> Bank;	
	
	@ManyToMany( cascade = CascadeType.ALL)
	private List<Attendance> attendance;
	
	@ManyToMany( cascade = CascadeType.ALL)
	private List<Salary> salary;
	
	  @ManyToMany( cascade = CascadeType.ALL)
	    private List<CheckInOut> checkInOut;
	
	public List<Address> getAddress() {
		return Address;
	}
	public void setAddress(List<Address> address) {
		Address = address;
	}
	public List<Bank> getBank() {
		return Bank;
	}
	public void setBank(List<Bank> bank) {
		Bank = bank;
		
	}
	
	
	public List<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}
	public List<Salary> getSalary() {
		return salary;
	}
	public void setSalary(List<Salary> salary) {
		this.salary = salary;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public List<CheckInOut> getCheckInOut() {
		return checkInOut;
	}
	public void setCheckInOut(List<CheckInOut> checkInOut) {
		this.checkInOut = checkInOut;
	}
	
	
	
}
