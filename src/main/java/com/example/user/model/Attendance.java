package com.example.user.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Attendance")
public class Attendance {
	
	@Id
	@GeneratedValue
	private int attendid;
	private String workingdays;
	private String holidays;
	private String present;
	private String cl_sl;
	private String lop;
	
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Users> user;

	
	
	public List<Users> getUser() {
		return user;
	}
	public void setUser(List<Users> user) {
		this.user = user;
	}
	
	public int getAttendid() {
		return attendid;
	}
	public void setAttendid(int attendid) {
		this.attendid = attendid;
	}
	public String getWorkingdays() {
		return workingdays;
	}
	public void setWorkingdays(String workingdays) {
		this.workingdays = workingdays;
	}
	public String getHolidays() {
		return holidays;
	}
	public void setHolidays(String holidays) {
		this.holidays = holidays;
	}
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getCl_sl() {
		return cl_sl;
	}
	public void setCl_sl(String cl_sl) {
		this.cl_sl = cl_sl;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	
	
}
