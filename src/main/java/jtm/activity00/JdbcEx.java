package jtm.activity00;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEx {

	public static void main(String[] args) {
		ClassesManager teacherManager = new ClassesManager();
		teacherManager.findClass(2);

	}

}

class ClassesManager {

	protected Connection conn; 
	
	public ClassesManager() {
		// TODO #1 When new TeacherManager is created, create connection to the
		// database server:
		// url = "jdbc:mysql://localhost/?autoReconnect=true&useSSL=false&characterEncoding=utf8"
		// user = "root"
		// pass = "Student007"
		// Hints:
		// 1. Do not pass database name into url, because some statements
		// for tests need to be executed server-wise, not just database-wise.
		// 2. Set AutoCommit to false and use conn.commit() where necessary in
		// other methods
		
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/?autoReconnect=true&useSSL=false", "root", "Student007");
				conn.setAutoCommit(false);
			} catch (Exception e) {
			}
		}
	}
	
	public String findClass(int id) {
		String result = "";
		try {
		conn.setAutoCommit(false);
		PreparedStatement pSmt = conn.prepareStatement("SELECT name FROM database_activity.clases where id=?");
		pSmt.setInt(1, id);
		conn.commit();
		ResultSet rs = pSmt.executeQuery();
		if (rs.next()) {
			result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}