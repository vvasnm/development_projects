package com.password.manager.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import com.password.manager.bean.UserData;
import com.password.manager.dao.DBConnection;


public class PMUtilities {
	
	public PMUtilities(){		
		super();		
	}
    Shell shell = new Shell();
   // static Logger log = Logger.getLogger(Utilities.class.getName());	
	public void createUserProfile(String sno,String name,String userid,String password){
		int rowCnt = 0;						
	//	createUserTable();				
		rowCnt = rowCount(Constants.DB_USER_TABLE);		
		if (rowCnt == 0){
			// in this way we are restricting the application is only for single user.
			insertUserInfo(sno,name,userid,password);
		}
		else{
			Shell shl = new Shell();
			MessageBox mBox = new MessageBox(shl,0);
			mBox.setMessage(Constants.UNIQUE_USER_ERROR);			
			mBox.open();
		}					
	}
	 
	
	//This Function Writes log File to TEMP folder...
	public void writeLogFile (String logInfo){
		String logFile = System.getenv("TEMP").concat(Constants.SEPERATOR).concat(Constants.LOGFILE);
		Logger logger = Logger.getLogger(Constants.LOGFILE); 
		FileHandler fh = null;
		try {
			fh = new FileHandler(logFile);
			logger.addHandler(fh);
		    SimpleFormatter formatter = new SimpleFormatter();  
		    fh.setFormatter(formatter);		    
		    logger.info(logInfo); 		    
		} 
		catch (SecurityException | IOException e) {			
			e.printStackTrace();
		}			    
	}
		
	public  int rowCount(String tableName){		
		int rowCount = 0;Connection con = null; 								
		try {
			 con = DBConnection.getDBConnection();
			 con.setAutoCommit(false);
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();							
			 sqlQuery = "SELECT COUNT (*) FROM " + tableName.concat(";"); 
			 ResultSet rs =  stmt.executeQuery(sqlQuery);		
			 while (rs.next()) {
				 rowCount = rs.getInt(1);
			 } stmt.close();			 									
		 } catch(SQLException e){
	    	if( con != null ) {
	    		try { con.rollback(); }        // rollback on error 
	    		catch( SQLException ee ) { }
	    	}e.printStackTrace();	    	
	    }finally {
	    	try { con.close(); }
	    	catch( SQLException e ) { e.printStackTrace(); }
	    }writeLogFile(rowCount + " - User Profile Exist.");				
		return rowCount;
	}
	
	// This function is used at the time of new user creation.
	public void insertUserInfo(String sno,String name,String userid,String password){
		writeLogFile("Inside insertUserInfo function...");
		try {  
			 Connection con = DBConnection.getDBConnection(); 
			 con.setAutoCommit(false);
		     String OpenBracket   = "(";  
		     String ClosedBracket = ")"; 
		     String SemoColon     = ";";
		     String Quote1        = "'";
		     
		     int sNo = Integer.parseInt(sno);
		                            //"(1,'DBSBANK','svad','12345');";   
		     String valueString1  = OpenBracket + sNo +",".concat(Quote1).concat(name).concat(Quote1).concat(",").concat(Quote1).concat(userid).concat(Quote1).concat(",").concat(Quote1).concat(password).concat(Quote1).concat(ClosedBracket).concat(SemoColon);
		     
			 Statement stmt = null;
			 String sqlQuery = null;
			 stmt = con.createStatement();
			 sqlQuery = "INSERT INTO PUSERPROFILE (ID,NAME,USERNAME,PASSWORD)"
			 		    + " VALUES"  
					    +  valueString1 ;			 
			 stmt.executeUpdate(sqlQuery);
			 con.commit();
			 stmt.close();
		 } 
		 catch (SQLException e) {			 
			 writeLogFile( e.getClass().getName() + ": " + e.getMessage());			 			 			 			 		
		     System.exit(0);
		 }
		//log.info("\nINFO: User Profile Created Successfully...");
		writeLogFile("User Profile Created Successfully...");
		
	}			
	// This function is used to validate username/password at the time of login.
	
	private UserData queryCretentials(UserData pmdata){		
		Connection con = null;			
		con  = DBConnection.getDBConnection();			
		try {						
			Statement stmt = con.createStatement();			
			con.setAutoCommit(false);													
			ResultSet rs = stmt.executeQuery( "SELECT * FROM PUSERPROFILE;" );	
			if(rs!=null){
				while ( rs.next()){						
					 int id = rs.getInt("id"); 							 
					 String  userName  = rs.getString("username");
			         String  password  = rs.getString("password");
			         String sNo = Integer.toString(id);	
			         //Setting the values to PMData
			         pmdata.setsNoDB(sNo);		       
			         pmdata.setUserNameDB(userName);
			         pmdata.setPassWordDB(password);	
			         writeLogFile("Fetched the Credentials.");
			     }
			}			
			rs.close();
		    stmt.close();						
		    con.close();									
	  } catch (SQLException e) {				  
		  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	  }	      			
		return pmdata;
	}	
	public boolean isUserValid (UserData pmdata){		
		boolean isValid = false;	    
		UserData data1 = queryCretentials(pmdata);					
		if ((data1.getUserNameDB().equals(pmdata.getUsername())) 
			&& (data1.getPassWordDB().equals(pmdata.getPassword()))){
		    isValid = true;	
		    writeLogFile("User is a Valid User,Proceed to Login.");
		}				
		return isValid;
	}	
	public boolean isDBExists(){
		boolean isExist = false;
		File file = new File (Constants.DB_FILE);
		 if(file.exists()) {
	         isExist = true;
	     }	 
		 return isExist;
	}	
	public boolean istableExists(String [] tables){
		boolean isExists = false;		
		ResultSet rs = null;
		String sqlQuery , table = null;
		Statement stmt = null;
		Connection con = DBConnection.getDBConnection();
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();							
			//sqlQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'"+";";
			sqlQuery = "SELECT name FROM sqlite_master WHERE type='table';";
			rs = stmt.executeQuery(sqlQuery);			     				    			
			while (rs.next()){	
				table  = rs.getString("name");
				for (String tablemame : tables){
					if (table.equals(tablemame)){
						
						isExists = true;										
					}
				}
				
			}			
		   stmt.close();						
		   con.close();			  		   																			
		} catch (SQLException e) {			
			  writeLogFile( e.getClass().getName() + ": " + e.getMessage());			  
		      System.exit(0);
		}	
		return isExists;		
	}
	
	public void clearFiles(String folderLocation){
		File folder = new File(folderLocation);
		File folderContent[] = folder.listFiles();		
		for (int i = 0; i < folderContent.length; i++) {
		    File pes = folderContent[i];
		    if (!pes.getName().endsWith(".log"))   {		    	
		    	folderContent[i].delete();
		    }
		    else if(pes.getName().endsWith(".lck")){
		    	folderContent[i].delete();
		    }
		}
	}
}
	
	
