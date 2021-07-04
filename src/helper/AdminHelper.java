package helper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import bean.*;
import persistence.*;

public class AdminHelper {
	static Scanner sc=new Scanner(System.in);

   
    
    public boolean adminLogin() throws Exception{
		System.out.println("\n*------------------------------Admin Login------------------------------*");
		System.out.println("Enter Admin Id:");	
		int aID=sc.nextInt();
		System.out.println();
//		doccid=dID;
		System.out.println("Enter Password:");
		String aPassword=sc.next();
		System.out.println();
		
		
		Admindao admindao=new Admindao();
		Admin admin=admindao.getRecordByaID(aID);
		if((admin.getPassword()).equals(aPassword)) {
				System.out.println("Admin login is successful..\n\n*** Welcome to the Online Appointment "+ admin.getAdminName() + " !!");
				return true;
		}
		else {
				System.out.println("!!! Password incorrect.. Try again. !!!");
				adminLogin();
			}
		return false;
	}
    
    public void admindashboard() throws ClassNotFoundException, SQLException, IOException{
        
        	Availabilitydao availabilitydao=new Availabilitydao();
        	Appointmentdao appointmentdao=new Appointmentdao();
        	ArrayList<Availability>availabilities=availabilitydao.viewAvailability();
    		System.out.println("\n+---------+-----------+---------+--------+");
    		System.out.println("|Doctor id"+"|"+"Date"+ "       |"+"In Time"+ "  |"+"Out Time|");
    		System.out.println("+---------+-----------+---------+--------+");
    		for(Availability availability:availabilities) {
    		
    			System.out.println("|"+availability.getDoctorId()+"      |"+availability.getDoctorDate()+ " |"+availability.getInTime()+ " |"+availability.getOutTime()+"|");
    		}
			System.out.println("+---------+-----------+---------+--------+\n");

        	ArrayList<Appointment>appointments=appointmentdao.viewallAppointments();
        	System.out.println("***List of all the Appointments..");
    		System.out.println("+--------------+----------+---------+----------+---------+");
    		System.out.println("|Appointment Id|Patient Id|Doctor Id|   Date   |   Time  |");
    		System.out.println("+--------------+----------+---------+----------+---------+");
    		for(Appointment appointment:appointments) {
    			System.out.println("|"+appointment.getaID()+"             |"+appointment.getPatientID()+ "       |"+appointment.getDoctorId()+ "        |"+appointment.getaDate()+"|"+appointment.getaTime()+" |");
    		}
			System.out.println("+--------------+----------+---------+----------+---------+\n");
    		

    }
    
    
    public void view_appointments(int did) throws Exception{
		System.out.println("The appointments that have been assigned to the doctor are:");
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
    public void view_all_appointments() throws Exception{
		System.out.println("The appointments are:");
		System.out.println("\n+--------------+----------+---------+----------+---------+");
		System.out.println("|Appointment_Id|Doctor_Id|Patient_Id|Date      |Time     |");
		System.out.println("+--------------+----------+---------+----------+---------+");
		Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		appointments=appointmentdao.viewallAppointments();
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
//		Availability availability=new Availability(date, iT, oT, did);
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
    
    public void cancel_appointments(int aid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
			boolean check=appointmentdao.cancel_appointment(aid);
			if (check) {
				System.out.println("Cancelled!");
			}
			else {
				System.out.println("Not possible!");
			}
	
	}
    
    public void modify_appointments(int aid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
		System.out.println("Enter the doctor Id:");
		int did=sc.nextInt();
			boolean check=appointmentdao.modify_appointment(aid,did);
			if (check) {
				System.out.println("Modified!");
			}
			else {
				System.out.println("Not possible!");
			}
		}
    
    public void availbydoc(int did) throws Exception {
    	Availabilitydao availabilitydao=new Availabilitydao();
    	ArrayList<Availability>availbydid= availabilitydao.viewAvailabilitybydid(did);
    	System.out.println("+---------+-----------+---------+--------+");
		System.out.println("|Doctor id"+"|"+"Date"+ "       |"+"In Time"+ "  |"+"Out Time|");
		System.out.println("+---------+-----------+---------+--------+");
		for (Availability availability : availbydid) {
			System.out.println("|"+availability.getDoctorId()+"        |"+availability.getDoctorDate()+ " |"+availability.getInTime()+ " |"+availability.getOutTime()+"|");
		}
		System.out.println("+---------+-----------+---------+--------+\n");
    	
    }

	
    
    

    
//	public static void select_option(int did, int case_opt)throws Exception{
//		switch(case_opt) {
//		
//		case 1:view_appointments(did);break;
//		case 2:cancel_appointments(did);break;
//		case 3:modify_appointments(did);break;
//		default: System.out.println("**Successfully logged out..\n"); 
//		}
//	}



    
}
