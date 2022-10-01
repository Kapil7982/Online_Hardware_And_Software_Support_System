package com.UseCase;

import java.util.Scanner;

import com.Bean.Employee;
import com.Bean.Engineer;
import com.Bean.HOD;
import com.Dao.EmployeeDao;
import com.Dao.EngineerDao;
import com.Dao.HODDao;
import com.Exceptions.ComplainException;
import com.Exceptions.EmployeeException;
import com.Exceptions.EngineerException;
import com.Exceptions.HODException;

public class Main {
	
	public static void homepage() throws HODException, EngineerException, EmployeeException {
      Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome To Hardware And Software Support System ");
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - ");
		
		System.out.println("1. Login As Admin ");
		System.out.println("2. Login As Engineeer ");
		System.out.println("3. Login As Employee ");
		System.out.println("4. Register As Employee ");
		System.out.println("5. EXIT");
	      
		int n = sc.nextInt();
	     
		if(n==1) {
			System.out.println("Enter UserName ");
			String username = sc.next();
			System.out.println("Enter Password ");
			String password = sc.next();
			HOD hod=null;
			hod = HODDao.checkHOD(username, password);
			if(hod!=null) {
			HODUseCase hoduc = new HODUseCase();
			try {
				hoduc.selectOptions(hod);
			} catch (EngineerException | ComplainException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}else if(n==2) {
			System.out.println("Enter UserName ");
			String username = sc.next();
			System.out.println("Enter Password ");
			String password = sc.next();
			Engineer eng=null;
			eng = EngineerDao.checkEngineer(username, password);
			if(eng!=null)
				try {
					EngineerUseCase.getEngineerOptions(eng);
				} catch (ComplainException | EngineerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
			
		}else if(n==3) {
			
			System.out.println("Enter UserName ");
			String username = sc.next();
			System.out.println("Enter Password ");
			String password = sc.next();
			Employee emp=null;
			emp = EmployeeDao.checkEmployee(username, password);
			if(emp!=null)
				try {
					EmployeeUseCase.getEmployeeOptions(emp);
				} catch (ComplainException | EmployeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}else if(n==4) {
			System.out.println("Enter UserName To Register");
			String username = sc.next();
			System.out.println("Enter Password To Register ");
			String password = sc.next();
			String res=null;
			try {
				res = EmployeeDao.regEmployee(username, password);
				System.out.println(res);
				homepage();
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		}
		else if(n==5) {
			System.out.println("Have A Great Day ");
			System.exit(0);
			
		}
		else {
			System.out.println("Invalid Input");		
			}
		
		
	}
	

	public static void main(String[] args) {
		
		try {
			homepage();
		} catch (HODException | EngineerException | EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}