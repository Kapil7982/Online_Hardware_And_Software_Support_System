package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Bean.Comp_Assign_Eng;
import com.Bean.Complain;
import com.Bean.Employee;
import com.Utility.DBUtil;

public class EmployeeImpl implements EmployeeDao{

	@Override
	public String regAComp(String status,String des,String category,Employee emp) {
		String msg="Your Complain not Registered ";
		try (Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps =  conn.prepareStatement("insert into complain(status,des,category,emp_username) values(?,?,?,?)");
			ps.setString(1,status);
			ps.setString(2,des);
			ps.setString(3,category);
			ps.setString(4, emp.getUsername());
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("select cid from complain where des=? and category = ? and emp_username=?");
			
			ps2.setString(1,des);
			ps2.setString(2, category);
			ps2.setString(3, emp.getUsername());
			
			ResultSet rs = ps2.executeQuery();
			int complainid;
			
			if(rs.next()) {
				complainid=rs.getInt("cid");
				msg="Your Complain Id "+complainid;
			}else {
				System.out.println("Complain Not Found");
			}
			
					
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	
		
		
	    return msg;
	}

	@Override
	public Comp_Assign_Eng getCompDetails(int compid,Employee emp){
		 Comp_Assign_Eng coe = null;
		 
		 try(Connection conn = DBUtil.provideConnection()) {
			 
			PreparedStatement ps =   conn.prepareStatement("select c.cid,c.status,c.des,c.category,c.emp_username,ec.eng_username from complain c inner join eng_com ec on"
					+ " c.cid=ec.cid and c.cid=? and ec.eng_username=?");
			
			ps.setInt(1, compid);
			ps.setString(2,emp.getUsername());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int cid = rs.getInt(1);
				String status = rs.getString(2);
				String des = rs.getString(3);
				String category = rs.getString(4);
				String username= rs.getString(5);
		
				coe = new Comp_Assign_Eng(cid,status,des,category,username);
				
			}else {
				System.out.println("No Engineer Assigned Yet ");
			}
			
			
		} catch (SQLException e) {
		  
			System.out.println(e.getMessage());
		}
		 
		 
		 return coe;
		
	}

	@Override
	public List<Complain> getYourCompHistory(Employee emp) {
		 
		List<Complain> complist = new ArrayList<>();
		
		try (Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps =  conn.prepareStatement("select * from complain where emp_username= ? ");
			ps.setString(1, emp.getUsername());
			
			ResultSet rs=  ps.executeQuery();
			
			while(rs.next()) {
				int cid=rs.getInt("cid");
				String status = rs.getString("status");
				String des = rs.getString("des");
				String category = rs.getString("category");
				String emp_username = rs.getString("emp_username");
				
				Complain comp = new Complain(cid,status,des,category,emp_username);
				complist.add(comp);
				
			}
			
			
		
					
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return complist;
		
	}

	@Override
	public String changePass(String newPass,Employee emp) {
		String msg="password not updated ";
		
	try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn.prepareStatement("update employee set password = ? where username=?");
			ps.setString(1, newPass);
			ps.setString(2, emp.getUsername());
			
			int x = ps.executeUpdate();
			if(x>0) msg="Password Updated Successfully ";
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return msg;
	
	}

}
