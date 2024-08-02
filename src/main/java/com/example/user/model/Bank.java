package com.example.user.model;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="BankDetails")
public class Bank {
	
	@Id
	@GeneratedValue
	private int bankid;
	private String bank;
	private String Accountno;
	private String Ifsccode;
	private String branch;
	private String PF;
	private String ESI;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Users> users;

	public int getBankid() {
		return bankid;
	}

	public void setBankid(int bankid) {
		this.bankid = bankid;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountno() {
		return Accountno;
	}

	public void setAccountno(String accountno) {
		Accountno = accountno;
	}

	public String getIfsccode() {
		return Ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		Ifsccode = ifsccode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPF() {
		return PF;
	}

	public void setPF(String pF) {
		PF = pF;
	}

	public String getESI() {
		return ESI;
	}

	public void setESI(String eSI) {
		ESI = eSI;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}



}
