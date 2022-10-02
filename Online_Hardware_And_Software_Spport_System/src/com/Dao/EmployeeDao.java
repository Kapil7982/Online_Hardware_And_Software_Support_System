package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Bean.Comp_Assign_Eng;
import com.Bean.Complain;
import com.Bean.Employee;
import com.Utility.DBUtil;

public interface EmployeeDao {
	
	public static Employee checkEmployee(String username , String password) {
		String msg = "EMPLOYEE NOT FOUND ";
		Employee emp = null;
		int flag=0;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("select * from employee where username = ? and "
					+ "password = ? ");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			 ResultSet rs =  ps.executeQuery();
			 
			 if(rs.next()) {
				 msg="Employee Login Suceessful";
				 flag=1;
			 }
			  
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		if(flag==1)
		 emp = new Employee(username,password);
		
		System.out.println(msg);
		return emp;
	}
	
	public static String regEmployee(String username, String password) {
		String msg = "Technical Error Registration Failed ";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("insert into employee values(?,?)");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			int x = ps.executeUpdate();
			if(x>0) msg="Employee Registration successful";
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return msg;
	}
	
	
	public String regAComp(String status,String des, String category,Employee emp);
	
	public Comp_Assign_Eng getCompDetails(int compid,Employee emp);
	
	public List<Complain> getYourCompHistory(Employee emp) throws NullPointerException;
	
	public String changePass(String newPass,Employee emp);

}
