package com.UseCase;

import java.util.List;
import java.util.Scanner;

import com.Bean.Complain;
import com.Bean.Engineer;
import com.Exceptions.ComplainException;
import com.Exceptions.EmployeeException;
import com.Exceptions.EngineerException;
import com.Exceptions.HODException;

public class EngineerUseCase {
	
	public static void getEngineerOptions(Engineer eng) throws ComplainException, EngineerException, EmployeeException {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Hello Engineer "+eng.getUsername());
		
		while(true) {
		
		System.out.println("1. Get Complaints Assigned to You ");
		System.out.println("2. Update complaint Status ");
		System.out.println("3. Get Your Complain History ");
		System.out.println("4. Change Your Password ");
		System.out.println("5. LogOut ");
		int c = sc.nextInt();
		
		if(c==1) {
			System.out.println("Complaints Assigned To you ");
			System.out.println("==========================");
			List<Complain> complist=null;
			complist = eng.getCompsOfYou(eng);
			   complist.forEach(com->{
			    	System.out.println("Complain id : "+com.getCompid());
			    	System.out.println("Complain Status: "+com.getStatus());
			    	System.out.println("Complain Description : "+com.getDesc());
			    	System.out.println("Complain Category : "+com.getCategory());
			    	System.out.println("-------------------------------------");
			
			    });
		 
			System.out.println();
		    
		}
		else if(c==2) {
			System.out.println("Update Complaint Status ");
			System.out.println("==========================");
			System.out.println("Enter Complain id ");
			int compid= sc.nextInt();
			System.out.println("Enter Status Message ");
			String status  = sc.next();
			
			String res=null;
			res = eng.updateCompStatus(compid,status, eng);
			System.out.println(res);
		
			
		}else if(c==3) {
			System.out.println("Get Your All Assigned Complain History ");
			
			try {
				List<Complain> complist=null;
				complist = eng.getCompHistory(eng);
				
				complist.forEach(com->{
					System.out.println("Complain id : "+com.getCompid());
					System.out.println("Complain Status : "+com.getStatus());
					System.out.println("Complain Description : "+com.getDesc());
					System.out.println("Complain Category : "+com.getCategory());
					System.out.println("------------------------");
				});
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
		
			
		}
		else if(c==4) {
			System.out.println("Change Your Password ");
			System.out.println("=====================");
			System.out.println("Enter new Password ");
			String newPass = sc.next();
			String res=null;
			res = eng.changePass(newPass, eng);
			System.out.println(res);
			
		}
		else if(c==5) {
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
			System.out.println("Invalid Input");
		}
		
		
		
		
		}
	
		
	
		
	}

}
