package com.example.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Jobs") 
public class Vaccant {

	@Id
	@GeneratedValue
	private int Jobid;
	private String manager;
	private String team_lead;
	private String senior_developer;
	private String junior_developer;
	private String frontend_developer;
	private String backend_developer;
	private String training_tutors;
	
	public int getJobid() {
		return Jobid;
	}
	public void setJobid(int jobid) {
		Jobid = jobid;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getTeam_lead() {
		return team_lead;
	}
	public void setTeam_lead(String team_lead) {
		this.team_lead = team_lead;
	}
	public String getSenior_developer() {
		return senior_developer;
	}
	public void setSenior_developer(String senior_developer) {
		this.senior_developer = senior_developer;
	}
	public String getJunior_developer() {
		return junior_developer;
	}
	public void setJunior_developer(String junior_developer) {
		this.junior_developer = junior_developer;
	}
	public String getFrontend_developer() {
		return frontend_developer;
	}
	public void setFrontend_developer(String frontend_developer) {
		this.frontend_developer = frontend_developer;
	}
	public String getBackend_developer() {
		return backend_developer;
	}
	public void setBackend_developer(String backend_developer) {
		this.backend_developer = backend_developer;
	}
	public String getTraining_tutors() {
		return training_tutors;
	}
	public void setTraining_tutors(String training_tutors) {
		this.training_tutors = training_tutors;
	}
	
	
	
	
}
