package presentation;
import bean.*;
import persistence.*;
import helper.*;
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
			AdminHelper adminHelper=new AdminHelper();

			loop2=adminHelper.adminLogin();
			while(loop2) {
				System.out.println("+--------------+--------Dashboard----------+---------+\r\n"						+ "");
				adminHelper.admindashboard();
				System.out.println("Enter the option: \n1) View by doctor id \n2) Remove appointment \n3) Modify appointment \n4) Exit");
				System.out.print("Enter your choice: ");
				int c2=sc.nextInt();
				System.out.println();
				switch(c2) {
					case 1:
						System.out.println("***View by doctor ID..");
						System.out.println("Enter the doctor's id:");
						int did=sc.nextInt();
						adminHelper.availbydoc(did);
						break;
					case 2:
						System.out.println("***Remove appointment..");
						adminHelper.view_all_appointments();
						System.out.print("Enter the appointment id: ");
						int ad=sc.nextInt();
						System.out.println();
						adminHelper.cancel_appointments(ad);
						break;
					case 3:
						System.out.println("***Modify appointment..");
						System.out.println("***Remove appointment..");
						adminHelper.view_all_appointments();
						System.out.print("Enter the appointment id: ");
						int ad1=sc.nextInt();
						System.out.println();
						adminHelper.modify_appointments(ad1);
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
			DoctorHelper doctorHelper=new DoctorHelper();

			int dID=doctorHelper.doctorLogin();
			if (dID!=-1) {				

			  while(loop2) {
				System.out.println("+--------------+--------Dashboard----------+---------+\r\n"						+ "");
				doctorHelper.menuopt();
				int choice =sc.nextInt();
				switch(choice) {

					case 1:
						doctorHelper.view_appointments(dID);
						doctorHelper.menuopt();
						break;
					case 2:
						doctorHelper.set_availability(dID);
						doctorHelper.menuopt();
						break;
					case 3:
						doctorHelper.cancel_appointments(dID);
						doctorHelper.menuopt();
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
				DoctorHelper doctorHelper1=new DoctorHelper();
				flag=doctorHelper1.doctorRegister();
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
			PatientHelper patientHelper=new PatientHelper();

			int pid=patientHelper.patientLogin();
			if (pid!=-1) {				

			  while(loop2) {
				System.out.println("+--------------+--------Dashboard----------+---------+\r\n"						+ "");
				patientHelper.menuopt();
				int choice =sc.nextInt();
				switch(choice) {
				/*		System.out.println("Enter the option:");
		System.out.println("1) View my appointments \n2) Add an appointment\n3) Remove an appointment\n4) Exit");
		System.out.print("Enter your choice: ");
    	System.out.println();*/
					case 1:
						patientHelper.view_appointments(pid);
						patientHelper.menuopt();
						break;
					case 2:
						patientHelper.patgetavail();
						boolean flag=true;
						while(flag) {
							flag=patientHelper.add_appointment(pid);
						}
						patientHelper.menuopt();
						break;
					case 3:
						patientHelper.cancel_appointments(pid);
						patientHelper.menuopt();
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
				PatientHelper patientHelper1=new PatientHelper();
				flag=patientHelper1.patientRegister();
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

}
