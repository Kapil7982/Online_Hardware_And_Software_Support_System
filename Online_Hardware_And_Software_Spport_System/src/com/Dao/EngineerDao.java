package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Bean.Complain;
import com.Bean.Engineer;
import com.Uitility.DBUtil;

public interface EngineerDao {

	public static Engineer checkEngineer(String username , String password) {
		String msg="ENGINEER NOT FOUND ";
		Engineer eng= null;
		int flag=0;
		
		try (Connection conn = DBUtil.provideConnection()){
			PreparedStatement ps =  conn.prepareStatement("select * from engineer "
					+ "where username = ? and password = ?");
			
			 ps.setString(1,username);
			 ps.setString(2, password);
			 
			 ResultSet rs =  ps.executeQuery();
			 
			 if(rs.next()) {
				 flag=1;
				 
				 msg="Enginner Login Successful ";
			 }
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		}
		System.out.println(msg);
		
		
		if(flag==1) {
		eng=  new Engineer(username,password);
		}
		
		return eng;
		
	
		
	
	}
	
	
	public static String regNewEngineer(String username , String password) {
		String msg="Registration failed ";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into engineer values (?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			
			int x = ps.executeUpdate();
			if(x>0) msg="Engineer Registration Successful ";			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return msg;
	}
	
	
	
	public List<Complain> getCompsOfYou(Engineer eng);
	
	public String updateComplainStatus(int compid,String status,Engineer eng);
	
	public List<Complain> getCompHistory(Engineer eng);
	
	public String change_Pass(String newPass,Engineer eng);

}
