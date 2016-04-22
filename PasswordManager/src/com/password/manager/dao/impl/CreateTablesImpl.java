package com.password.manager.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.password.manager.dao.CreateTables;
import com.password.manager.dao.DBConnection;

public class CreateTablesImpl implements CreateTables {
	@Override
	public void createUserTable() {
		Connection con = null; 
		try {
			 con = DBConnection.getDBConnection();
			 con.setAutoCommit(false);
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();			
			 sqlQuery = " CREATE TABLE IF NOT EXISTS PUSERPROFILE " +
	                    "(ID  INT PRIMARY KEY     NOT NULL, " +
					    " NAME           TEXT     NOT NULL, " + 
	                    " USERNAME       TEXT     NOT NULL, " + 	                   
	                    " PASSWORD       CHAR(50))         " ; 			 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();			 
		 } 		
		catch( SQLException e ) {
	    	if( con != null ) {
	    		try { con.rollback(); }        // rollback on error 
	    		catch( SQLException ee ) { }
	    	}
	    	e.printStackTrace();
	    }finally {
	    	try { con.close(); }
	    	catch( SQLException e ) { e.printStackTrace(); }
	    }		
	}
	@Override
	public void createCategoryTable() {
		Connection con = null; 
		try {
			 con = DBConnection.getDBConnection();
			 con.setAutoCommit(false);
			 Statement stmt  = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();				
			 sqlQuery = "CREATE TABLE  PCATEGORY (CATEGORY TEXT PRIMARY KEY  NOT NULL);";	                    			 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();			 
		 } 		
		catch( SQLException e ) {
	    	if( con != null ) {
	    		try { con.rollback(); }        // rollback on error 
	    		catch( SQLException ee ) { }
	    	}
	    	e.printStackTrace();
	    }finally {
	    	try { con.close(); }
	    	catch( SQLException e ) { e.printStackTrace(); }
	    }		
		
	}			
	@Override
	public void passwordInformationTable() {
		Connection con = null; 
		try {
			 con = DBConnection.getDBConnection();
			 con.setAutoCommit(false);
			 Statement stmt  = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();				
			 sqlQuery = "CREATE TABLE  PPASSWORDDETAILS (ACCOUNT TEXT PRIMARY KEY  NOT NULL,CATEGORY TEXT NOT NULL,USERNAME  TEXT NOT NULL,PASSWORD  CHAR(50));";	                    			 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();			 
		 } 		
		catch( SQLException e ) {
	    	if( con != null ) {
	    		try { con.rollback(); }        // rollback on error 
	    		catch( SQLException ee ) { }
	    	}
	    	e.printStackTrace();
	    }finally {
	    	try { con.close(); }
	    	catch( SQLException e ) { e.printStackTrace(); }
	    }		
		
	}
	@Override
	public void CreateAllTables() {
		createUserTable();
		createCategoryTable();
		passwordInformationTable();		
	}

}
