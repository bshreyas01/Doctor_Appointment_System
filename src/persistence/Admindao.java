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
public class Admindao {
	
	public Admin getRecordByaID(int id)
			throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException {
		
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from admintable where AdminID=(?)");
		statement.setInt(1, id);
		ResultSet resultset = statement.executeQuery();
		Admin admin = new Admin();
		if (resultset.next()) {
//			admin = new Admin(id, null, null, null, null);
			admin=new Admin();
			admin.setAdminId(resultset.getInt("AdminID"));
			admin.setAdminName(resultset.getString("AdminName"));
			admin.setContactNumber(resultset.getString("ContactNumber"));
			admin.setEmail(resultset.getString("Email"));
			admin.setPassword(resultset.getString("Password"));
		}
		connection.close();
		return admin;
	}

}
