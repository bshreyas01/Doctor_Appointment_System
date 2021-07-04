package helper;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import bean.*;
import persistence.*;

public class DoctorHelper {
	static Scanner sc=new Scanner(System.in);

    public boolean doctorRegister() throws Exception {
    	
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
		String dPassword=sc.next();
		System.out.println();

		Doctordao doctordao=new Doctordao();
		Doctor d1=new Doctor();
		d1.setdAge(dAge);
		d1.setdEmail(dEmail);d1.setdGender(dGender);d1.setDid(did);d1.setdMobile(dMobile);d1.setdName(dName);d1.setdPassword(dPassword);
		boolean check=doctordao.signupdoctor(d1);
		if(check==true) {
			System.out.println("Successfully registered!");return false;}
		else {
			System.out.println("Could not register!");
			return true;
		}
	}
    
    
    public int doctorLogin() throws Exception{
		System.out.println("\n*------------------------------Doctor Login------------------------------*");
		System.out.println("Enter Doctor_ID:");	
		int dID=sc.nextInt();
		System.out.println();
//		doccid=dID;
		System.out.println("Enter Password:");
		String dPassword=sc.next();
		System.out.println();
		
		
		Doctordao doctordao=new Doctordao();
		Doctor doctor=doctordao.getRecordBydID(dID);
		if((doctor.getdPassword()).equals(dPassword)) {
				System.out.println("Doctor login is successful..\n\n*** Welcome to the Online Appointment "+ doctor.getdName() + " !!");
				return dID;
		}
		else {
				System.out.println("!!! Password incorrect.. Try again. !!!");
				doctorLogin();
			}
		return -1;
	}
    
    public void doctordashboard(){
        try {
        	Availabilitydao availabilitydao=new Availabilitydao();
        	ArrayList<Availability>availabilities=availabilitydao.viewAvailability();
    		System.out.println("\n+---------+-----------+---------+--------+");
    		System.out.println("|Doctor id"+"|"+"Date"+ "       |"+"In Time"+ "  |"+"Out Time|");
    		System.out.println("+---------+-----------+---------+--------+");
    		for(Availability availability:availabilities) {
    		
    			System.out.println("|"+availability.getDoctorId()+"      |"+availability.getDoctorDate()+ " |"+availability.getInTime()+ " |"+availability.getOutTime()+"|");
    			System.out.println("+---------+-----------+---------+--------+\n");
    		}
        }
        catch(Exception e) {
        	System.out.println("SQL error");
        }
    }
    
    public void view_appointments(int did) throws Exception{
		System.out.println("The appointments that have been assigned to you are:");
		System.out.println("\n+--------------+----------+---------+----------+---------+");
		System.out.println("|Appointment_Id|Doctor_Id|Patient_Id|Date      |Time     |");
		System.out.println("+--------------+----------+---------+----------+---------+");
		Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbydid(did);
		for(Appointment appointment:appointments) {
			System.out.println("|"+appointment.getaID()+"             |"+appointment.getDoctorId()+ "         |"+appointment.getPatientID()+ "      |"+appointment.getaDate()+"|"+appointment.getaTime()+" |");
			System.out.println("+--------------+----------+---------+----------+---------+\n");
		}
    }
    
    public void set_availability(int did) throws Exception{
    	
		System.out.println("\nEnter the Availability_Date(YYYY-MM-DD) and inTime(HH:MM:SS) and OutTime:");
//		String dname = doccname;
		String date=sc.next();
		String iT=sc.next();
		String oT=sc.next();
		Availabilitydao availabilitydao=new Availabilitydao();
		Availability availability=new Availability();
		availability.setDoctorDate(date);
		availability.setDoctorId(did);
		availability.setInTime(iT);
		availability.setOutTime(oT);
		boolean check=availabilitydao.setavailability(availability);
		if(check==true)
			System.out.println("--> Successfully added your availability. <--\n");
		else {
			System.out.println("Could not add!");
		}
    }
    
    public void cancel_appointments(int did) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		ArrayList<Integer>aplist=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbydid(did);
		for(Appointment appointment:appointments) {
			aplist.add(appointment.getaID());
		}
		System.out.println("Enter the appointment Id:");
		int aid=sc.nextInt();
		if (aplist.contains(aid)) {
			boolean check=appointmentdao.cancel_appointment(aid);
			if (check) {
				System.out.println("Cancelled!");
			}
			else {
				System.out.println("Not possible!");
			}
		}
		else {
			System.out.println("Not possible!");
		}
	}
    
    public void menuopt() {
    	System.out.println("**Enter your option:\n1) View Appointments  \n2) Set Availability \n3) Cancel Appointment\n4) Exit");
    	System.out.print("Enter your choice: ");
    	System.out.println();
    }
    
//	public static void select_option(int did, int case_opt)throws Exception{
//		switch(case_opt) {
//		case 1:view_appointments(did);break;
//		case 2:set_availability(did);break;
//		case 3:cancel_appointments(did);break;
//		default: System.out.println("**Successfully logged out..\n"); 
//		}
//	}



    
}
