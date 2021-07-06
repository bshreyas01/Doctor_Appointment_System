package service;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import bean.*;
import persistence.*;

public class DoctorService {
	static Scanner sc=new Scanner(System.in);

    public boolean doctorRegister(Doctor d1) throws Exception {


		Doctordao doctordao=new Doctordao();
		boolean check=doctordao.signupdoctor(d1);
		if(check==true) {return false;}
		else {
			return true;
		}
	}
    
    
    public int doctorLogin(int dID, String dPassword) throws Exception{

		Doctordao doctordao=new Doctordao();
		Doctor doctor=doctordao.getRecordBydID(dID);
		if((doctor.getdPassword()).equals(dPassword)) {
				return dID;
		}
		else {
			return -1;
			}
		
	}
   
    
    public ArrayList<Appointment> view_appointments(int did) throws Exception{

		Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbydid(did);
		return appointments;
    }
    
    public boolean set_availability(Availability availability) throws Exception{
    	

		Availabilitydao availabilitydao=new Availabilitydao();

		boolean check=availabilitydao.setavailability(availability);
		if(check==true)
			return true;
		else {return false;
		}
    }
    
    public boolean cancel_appointments(int did,int aid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		ArrayList<Integer>aplist=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbydid(did);
		for(Appointment appointment:appointments) {
			aplist.add(appointment.getaID());
		}

		if (aplist.contains(aid)) {
			boolean check=appointmentdao.cancel_appointment(aid);
			if (check) {
				return true;			}
			else {return false;			}
		}
		else {return false;		}
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
