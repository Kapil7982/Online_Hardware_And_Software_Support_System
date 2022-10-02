package com.Bean;

import com.Dao.EngineerIMpl;

public class Engineer extends EngineerIMpl{
	
	private String username;
	private String password;
	
	public Engineer() {
		// TODO Auto-generated constructor stub
	}

	public Engineer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Engineer [username=" + username + ", password=" + password + "]";
	}
	
	
	

}
