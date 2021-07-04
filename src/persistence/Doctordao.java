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

public class Doctordao {
	
	
	public boolean signupdoctor(Doctor d1) throws SQLException, ClassNotFoundException,ClassNotFoundException, IOException {
		Connection connection = MySQLConnection.getConnection();
		PreparedStatement s=connection.prepareStatement("INSERT into DoctorTable values(?,?,?,?,?,?,?)");
		s.setInt(1, d1.getDid());
		s.setString(2, d1.getdName());
		s.setString(3,d1.getdGender());
		s.setInt(4, d1.getdAge());
		s.setString(5,d1.getdMobile());
		s.setString(6, d1.getdEmail());
		s.setString(7, d1.getdPassword());
		int rows = s.executeUpdate();
		if (rows > 0)
			return true;
		connection.close();
		return false;
	}
	
	public Doctor getRecordBydID(int id)
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from DoctorTable where DoctorId=(?)");
		statement.setInt(1, id);
		ResultSet resultset = statement.executeQuery();
		Doctor doc = new Doctor();
		if (resultset.next()) {
			doc = new Doctor();
			doc.setDid(resultset.getInt("DoctorId"));
			doc.setdName(resultset.getString("DoctorName"));
			doc.setdGender(resultset.getString("DoctorGender"));
			doc.setdAge(resultset.getInt("DoctorAge"));
			doc.setdMobile(resultset.getString("DoctorMobile"));
			doc.setdEmail(resultset.getString("DoctorEmail"));
			doc.setdPassword(resultset.getString("DoctorPassword"));
		}
		connection.close();
		return doc;
	}

}