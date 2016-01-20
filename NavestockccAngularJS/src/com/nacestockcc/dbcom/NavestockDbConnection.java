package com.nacestockcc.dbcom;

//import com.google.cloud.sql.jdbc.Connection;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;





public class NavestockDbConnection {
	
	private Connection conn;
	
	public Connection getNavestockDbConnection(){ 
	
	String url = null;
	
	try {	
		if (SystemProperty.environment.value() ==
		SystemProperty.Environment.Value.Production) {
		// Connecting from App Engine.
		// Load the class that provides the "jdbc:google:mysql://"
		// prefix.
			Class.forName("com.mysql.jdbc.GoogleDriver");
			url = "jdbc:google:mysql://navestockcc-001:navestockdb/navestockdb?user=root";	 
			conn = DriverManager.getConnection(url);
		} else {
              // Local MySQL instance to use during development.
				Class.forName("com.mysql.jdbc.Driver");
			  url = "jdbc:mysql://173.194.255.10:3306/navestockdb";
			  conn = DriverManager.getConnection(url, "*****", "*******");
		}		
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}

	
	return conn;
	}

	public void closeNavestockDbConnection(Connection closeConn){
		 try {
			closeConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
