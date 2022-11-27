package org.atminterface.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbConnection {

	static final String DB_URL="jdbc:mysql://localhost:3306/";
	static final String DB_USER="root";
	static final String DB_PASSWORD="root";
	static final String DB_NAME="Atmbankdb";
	
	//public static void dbconnect() throws ClassNotFoundException 
	public static Connection dbconnect() throws ClassNotFoundException 
	{
		Connection connection=null;
		try
		{
			//Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);
//			if(connection!=null)
//			{
//				System.out.println("Connection Sucessfully");
//		
//			}
//			else
//			{
//				System.out.println("Not Connected");
//			}
		}
		catch (SQLException e)
		{
			//System.out.println(e);
			System.out.println("Connection Error!!");
		}
		
		return connection;
		
		
	}

}
