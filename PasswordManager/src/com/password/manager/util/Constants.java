package com.password.manager.util;

public class Constants {
	
	public static final String DB_CLASS = "org.sqlite.JDBC";
	public static final String DB_CONNECTION_NAME = "jdbc:sqlite:pm.db";
	public static final String PASSWORD = "Password";
	public static final String USERNAME = "User name";
	public static final String CONNECT = "Connect";
	public static final String CLEAR = "Clear";
	public static final String INCORRECT_CREDNTIALS = "User name or passowrd combitation is invalid,Please Enter Valid Username/Passowrd.";
    public static final String USER_EXISTS = "User already exists,please login using username and password to login.";
	public static final String SEPERATOR = "\\";
	public static final String LOGFILE = "passwordManager.log";
	public static final String DB_FILE = "pm.db";
	public static final String DB_USER_TABLE = "PUSERPROFILE"; 
	public static final String DB_PM_TABLE = "PDETAILS"; 
	public static final String NO_DATA_ERROR = "No Data exists,please create user first and try loggin in...";
	public static final String UNIQUE_USER_ERROR = "Only One user is permitted to use this application,Cant create new user...";
	public static final String CLOSE_WARNING = "You are about to close the page,press 'OK' to close the Window.";
	public static final String EMPTY_FIELDS = "Fields can't be Empty,please fill them and execute the operation.";
	public static final String ACCOUNT_EXIST = "The Account already Exist,please enter different account.";
	public static final String PASSWORD_MATCH = "Entered Password were not matched,please enter correct data.";
	public static final String VIEW_INFO_ERROR = "Select the Account Details to perform the operation.";
	public static final String ACCOUNT_NAME = "ACCOUNT NAME";
	public static final String NULL_WHERE_CLAUSE = "Please select the 'LABEL' Or 'Account' to Fetch the  Records.";
	public static final String NO_HEAD_SELECTED = "Account can't be empty, Please selecte Account and Delete the records from DB";
	public static final String CATEGORY_EXIST = "Category already exist,please enter a different category.";
	public static final String CATEGORY_HAS_ACCOUNTS = "There are accounts available under the category,please remove the accounts and try removing the category.";
	public static final String SELECT_CATEGORY = "Please select category and execute the Operation.";
	public static final String CATEGORY_INSTRUCTIONS = "1) Check the Existing Category from List above before creating it.\r\n2) Check is to avoid duplicates.\r\n\r\n";
	public static final String SAVE_CATEGORY_TOOLTIP = "Click to Save Category";
	public static final String DELETE_CATEGORY_TIP = "Click to Delete Category";
	public static final String NO_ACCOUNTS = "No Accounts to Display!!";
	
}