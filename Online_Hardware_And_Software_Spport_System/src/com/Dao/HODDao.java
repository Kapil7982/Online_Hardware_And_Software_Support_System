package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



public interface HODDao {

	public static HOD checkHOD(String username ,String password) {
		String msg = "ADMIN NOT FOUND ";
		HOD hod = null;
		int flag=0;
		
		try(Connection conn = DBUtil.provideConnection()){
		PreparedStatement ps = conn.prepareStatement("select * from hod where username=? and password= ? ");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				flag=1;
				msg="HOD Login Successful";
			}
		
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(flag==1)
		hod = new HOD(username,password);
		
		
		
		System.out.println(msg);
		return hod;
	}
	
	
	
	public List<Engineer> getAllEng();
	
	public String delete_An_Eng(String username);
	
	public List<Complain> getAllComp();
	
	public String assign_Complain_To_Eng(int compid,String eng_username);
}
