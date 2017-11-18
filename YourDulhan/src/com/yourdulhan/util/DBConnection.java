package com.yourdulhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static final String driverName="com.mysql.jdbc.Driver";
	public static final String dbUrl="jdbc:mysql://localhost:3306/your_dulhan";
	public static final String dbUser="root";
	public static final String dbPassword="root";

	public static Connection getDbConnection(){
		Connection conn=null;

		try {
			Class.forName(driverName);
			try {
				conn=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return conn;

	}


}
