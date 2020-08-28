package team.f4.javaee.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBUntils {
	//1 ������
	public static Connection getconn() throws ClassNotFoundException, SQLException {
		final String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
		final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Information";
		final String username = "sa";
		final String password = "12345";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	public  void releaseAll(Connection conn,
			PreparedStatement ps,
			ResultSet rs) {
		//		6�ͷ���Դ
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
