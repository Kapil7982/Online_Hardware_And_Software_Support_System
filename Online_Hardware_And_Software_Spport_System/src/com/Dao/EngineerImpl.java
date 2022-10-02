package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bean.Complain;
import com.Bean.Engineer;
import com.Utility.DBUtil;

public class EngineerIMpl implements EngineerDao {

	@Override
	public List<Complain> getCompsOfYou(Engineer eng) {
		List<Complain> complist = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("select c.cid,c.status,c.des,c.category,c.emp_username from complain c inner join"
					+ " eng_com ec on c.cid=ec.cid and ec.eng_username=? and c.status !='completed'");
			ps.setString(1,eng.getUsername());
			
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {	
				int cid = rs.getInt("c.cid");
				String status = rs.getString("c.status");
				String des = rs.getString("c.des");
				String category = rs.getString("c.category");
				String emp_username = rs.getString("c.emp_username");
				Complain comp = new Complain(cid,status,des,category,emp_username);
				complist.add(comp);
			}
		    
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return complist;
		
	}

	@Override
	public String updateCompStatus(int compid ,String status,Engineer eng) {
		String msg="not updated ";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("update complain c inner join eng_com ec on c.cid=ec.cid and c.cid=? and ec.cid=? and ec.eng_username=? set c.status=?;");
		
			ps.setInt(1, compid);
			ps.setInt(2, compid);
			ps.setString(3, eng.getUsername());
			ps.setString(4, status);
			
			int x =ps.executeUpdate();
			if(x>0) msg="status updated successfully ";
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return msg;
	}

	@Override
	public List<Complain> getCompHistory(Engineer eng) {
		List<Complain> complist = new ArrayList<>();
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =  conn.prepareStatement("select c.cid,c.status,c.des,c.category,c.emp_username from "
					+ "complain c inner join eng_com ec on"
					+ " c.cid=ec.cid and ec.eng_username=?;");
			
			ps.setString(1,eng.getUsername());
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {
				int cid = rs.getInt(1);
				String status = rs.getString(2);
				String des = rs.getString(3);
				String category = rs.getString(4);
				String emp_username= rs.getString(5);
				
				Complain comp = new Complain(cid,status,des,category,emp_username);
				complist.add(comp);
				
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		return complist;
	}

	@Override
	public String changePass(String newPass,Engineer eng) {
		String msg = "password not updated ";
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update engineer set password = ? where username=?");
			ps.setString(1, newPass);
			ps.setString(2, eng.getUsername());
			
			int x = ps.executeUpdate();
			if(x>0) msg="Password Updated Successfully ";
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return msg;
	}

}
