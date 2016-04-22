package com.password.manager.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.password.manager.bean.QueryData;
import com.password.manager.dao.DBActions;
import com.password.manager.dao.DBConnection;
import com.password.manager.util.Utilities;

public class DBActionsImpl implements DBActions {

	Utilities util = new Utilities();
	static Logger log = Logger.getLogger(DBActionsImpl.class.getName());
	@Override
	public void createPMDataTable() {
		Connection con = null; 
		try {
			 con = DBConnection.getDBConnection();
			 con.setAutoCommit(false);
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();	
			 util.writeLogFile( "\nIn createPMDataTable...function");		
			 sqlQuery = "CREATE TABLE  PDETAILS (HEAD TEXT   PRIMARY KEY  NOT NULL,LABEL TEXT NOT NULL,USERNAME  TEXT NOT NULL,PASSWORD  CHAR(50));";	                    			 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();
			 util.writeLogFile( "\nTable Created Successfully!!");
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
	public void insertPMDetails(QueryData qData) {
		util.writeLogFile( "\nI am inside the insertPMDetails...function");		
		try  {  
			 Connection con = DBConnection.getDBConnection(); 
			 con.setAutoCommit(false);
		    // int rowCnt = 0;
			 String OpenBracket   = "(";  
		     String ClosedBracket = ")"; 
		     String SemoColon     = ";";
		     String Quote         = "'";		     
		    // rowCnt = util.rowCount(Constants.DB_PM_TABLE)+1;
		    
		     String valueString1  = OpenBracket.concat(Quote).concat(qData.getPmHead()).concat(Quote).concat(",").concat(Quote).concat(qData.getSelectedCategory()).concat(Quote).concat(",").concat(Quote).concat(qData.getPmUsername()).concat(Quote).concat(",").concat(Quote).concat(qData.getPmPassword()).concat(Quote).concat(ClosedBracket).concat(SemoColon);		    
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();
			 
			 sqlQuery = "INSERT INTO PPASSWORDDETAILS (ACCOUNT,CATEGORY,USERNAME,PASSWORD)"
			 		    + " VALUES"  
					    +  valueString1 ;				 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();
			 util.writeLogFile( "\nValues Inserted into DB Successfully!!");
		 } 
		 catch (SQLException e){			 
			 util.writeLogFile( e.getClass().getName() + ": " + e.getMessage());			 			 			 			 		
		     System.exit(0);
		 }			
	}			
	@Override
	public void updatePMDetails(QueryData qData) {
		// update ppassworddetails set password = "svad2" where ACCOUNT="DBS" AND CATEGORY = "BANKS";
		Connection con = null;
		util.writeLogFile( "Inside deletePMDetails function...");			   
		con  = DBConnection.getDBConnection();
		String account = null;		
		String password = null;
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);
			password = qData.getModifiedPassword();
			account  = qData.getSelectedAccount();
			String sqlQuery = "UPDATE PPASSWORDDETAILS SET PASSWORD = " + "'"+password+ "'" + " WHERE ACCOUNT= "+"'"+account+"'"+";";			
			System.out.println(sqlQuery);
			stmt.executeUpdate(sqlQuery);						
		    stmt.close();	
		    con.commit();
		    con.close();			     
	  } 
	  catch (SQLException e) {		 
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	  }	      				
	}
	@Override
	public void deletePMDetails(QueryData qData) {
		Connection con = null;
		util.writeLogFile( "Inside deletePMDetails function...");		
	    String existingHead = null;
		con  = DBConnection.getDBConnection();
		existingHead = qData.getPmExistingHead();
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);
			existingHead = qData.getSelectedAccount();
			String sqlQuery = "DELETE FROM PPASSWORDDETAILS WHERE ACCOUNT= "+"'"+existingHead+"'" + ";";			
			stmt.executeUpdate(sqlQuery);						
		    stmt.close();	
		    con.commit();
		    con.close();	
		    util.writeLogFile( "Selected Head deleted Successfully...");
	  } 
	  catch (SQLException e) {		
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	  }	      										
	}    
	@Override
	public QueryData selectPMDetails(QueryData qData) {
		Connection con = null;
		//util.writeLogFile( "Inside selectPMDetails function...");
	    String whereClause = null;  
		con  = DBConnection.getDBConnection();			
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);			
			whereClause = qData.getWhereClauseField();			
			String sqlQuery = "SELECT * FROM PPASSWORDDETAILS WHERE ACCOUNT = " + "'" + whereClause + "'" +  ";";			
			ResultSet rs = stmt.executeQuery( sqlQuery );
			int i=0;
			while (rs.next()){					
				 String  account   = rs.getString("ACCOUNT"); 							 
				 String  userName  = rs.getString("USERNAME");
		         String  password  = rs.getString("PASSWORD");
		         i++;
		         qData.setAccountDB(account);	
		         qData.setUserNameDB(userName);
		         qData.setPassWordDB(password);		                 
		     }
			qData.setRowCnt(i);
			rs.close();
		    stmt.close();						
		    con.close();									
	  } 
	  catch (SQLException e) {				  
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);}	      				
		return qData;		
	}	
	@Override
	public void insertIntocategory(QueryData qData) {
		Connection con = null;
		con  = DBConnection.getDBConnection();
		try  {  	 
			 con.setAutoCommit(false);		    	    
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();
			 String category = qData.getSelectedCategory();
			 sqlQuery = "INSERT INTO PCATEGORY (CATEGORY) VALUES ('" + category + "'" + ")" + ";";
			 System.out.println(sqlQuery);		    				 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();
			 util.writeLogFile( "\nValues Inserted into DB Successfully!!");
		 } 
		 catch (SQLException e){			 
			 util.writeLogFile( e.getClass().getName() + ": " + e.getMessage());			 			 			 			 		
		     System.exit(0);}	 
	}
	public QueryData getExistingAccounts(QueryData qData){		
		util.writeLogFile( "Inside getExistingAccounts function..");
		Connection con = null;	
		ResultSet rs = null;		
		int inx = 0;		
		String [] account = new String [200];		
		con  = DBConnection.getDBConnection();		
		//QueryData qData = new QueryData();
		try {											
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();	
			String whereClause =qData.getSelectedCategory();
			String sqlQuery =  "SELECT ACCOUNT FROM PPASSWORDDETAILS WHERE CATEGORY = " + "'" + whereClause + "'" +  ";";
			rs = stmt.executeQuery(sqlQuery);				
			while ( rs.next() ){										
				account[inx] = rs.getString("ACCOUNT"); 													
				inx++;									
				String [] headNotNull = removeNullValue(account);				
				qData.setAccount(headNotNull);								
		    }					        												 								
			rs.close();
		    stmt.close();						
		    con.close();								   		    		    
	  } 
	  catch (SQLException e)  {				  
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	  }	      				
		return qData;
	}
	
	private String [] removeNullValue (String [] values){
	   
		List<String> list = new ArrayList<String>();
		for(String s : values) {
		       if(s != null && s.length() > 0) {
		          list.add(s);
		       }
		    }   
		values = list.toArray(new String[list.size()]);

	    return values;
	}
	
	
	public QueryData getCategoriesFromDB() {
		Connection con = null;	
		ResultSet rs = null;
		int inx = 0;			
		String [] category= new String [100];	
		QueryData qData = new QueryData();
		con  = DBConnection.getDBConnection();			
		try {											
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();	
			String sqlQuery =  "SELECT CATEGORY FROM PCATEGORY;";
			rs = stmt.executeQuery(sqlQuery);				
			while ( rs.next() ){										
				category[inx] = rs.getString("CATEGORY"); 	inx++;																										
				String [] categoriesNotNull = removeNullValue(category);				
				qData.setCategory(categoriesNotNull);								
		    }					        												 								
			rs.close();
		    stmt.close();						
		    con.close();								   		    		    
	  } 
	  catch (SQLException e)  {				  
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	  }	
		return qData;
	}
	public  int accountCount(String category){		
		int rowCount = 0;
		Connection con = null; 						
		try {
			 con = DBConnection.getDBConnection();
			 con.setAutoCommit(false);
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();							
			 sqlQuery = "SELECT COUNT (*) FROM PPASSWORDDETAILS WHERE CATEGORY = " + "'"+category+"'"+(";"); 			
			 ResultSet rs =  stmt.executeQuery(sqlQuery);		
			 while (rs.next()) {
				 rowCount = rs.getInt(1);
			 }			 			
			 stmt.close();			
		 } catch( SQLException e ) {
	    	if( con != null ) {
	    		try { con.rollback(); }        // rollback on error 
	    		catch( SQLException ee ) { }
	    	}
	    	e.printStackTrace();
	    }finally {
	    	try { con.close(); }
	    	catch( SQLException e ) { e.printStackTrace(); }
	    }
		return rowCount;
	}
	
}
