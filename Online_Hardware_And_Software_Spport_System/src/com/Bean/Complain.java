package com.Bean;

public class Complain {

	private int compid;
	private String status;
	private String des;
	private String category;
	private String emp_username;
	
	public Complain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Complain(int compid, String status, String des, String category, String emp_username) {
		super();
		this.compid = compid;
		this.status = status;
		this.des = des;
		this.category = category;
		this.emp_username = emp_username;
	}

	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return des;
	}

	public void setDesc(String des) {
		this.des = des;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEmp_username() {
		return emp_username;
	}

	public void setEmp_username(String emp_username) {
		this.emp_username = emp_username;
	}

	@Override
	public String toString() {
		return "Complain [compid=" + compid + ", status=" + status + ", des=" + des + ", category=" + category
				+ ", emp_username=" + emp_username + "]";
	}
	
	
	
}
