package com.password.manager.bean;

public class UserData {
		
	private String username = null;
	private String password = null;
	private String tablename = null;
	private String newUserSno = null;
	private String newUser = null;
	private String newUserID = null;
	private String newUserPassword = null;
	private String userNameDB = null;
	private String passWordDB = null;
	private String sNoDB = null;
	private boolean isTableExits = false;
	
	
	public boolean isTableExits() {
		return isTableExits;
	}
	public void setTableExits(boolean isTableExits) {
		this.isTableExits = isTableExits;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getNewUserSno() {
		return newUserSno;
	}
	public void setNewUserSno(String newUserSno) {
		this.newUserSno = newUserSno;
	}
	public String getNewUser() {
		return newUser;
	}
	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}
	public String getNewUserID() {
		return newUserID;
	}
	public void setNewUserID(String newUserID) {
		this.newUserID = newUserID;
	}
	public String getNewUserPassword() {
		return newUserPassword;
	}
	public void setNewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
	}		
	public String getUserNameDB() {
		return userNameDB;
	}
	public void setUserNameDB(String userNameDB) {
		this.userNameDB = userNameDB;
	}
	public String getPassWordDB() {
		return passWordDB;
	}
	public void setPassWordDB(String passWordDB) {
		this.passWordDB = passWordDB;
	}
	public String getsNoDB() {
		return sNoDB;
	}
	public void setsNoDB(String sNoDB) {
		this.sNoDB = sNoDB;
	}
}
