package helper;

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

public class PatientHelper {

	static Scanner sc=new Scanner(System.in);

    public boolean patientRegister() throws Exception {
    	
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
		String pEmail=sc.next();
		System.out.println();
		
		System.out.println("Enter Password:");
		String pPassword=sc.next();
		System.out.println();

		Patientdao patientdao=new Patientdao();
				
		Patient p1=new Patient();
		
		p1.setpAge(pAge);p1.setpEmail(pEmail);p1.setpGender(pGender);p1.setpMobile(pMobile);p1.setpName(pName);p1.setpPassword(pPassword);
		boolean check=patientdao.signuppatient(p1);
		if(check==true) {
			System.out.println("Successfully registered!");return false;}
		else {
			System.out.println("Could not register!");
			return true;
		}
	}
    
    
    public int patientLogin() throws Exception{
		System.out.println("\n*------------------------------Patient Login------------------------------*");
		System.out.println("Enter Email_ID:");	
		String pEmail=sc.next();
		System.out.println();
//		doccid=dID;
		System.out.println("Enter Password:");
		String pPassword=sc.next();
		System.out.println();
		
		
		Patientdao patientdao=new Patientdao();
		Patient patient=patientdao.getpatRecordByID(pEmail);
		if((patient.getpPassword()).equals(pPassword)) {
			System.out.println("Patient login is successful..\n\n*** Welcome to the Online Appointment "+ patient.getpName() + " !!");
			return patient.getPid();
		}
		else {
				System.out.println("!!! Password incorrect.. Try again. !!!");
				patientLogin();
				return -1;
			}
	}
    
    public void patgetavail() throws ClassNotFoundException, SQLException, IOException{
        
    	Availabilitydao availabilitydao=new Availabilitydao();
    	ArrayList<Availability>availabilities=availabilitydao.viewAvailability();
		System.out.println("\n+---------+-----------+---------+--------+");
		System.out.println("|Doctor id"+"|"+"Date"+ "       |"+"In Time"+ "  |"+"Out Time|");
		System.out.println("+---------+-----------+---------+--------+");
		for(Availability availability:availabilities) {
		
			System.out.println("|"+availability.getDoctorId()+"      |"+availability.getDoctorDate()+ " |"+availability.getInTime()+ " |"+availability.getOutTime()+"|");
		}
		System.out.println("+---------+-----------+---------+--------+\n");
	}
    
    
    public void view_appointments(int pid) throws Exception{
		System.out.println("The appointments that have been assigned to you are:");
		System.out.println("\n+--------------+----------+---------+----------+---------+");
		System.out.println("|Appointment_Id|Doctor_Id|Patient_Id|Date      |Time     |");
		System.out.println("+--------------+----------+---------+----------+---------+");
		Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbypid(pid);
		for(Appointment appointment:appointments) {
			System.out.println("|"+appointment.getaID()+"             |"+appointment.getDoctorId()+ "         |"+appointment.getPatientID()+ "      |"+appointment.getaDate()+"|"+appointment.getaTime()+" |");
			System.out.println("+--------------+----------+---------+----------+---------+\n");
		}
    }
    
    public boolean add_appointment(int pid)throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
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
		
		boolean check=appointmentdao.add_appointment(appointment);
		if(check==true) {
			System.out.println("Successfully added!");return false;}
		else {
			System.out.println("Could not add!");
			return true;
		}
    }
    
    public void cancel_appointments(int pid) throws Exception{
    	Appointmentdao appointmentdao=new Appointmentdao();
		ArrayList<Appointment>appointments=new ArrayList<>();
		ArrayList<Integer>aplist=new ArrayList<>();
		appointments=appointmentdao.viewAppointmentbypid(pid);
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
		System.out.println("Enter the option:");
		System.out.println("1) View my appointments \n2) Add an appointment\n3) Remove an appointment\n4) Exit");
		System.out.print("Enter your choice: ");
    	System.out.println();
    }
}
