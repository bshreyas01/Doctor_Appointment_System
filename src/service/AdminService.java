package service;
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

public class AdminService {
	static Scanner sc=new Scanner(System.in);

   
    
    public boolean adminLogin(int aID, String aPassword) throws Exception{
		Admindao admindao=new Admindao();
		Admin admin=admindao.getRecordByaID(aID);
		if((admin.getPassword()).equals(aPassword)) {
				return true;
		}
		else
			return false;
	}
    
    public ArrayList<Availability> adminavailabilities() throws ClassNotFoundException, SQLException, IOException{
        	Availabilitydao availabilitydao=new Availabilitydao();
        	ArrayList<Availability>availabilities=availabilitydao.viewAvailability();
        	return availabilities;
    }
    public ArrayList<Appointment> adminappointments() throws ClassNotFoundException, SQLException, IOException{
    	
    	Appointmentdao appointmentdao=new Appointmentdao();
    	ArrayList<Appointment>appointments=appointmentdao.viewallAppointments();

		return appointments;
    }
    
    
    public ArrayList<Appointment> view_appointments(int did) throws Exception{

		Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbydid(did);
		return appointments;

    }

    
    public boolean cancel_appointments(int aid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
			boolean check=appointmentdao.cancel_appointment(aid);
			if (check==true) {
				return true;
			}
			else {
				return false;
			}
	
	}
    
    public boolean modify_appointments(int aid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
		System.out.println("Enter the doctor Id:");
		int did=sc.nextInt();
			boolean check=appointmentdao.modify_appointment(aid,did);
			if (check==true) {
				return true;
			}
			else {
				return false;
			}
	
		}
    
    public ArrayList<Availability> availbydoc(int did) throws Exception {
    	Availabilitydao availabilitydao=new Availabilitydao();
    	ArrayList<Availability>availbydid= availabilitydao.viewAvailabilitybydid(did);
    	return availbydid;
    	
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
