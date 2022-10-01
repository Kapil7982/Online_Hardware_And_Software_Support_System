package com.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bean.Complain;
import com.Bean.Engineer;
import com.Uitility.DBUtil;




public class HODImpl implements HODDao{


	@Override
	public List<Engineer> getAllEng() {
		List<Engineer> englist= new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from engineer");
			
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {
				String username= rs.getString("username");
				String password = rs.getString("password");
				Engineer eng = new Engineer(username,password);
				englist.add(eng);
			}
			  
		
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		

		
		return englist;
		}

	@Override
	public String delete_An_Eng(String username) {
		String msg="deletion failed ";
		
		
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("delete from engineer where username = ?");
			ps.setString(1, username);
			
			int x = ps.executeUpdate();
			if(x>0) msg=username+" successfully deleted ";
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return msg;
	}

	@Override
	public List<Complain> getAllComp() {
		List<Complain> complist = new ArrayList<>();
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from complain where status='raised'");
			
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {
				int cid = rs.getInt("cid");
				String status = rs.getString("status");
				String des = rs.getString("des");
				String category = rs.getString("category");
				String emp_username= rs.getString("emp_username");
				
				Complain comp = new Complain(cid,status,des,category,emp_username);
				complist.add(comp);
				
			}
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return complist;
	}

	@Override
	public String assign_Complain_To_Eng(int compid, String eng_username) {
		String msg=compid+" is not assigned "+eng_username+" successfully ";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("insert into eng_com (cid,eng_username) values(?,?)");
			ps.setInt(1, compid);
			ps.setString(2, eng_username);
			PreparedStatement ps2 = conn.prepareStatement("update complain set status='assigned' where cid=?");
			ps2.setInt(1, compid);
			int y = ps2.executeUpdate();
			
			int x  = ps.executeUpdate();
			
			if(x>0 && y>0 ) msg="Complaint "+compid+" is assigned "+eng_username+" successfully ";
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return msg;
		
	}

}