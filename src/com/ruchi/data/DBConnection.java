package com.ruchi.data;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;



public class DBConnection {
	private static BasicDataSource dataSource = null;
	private static String db = "ruchidb";
	@SuppressWarnings("finally")
	public static Connection getConnection() {
		Connection con = null;
		try {
			if (dataSource == null) {
				dataSource = new BasicDataSource();

				dataSource.setDriverClassName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				String url = "jdbc:mysql://localhost:3306/"+db;
				String user = "root";
				String password = "";
				dataSource.setUrl(url);
				dataSource.setUsername(user);
				dataSource.setPassword(password);
			}
			con = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return con;
		}
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResource(AutoCloseable res) {
		if (res != null) {
			try {
				res.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
}

