package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import bean.Appointment;
import bean.Availability;
import bean.Doctor;
import bean.Patient;
import persistence.Appointmentdao;
import persistence.Availabilitydao;
import persistence.Doctordao;
import persistence.Patientdao;

public class PatientService {

	static Scanner sc=new Scanner(System.in);

    public boolean patientRegister(Patient patient) throws Exception {
    	Patientdao patientdao=new Patientdao();
		boolean check=patientdao.signuppatient(patient);
		if(check==true) {
			return false;}
		else {
			return true;
		}
	}
    
    
    public int patientLogin(String pEmail,String pPassword) throws Exception{

		Patientdao patientdao=new Patientdao();
		Patient patient=patientdao.getpatRecordByID(pEmail);

		if((patient.getpPassword()).equals(pPassword)) {
			return patient.getPid();
		}
		else {
			return -1;
		}
	}
      
    
    public ArrayList<Appointment> view_appointments(int pid) throws Exception{

		Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbypid(pid);
		return appointments;

    }
    
    public boolean add_appointment(Appointment appointment)throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();

		
		boolean check=appointmentdao.add_appointment(appointment);
		if(check==true) {
			return true;}
		else {
			return false;
		}
    }
    
    public boolean cancel_appointments(int pid,int aid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		ArrayList<Integer>aplist=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbypid(pid);
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
    

}
