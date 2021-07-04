package persistence;
import bean.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.sql.*;
import helper.MySQLConnection;

public class Appointmentdao {
	
	public ArrayList<Appointment> viewAppointmentbydid(int DoctorId)
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		ArrayList<Appointment>appointments=new ArrayList<>();
		Connection con = MySQLConnection.getConnection();

		PreparedStatement s1=con.prepareStatement("Select * from AppointmentTable WHERE DoctorId=?");
		s1.setInt(1, DoctorId);
		ResultSet r1=s1.executeQuery();
		
		while(r1.next()) {
			Appointment appointment=new Appointment();
			appointment.setaID(r1.getInt("aID"));
			appointment.setaDate(r1.getString("aDate"));
			appointment.setaTime(r1.getString("aTime"));
			appointment.setDoctorId(r1.getInt("DoctorId"));
			appointment.setPatientID(r1.getInt("PatientID"));
			appointments.add(appointment);
		}
		con.close();
		return appointments;
	}
	
	public boolean cancel_appointment(int aID) 
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		Connection con = MySQLConnection.getConnection();
		PreparedStatement sta=con.prepareStatement("Delete from AppointmentTable where aid=?");
		sta.setInt(1, aID);
		int i=sta.executeUpdate();
		if(i>0) {
			return true;
		}
		else return false;
	}
	
	public ArrayList<Appointment> viewAppointmentbypid(int patientID)
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		ArrayList<Appointment>appointments=new ArrayList<>();
		Connection con = MySQLConnection.getConnection();

		PreparedStatement s1=con.prepareStatement("Select * from AppointmentTable WHERE patientID=?");
		s1.setInt(1, patientID);
		ResultSet r1=s1.executeQuery();
		
		while(r1.next()) {
			Appointment appointment=new Appointment();
			appointment.setaID(r1.getInt("aID"));
			appointment.setaDate(r1.getString("aDate"));
			appointment.setaTime(r1.getString("aTime"));
			appointment.setDoctorId(r1.getInt("DoctorId"));
			appointment.setPatientID(r1.getInt("PatientID"));
			appointments.add(appointment);
		}
		con.close();
		return appointments;
	}
	
	public boolean add_appointment(Appointment a1) throws SQLException, ClassNotFoundException,ClassNotFoundException, IOException {
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement s=connection.prepareStatement("INSERT INTO AppointmentTable(PatientId, DoctorId, aDate, aTime) VALUES(?,?,?,?)");
		s.setInt(1, a1.getPatientID());
		s.setInt(2, a1.getDoctorId());
		s.setDate(3,java.sql.Date.valueOf(a1.getaDate()));
		s.setTime(4, java.sql.Time.valueOf(a1.getaTime()));
		int rows = s.executeUpdate();
		if (rows > 0)
			return true;
		connection.close();
		return false;
	}
	
	public ArrayList<Appointment> viewallAppointments()
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		ArrayList<Appointment>appointments=new ArrayList<>();
		Connection con = MySQLConnection.getConnection();

		PreparedStatement s1=con.prepareStatement("Select * from AppointmentTable order by aDate, aTime");
		ResultSet r1=s1.executeQuery();
		
		while(r1.next()) {
			Appointment appointment=new Appointment();
			appointment.setaID(r1.getInt("aID"));
			appointment.setaDate(r1.getString("aDate"));
			appointment.setaTime(r1.getString("aTime"));
			appointment.setDoctorId(r1.getInt("DoctorId"));
			appointment.setPatientID(r1.getInt("PatientID"));
			appointments.add(appointment);
		}
		con.close();
		return appointments;
	}
	
	public boolean modify_appointment(int appointmentId,int docid) throws SQLException, ClassNotFoundException,ClassNotFoundException, IOException {
		Connection connection = MySQLConnection.getConnection();
    	PreparedStatement s2=connection.prepareStatement("UPDATE AppointmentTable SET DoctorId=? where aId=?");
    	s2.setInt(1, docid);
    	s2.setInt(2, appointmentId);
		int rows = s2.executeUpdate();
		if (rows > 0)
			return true;
		connection.close();
		return false;
	}
	
	

}


