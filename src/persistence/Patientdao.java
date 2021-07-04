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

public class Patientdao {
	
	
	public boolean signuppatient(Patient p1) throws SQLException, ClassNotFoundException,ClassNotFoundException, IOException {
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement s=connection.prepareStatement("INSERT INTO PatientTable(PatientName, PatientGender, PatientAge, PatientMobile, PatientEmail, PatientPassword) VALUES(?,?,?,?,?,?)");
		s.setString(1, p1.getpName());
		s.setString(2,p1.getpGender());
		s.setInt(3, p1.getpAge());
		s.setString(4,p1.getpMobile());
		s.setString(5, p1.getpEmail());
		s.setString(6, p1.getpPassword());
		int rows = s.executeUpdate();
		if (rows > 0)
			return true;
		connection.close();
		return false;
	}
	
	public Patient getpatRecordByID(String email)
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from PatientTable where PatientEmail=(?)");
		statement.setString(1, email);
		ResultSet resultset = statement.executeQuery();
		Patient pat = new Patient();
		if (resultset.next()) {
			pat = new Patient();
			pat.setpName(resultset.getString("PatientName"));
			pat.setPid(resultset.getInt("PatientID"));
			pat.setpGender(resultset.getString("PatientGender"));
			pat.setpAge(resultset.getInt("PatientAge"));
			pat.setpMobile(resultset.getString("PatientMobile"));
			pat.setpEmail(resultset.getString("PatientEmail"));
			pat.setpPassword(resultset.	getString("PatientPassword"));
		}
		connection.close();
		return pat;
	}

}