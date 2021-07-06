package presentation;
import bean.*;
import client.*;
import persistence.*;
import service.AdminService;
import service.DoctorService;
import service.PatientService;
import helper.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Applpresentation {
	static Scanner sc=new Scanner(System.in);

    public static void adminStart() throws Exception {
		boolean loop=true;
		boolean loop2=true;
		int c1;
		while(loop) {
		System.out.println("***------------------------------Welcome to the Admin page------------------------------***");
		System.out.println("Enter the option: \n1) Login \n2) Exit");
		System.out.print("Enter your choice: ");
		c1=sc.nextInt();
		System.out.println();
		switch(c1) {
		case 1:
			AdminService adminService=new AdminService();
			System.out.println("\n*------------------------------Admin Login------------------------------*");
			System.out.println("Enter Admin Id:");	
			int aID=sc.nextInt();
			System.out.println();
//			doccid=dID;
			System.out.println("Enter Password:");
			String aPassword=sc.next();
			System.out.println();
			loop2=adminService.adminLogin(aID,aPassword);
			if(loop2) {
			System.out.println("Admin login is successful..\n\n*** Welcome to the Online Appointment !!");
			}
			while(loop2==false) {
				System.out.println("!!! Password incorrect.. Try again. !!!");
				loop2=adminService.adminLogin(aID,aPassword);
			}
			while(loop2) {
				System.out.println("+--------------+--------Dashboard----------+---------+\r\n"						+ "");
				Reusable.viewallavailabilities();
				Reusable.view_all_appointments();
				ArrayList<Appointment>appointments;

				
				System.out.println("Enter the option: \n1) View by doctor id \n2) Remove appointment \n3) Modify appointment \n4) Exit");
				System.out.print("Enter your choice: ");
				int c2=sc.nextInt();
				System.out.println();
				switch(c2) {
					case 1:
						System.out.println("***View by doctor ID..");
						System.out.println("Enter the doctor's id:");
						int did=sc.nextInt();

				    	System.out.println("+---------+-----------+---------+--------+");
						System.out.println("|Doctor id"+"|"+"Date"+ "       |"+"In Time"+ "  |"+"Out Time|");
						System.out.println("+---------+-----------+---------+--------+");
						ArrayList<Availability>availbydid=adminService.availbydoc(did);
						for (Availability availability : availbydid) {
							System.out.println("|"+availability.getDoctorId()+"        |"+availability.getDoctorDate()+ " |"+availability.getInTime()+ " |"+availability.getOutTime()+"|");
						}
						System.out.println("+---------+-----------+---------+--------+\n");

						System.out.println("The appointments that have been assigned to the doctor are:");
						System.out.println("\n+--------------+----------+---------+----------+---------+");
						System.out.println("|Appointment_Id|Doctor_Id|Patient_Id|Date      |Time     |");
						System.out.println("+--------------+----------+---------+----------+---------+");
						appointments=adminService.view_appointments(did);
						for(Appointment appointment:appointments) {
							System.out.println("|"+appointment.getaID()+"             |"+appointment.getDoctorId()+ "         |"+appointment.getPatientID()+ "      |"+appointment.getaDate()+"|"+appointment.getaTime()+" |");
							System.out.println("+--------------+----------+---------+----------+---------+\n");
						}

						break;
					case 2:
						System.out.println("***Remove appointment..");
						Reusable.view_all_appointments();
						System.out.print("Enter the appointment id: ");
						int ad=sc.nextInt();
						System.out.println();
						boolean flag= adminService.cancel_appointments(ad);
						if (flag==true)
							System.out.println("Cancelled!");
						else {
							System.out.println("Not possible!");
						}
						break;
					case 3:
						System.out.println("***Modify appointment..");
						Reusable.view_all_appointments();
						System.out.print("Enter the appointment id: ");
						int ad1=sc.nextInt();
						System.out.println();
						flag=adminService.modify_appointments(ad1);
						if (flag==true)
							System.out.println("Modified!");
						else {
							System.out.println("Not possible!");
						}
						break;
					case 4: 
						loop2=false;
						break;
				}
			}
			break;
		case 2:
			loop=false;
			System.out.println("--> Successfully logged out. <--\n");
			break;
			}
		}
    }
	
	public static void docStart() throws Exception {
		boolean loop=true;
		boolean loop2=true;
		int c1;
		while(loop) {
		System.out.println("*---------------Doctor login/ Registration------------------*");
		System.out.println(" 1) New doctor registration");
		System.out.println(" 2) Existing doctor login\n 3) Exit");
		System.out.print("Enter your choice: ");
		c1=sc.nextInt();
		System.out.println();
		switch(c1) {
		case 2:
			DoctorService doctorService=new DoctorService();

			System.out.println("\n*------------------------------Doctor Login------------------------------*");
			System.out.println("Enter Doctor_ID:");	
			int dID=sc.nextInt();
			System.out.println();
//			doccid=dID;
			System.out.println("Enter Password:");
			String dPassword=sc.next();
			System.out.println();
			
			dID=doctorService.doctorLogin(dID,dPassword);
			if (dID!=-1) {		
				System.out.println("Doctor login is successful..\n\n*** Welcome to the Online Appointment!!");


			  while(loop2) {
				System.out.println("+--------------+--------Dashboard----------+---------+\r\n"						+ "");
				Reusable.viewallavailabilities();
				menuoptdoc();
				int choice =sc.nextInt();
				switch(choice) {

					case 1:
						System.out.println("The appointments that have been assigned to you are:");
						System.out.println("\n+--------------+----------+---------+----------+---------+");
						System.out.println("|Appointment_Id|Doctor_Id|Patient_Id|Date      |Time     |");
						System.out.println("+--------------+----------+---------+----------+---------+");
						ArrayList<Appointment> appointments= doctorService.view_appointments(dID);
						for(Appointment appointment:appointments) {
							System.out.println("|"+appointment.getaID()+"             |"+appointment.getDoctorId()+ "         |"+appointment.getPatientID()+ "      |"+appointment.getaDate()+"|"+appointment.getaTime()+" |");
							System.out.println("+--------------+----------+---------+----------+---------+\n");
						}
						menuoptdoc();
						break;
					case 2:
						
						System.out.println("\nEnter the Availability_Date(YYYY-MM-DD) and inTime(HH:MM:SS) and OutTime:");
//						String dname = doccname;
						String date=sc.next();
						String iT=sc.next();
						String oT=sc.next();
						Availability availability=new Availability();
						availability.setDoctorDate(date);
						availability.setDoctorId(dID);
						availability.setInTime(iT);
						availability.setOutTime(oT);
						boolean flag= doctorService.set_availability(availability);
						if(flag==true) {
							System.out.println("--> Successfully added your availability. <--\n");
						}
						else {
							System.out.println("Could not add!");
						}
						menuoptdoc();
						break;
					case 3:
						System.out.println("Enter the appointment Id:");
						int aid=sc.nextInt();
						boolean check= doctorService.cancel_appointments(dID,aid);
						if (check==true) {
							System.out.println("Cancelled!");
						}
						else {
							System.out.println("Not possible!");
						}
						menuoptdoc();
						break;
					default: 
						loop2=false;
						break;
				}
			  }
			}
			break;
		case 1:
			boolean flag=true;
			while(flag) {
				DoctorService doctorService1=new DoctorService();
		    	
				System.out.println("\n*------------------------------New doctor Registration------------------------------*");
				System.out.println("Enter doctor Id:");
				int did=sc.nextInt();
				System.out.println("Enter doctorName:");
				String dName=sc.next();
				System.out.println();

				System.out.println("Enter doctor Gender:");
				String dGender=sc.next();
				System.out.println();
				
				System.out.println("Enter doctor Age:");
				int dAge=sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				System.out.println("Enter doctor Mobile Number:");
				String dMobile=sc.next();
				System.out.println();
				
				System.out.println("Enter doctor email ID:");
				String dEmail=sc.next();
				System.out.println();
				
				System.out.println("Enter Password:");
				String dPassword1=sc.next();
				System.out.println();
				
				Doctor doctor=new Doctor(did, dName, dGender, dAge, dMobile, dEmail, dPassword1);
				flag=doctorService1.doctorRegister(doctor);
				if (flag==true)
					System.out.println("Not possible!!");
				else {
					System.out.println("Registered!");
				}
			}
			break;
		case 3:
			loop=false;
			System.out.println("--> Successfully logged out. <--\n");
			break;
		}
	  }
		
	}
	
	public static void patientStart() throws Exception {
		boolean loop=true;
		boolean loop2=true;
		int c1;
		while(loop) {
			System.out.println("---------------Patient login/ Registration------------------");
			System.out.println(" 1) New user registration");
			System.out.println(" 2) Existing user/login\n 3) Exit");
			System.out.println("Enter Your choice:");
		c1=sc.nextInt();
		System.out.println();
		switch(c1) {
		case 2:
			PatientService patientService=new PatientService();

			System.out.println("\n*------------------------------Patient Login------------------------------*");
			System.out.println("Enter Email_ID:");	
			String pEmail=sc.next();
			System.out.println();
//			doccid=dID;
			System.out.println("Enter Password:");
			String pPassword=sc.next();
			System.out.println();
			int pid=patientService.patientLogin(pEmail,pPassword);
			if (pid!=-1) {				

			  while(loop2) {
				System.out.println("+--------------+--------Dashboard----------+---------+\r\n"						+ "");
				menuoptpat();
				int choice =sc.nextInt();
				switch(choice) {
					case 1:
						
						System.out.println("The appointments that have been assigned to you are:");
						System.out.println("\n+--------------+----------+---------+----------+---------+");
						System.out.println("|Appointment_Id|Doctor_Id|Patient_Id|Date      |Time     |");
						System.out.println("+--------------+----------+---------+----------+---------+");
						
						ArrayList<Appointment>appointments=patientService.view_appointments(pid);
						for(Appointment appointment:appointments) {
							System.out.println("|"+appointment.getaID()+"             |"+appointment.getDoctorId()+ "         |"+appointment.getPatientID()+ "      |"+appointment.getaDate()+"|"+appointment.getaTime()+" |");
							System.out.println("+--------------+----------+---------+----------+---------+\n");
						}
						menuoptpat();
						break;
					case 2:
						Reusable.viewallavailabilities();
						
				    	Appointment appointment=new Appointment();
						System.out.println("Enter the Doctor Id:");
						int did = sc.nextInt();
						System.out.println("Enter your Appointment date(yyyy-mm-dd):");
						String ad = sc.next();
						System.out.println("Enter your Appointment time(hh:mm:ss):");
						String at = sc.next();
						appointment.setDoctorId(did);
						appointment.setPatientID(pid);
						appointment.setaDate(ad);
						appointment.setaTime(at);
						
						boolean flag=true;

						flag=patientService.add_appointment(appointment);
						if(flag==true) {
							System.out.println("Successfully added!");}
						else {
							System.out.println("Could not add!");
						}
						menuoptpat();
						break;
					case 3:
						System.out.println("Enter the appointment Id:");
						int aid=sc.nextInt();
						boolean check= patientService.cancel_appointments(pid,aid);
						if (check==true) {
							System.out.println("Cancelled!");
						}
						else {
							System.out.println("Not possible!");
						}
						menuoptpat();
						break;
					default: 
						loop2=false;
						break;
				}
			  }
			}
			break;
		case 1:
			boolean flag=true;
			while(flag) {	    	
			   	PatientService patientService1=new PatientService();
				System.out.println("\n*------------------------------New Patient Registration------------------------------*");

				System.out.println("Enter Patient Name:");
				String pName=sc.next();
				System.out.println();

				System.out.println("Enter Patient Gender:");
				String pGender=sc.next();
				System.out.println();
				
				System.out.println("Enter Patient Age:");
				int pAge=sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				System.out.println("Enter Patient Mobile Number:");
				String pMobile=sc.next();
				System.out.println();
				
				System.out.println("Enter Patient email ID:");
				String pEmail1=sc.next();
				System.out.println();
				
				System.out.println("Enter Password:");
				String pPassword1=sc.next();
				System.out.println();
				
				Patient patient=new Patient(pName, pGender, pAge, pMobile, pEmail1, pPassword1);
				flag=patientService1.patientRegister(patient);
				if (flag==true)
					System.out.println("Not possible!!");
				else {
					System.out.println("Registered!");
				}
			}
			break;
		case 3:
			loop=false;
			System.out.println("--> Successfully logged out. <--\n");
			break;
		}
	  }
		
	}

	public static void startappmenu() {
			System.out.println("***---------------------Welcome to the Online Doctor Appointment---------------------***");
			System.out.println("**Enter the type of User:");
			System.out.println("1) Admin\n2) Doctor\n3) Patient\n4) Exit");
	}
	
	public static void performmenu() throws Exception {
			Scanner in = new Scanner(System.in);
			System.out.print("\n**Enter your choice: ");
			int user = in.nextInt();
			System.out.println();
			switch(user) {
			case 1:
				adminStart();
				break;
			case 2:
				docStart();
				break;
			case 3:
				patientStart();
				break;
			case 4:
				System.out.println("******Thanks for using our Online Doctor Appointment service******");
				in.close();break;
			}
			return;
	}
	
    public static void menuoptdoc() {
    	System.out.println("**Enter your option:\n1) View Appointments  \n2) Set Availability \n3) Cancel Appointment\n4) Exit");
    	System.out.print("Enter your choice: ");
    	System.out.println();
    }
    
    public static void menuoptpat() {
		System.out.println("Enter the option:");
		System.out.println("1) View my appointments \n2) Add an appointment\n3) Remove an appointment\n4) Exit");
		System.out.print("Enter your choice: ");
    	System.out.println();
    }

}
