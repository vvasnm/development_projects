package com.password.manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;


import com.password.manager.util.Constants;

public class DBConnection {
	static{
		try 
		{
			Class.forName(Constants.DB_CLASS);
			
		} 
		catch (ClassNotFoundException e) 
		{					
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	private static Connection conn = null;
	
	public static synchronized Connection getDBConnection(){
		try{
			if(conn == null || conn.isClosed()){
				conn = null;				
				conn =  DriverManager.getConnection(Constants.DB_CONNECTION_NAME);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
