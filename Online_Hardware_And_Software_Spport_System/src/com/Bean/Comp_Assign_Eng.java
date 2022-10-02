package com.Bean;

public class Comp_Assign_Eng {
	private int compid;
	private String status;
	private String des;
	private String category;
	private String eng_username;
	
	public Comp_Assign_Eng() {
		// TODO Auto-generated constructor stub
	}

	public Comp_Assign_Eng(int compid, String status, String des,String category, String eng_username) {
		super();
		this.compid = compid;
		this.status = status;
		this.des = des;
		this.category=category;
		this.eng_username = eng_username;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEng_username() {
		return eng_username;
	}

	public void setEng_username(String eng_username) {
		this.eng_username = eng_username;
	}

	@Override
	public String toString() {
		return "Comp_Assign_Eng [compid=" + compid + ", status=" + status + ", des=" + des + ", category=" + category
				+ ", eng_username=" + eng_username + "]";
	}
	
	
	
	
	

}

