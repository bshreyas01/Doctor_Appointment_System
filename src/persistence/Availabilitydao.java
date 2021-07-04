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

public class Availabilitydao {
	
	public boolean setavailability(Availability availability) throws SQLException, ClassNotFoundException,ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement s1=connection.prepareStatement("INSERT into AvailabilityTable values(?,?,?,?)");
		s1.setInt(4, availability.getDoctorId());
		s1.setDate(1, java.sql.Date.valueOf(availability.getDoctorDate()));
		s1.setTime(2,java.sql.Time.valueOf(availability.getInTime()));
		s1.setTime(3, java.sql.Time.valueOf(availability.getOutTime()));
		
		int r1=s1.executeUpdate();
		connection.close();
		if(r1>0) {
			return true;
		}
		else
			return false;
		
	}
	
	public ArrayList<Availability> viewAvailabilitybydid(int DoctorId)
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		ArrayList<Availability>availabilities=new ArrayList<>();
		Connection con = MySQLConnection.getConnection();
		Statement st=con.createStatement();
		String query= "select * from AvailabilityTable where DoctorId = " + DoctorId +  " order by DoctorDate, InTime";
		ResultSet r1 = st.executeQuery(query);
		
		while(r1.next()) {
			Availability availability=new Availability();
			availability.setDoctorId(r1.getInt("DoctorId"));
			availability.setDoctorDate(r1.getString("DoctorDate"));
			availability.setInTime(r1.getString("InTime"));
			availability.setOutTime(r1.getString("OutTime"));
			availabilities.add(availability);
		}
		con.close();
		return availabilities;
	}
	
	public ArrayList<Availability> viewAvailability()
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		ArrayList<Availability>availabilities=new ArrayList<>();
		Connection con = MySQLConnection.getConnection();
		Statement st=con.createStatement();
		String query= "select * from AvailabilityTable order by DoctorDate, InTime";
		ResultSet r1 = st.executeQuery(query);
		
		while(r1.next()) {
			Availability availability=new Availability();
			availability.setDoctorId(r1.getInt("DoctorId"));
			availability.setDoctorDate(r1.getString("DoctorDate"));
			availability.setInTime(r1.getString("InTime"));
			availability.setOutTime(r1.getString("OutTime"));
			availabilities.add(availability);
		}
		con.close();
		return availabilities;
	}
}
