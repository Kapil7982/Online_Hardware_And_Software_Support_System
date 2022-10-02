package com.UseCase;

import java.util.List;
import java.util.Scanner;

import com.Bean.Complain;
import com.Bean.Engineer;
import com.Bean.HOD;
import com.Dao.EngineerDao;
import com.Dao.HODDao;
import com.Dao.HODImpl;
import com.Exceptions.ComplainException;
import com.Exceptions.EmployeeException;
import com.Exceptions.EngineerException;
import com.Exceptions.HODException;



public class HODUseCase{
	
	public void selectOptions(HOD hod) throws EngineerException, ComplainException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome "+hod.getUsername());
		System.out.println();
		
		while(true) {
		
		System.out.println();
		System.out.println("1. Register New Engineer ");
		System.out.println("2. Get All The Engineers List ");
		System.out.println("3. Delete An Engineer ");
		System.out.println("4. Show All The Complains ");
		System.out.println("5. Assign Complain with Engineer ");
		System.out.println("6. Logout");
		int c = sc.nextInt();
		
		HODDao hd= new HODImpl();
		
		if(c==1) {
			System.out.println("Register New Engineer ");
			System.out.println("======================");
			System.out.println("Enter UserName");
			String username=sc.next();
			System.out.println("Enter Password");
			String password = sc.next();
		    String res=null;
			res = EngineerDao.regNewEngineer(username, password);
			  System.out.println(res+"\n");
		  
		    
		    
			
		}else if(c==2) {
		    
			System.out.println("The Engineers List ");
			System.out.println("====================");
			
			List<Engineer> englist=null;
			englist = hd.getAllEng();
			englist.forEach(s->{
				System.out.println("Engineer Username : "+s.getUsername());
			});
			System.out.println();
			
			
			
		}else if(c==3) {
			System.out.println("Deleting An Engineer ");
			System.out.println("=================================");
			System.out.println("Enter Engineer Username to Delete ");
			String username = sc.next();
			String res=null;
			res = hd.deleteAnEng(username);
			System.out.println(res+"\n");
			
			
			
		}
		else if(c==4) {
			System.out.println("Displaying all the Raised  Complains ");
			System.out.println("=====================================");
		    List<Complain> complist=null;
			complist = hd.getAllComp();
			 complist.forEach(com->{
			    	System.out.println("Complain id : "+com.getCompid());
			    	System.out.println("Complain Status : "+com.getStatus());
			        System.out.println("Complain Description : "+com.getDes());
			        System.out.println("Complain Category : "+com.getCategory());
			        System.out.println("------------------");
			    });		
		   
		    
		  
		    
		    }
		else if(c==5) {
			System.out.println("Assigning the complain with Engineer");
			System.out.println("Enter Complain id : ");
			int compid= sc.nextInt();
			System.out.println("Enter Engineer Username ");
			String username=sc.next();
			String res=null;
			res = hd.assignCompToEng(compid, username);
			System.out.println(res);
			
			
			
			
		}
		else if(c==6) {
		   System.out.println("Thank You ");
		   try {
			Main.homepage();
		} catch (HODException | EngineerException | EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   break;
		}
		else {
			System.out.println("Invalid Input ");
	
		}
		
		}
		
		
	}
	

}