package com.password.manager.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.password.manager.bean.Account;
import com.password.manager.bean.QueryData;
import com.password.manager.dao.DBActions;
import com.password.manager.dao.DBConnection;
import com.password.manager.util.Utilities;

public class DBActionsImpl implements DBActions {

	Utilities util = new Utilities();	
	@Override
	public void createNewAccount(Account acc) {
		util.writeLogFile( "\nI am inside the insertPMDetails...function");		
		try  {  
			 Connection con = DBConnection.getDBConnection(); 
			 con.setAutoCommit(false);		    
			 String OpenBracket   = "(";  
		     String ClosedBracket = ")"; 
		     String SemoColon     = ";";
		     String Quote         = "'";		     		    	    
		     String valueString1  = OpenBracket.concat(Quote).concat(acc.getNewAccountName()).concat(Quote).concat(",").concat(Quote).concat(acc.getNewAccountCategory()).concat(Quote).concat(",").concat(Quote).concat(acc.getNewAccountUsername()).concat(Quote).concat(",").concat(Quote).concat(acc.getNewAccountPassword()).concat(Quote).concat(ClosedBracket).concat(SemoColon);		    
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
	public void updateAccount(String accountPassword, String accountName) {		
		Connection con = null;	 
		con  = DBConnection.getDBConnection();		
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);
			String sqlQuery = "UPDATE PPASSWORDDETAILS SET PASSWORD = " + "'"+accountPassword+ "'" + " WHERE ACCOUNT= "+"'"+accountName+"'"+";";						
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
	public void deleteAccount(String accountName) {
		Connection con = null;
		con  = DBConnection.getDBConnection();
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);
			String sqlQuery = "DELETE FROM PPASSWORDDETAILS WHERE ACCOUNT= "+"'"+accountName+"'" + ";";			
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
	public Account getAccountData(String account) {
		Account aCC = new Account(account);
		Connection con = null;		
	    String accValue = null;  
		con  = DBConnection.getDBConnection();			
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);			
			accValue = account;			
			String sqlQuery = "SELECT * FROM PPASSWORDDETAILS WHERE ACCOUNT = " + "'" + accValue + "'" +  ";";			
			ResultSet rs = stmt.executeQuery( sqlQuery );			
			while (rs.next()){					
				 String  acc       = rs.getString("ACCOUNT"); 							 
				 String  userName  = rs.getString("USERNAME");
		         String  password  = rs.getString("PASSWORD");
		         String  category  = rs.getString("CATEGORY");
		         
		         aCC.setNewAccountName(acc);	
		         aCC.setNewAccountUsername(userName);
		         aCC.setNewAccountPassword(password);	
		         aCC.setCategory(category);
		     }			
			rs.close();
		    stmt.close();						
		    con.close();									
	  } 
	  catch (SQLException e) {				  
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);}	      				
		return aCC;		
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
			 String category = qData.getSelectedCategory().toUpperCase();
			 sqlQuery = "INSERT INTO PCATEGORY (CATEGORY) VALUES ('" + category + "'" + ")" + ";";			    				 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();			
		 } 
		 catch (SQLException e){			 
			 util.writeLogFile( e.getClass().getName() + ": " + e.getMessage());			 			 			 			 		
		     System.exit(0);}	 
	}
	@Override
	public void deleteFromCategory(QueryData qData) {
		Connection con = null;
		con  = DBConnection.getDBConnection();
		try  {  	 
			 con.setAutoCommit(false);		    	    
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();
			 String category = qData.getCategoryTobeRemoved();
			 sqlQuery = "DELETE FROM PCATEGORY WHERE CATEGORY= "+"'"+category+"'" + ";";							    				 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();
			 util.writeLogFile( "\nValues removed from DB Successfully!!");
		 } 
		 catch (SQLException e){			 
			 util.writeLogFile( e.getClass().getName() + ": " + e.getMessage());			 			 			 			 		
		     System.exit(0);}	 		
	}
	public QueryData getExistingAccounts(String categoryName){				
		Connection con = null;	
		ResultSet rs = null;		
		int inx = 0;
		QueryData qData = new QueryData();		
		String [] account = new String [200];		
		con  = DBConnection.getDBConnection();				
		try {											
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();	
			String whereClause =categoryName;
			String sqlQuery =  "SELECT ACCOUNT FROM PPASSWORDDETAILS WHERE CATEGORY = " + "'" + whereClause + "'" +  ";";
			rs = stmt.executeQuery(sqlQuery);				
			while ( rs.next() ){										
				account[inx] = rs.getString("ACCOUNT"); 													
				inx++;									
				String [] accountsNotNull = removeNullValue(account);
				qData.setAccount(accountsNotNull);										
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
