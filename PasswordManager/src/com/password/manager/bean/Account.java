package com.password.manager.bean;

public class Account {
	private String newAccountName = null;
	private String name;
	private String newAccountUsername = null;
	private String newAccountPassword = null;
	private String newAccountCategory = null;
	private String [] account = null;
	
	public Account(String accountName){
		name = accountName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String[] getAccount() {
		return account;
	}
	public void setAccount(String[] account) {
		this.account = account;
	}
	public String getNewAccountName() {
		return newAccountName;
	}
	public void setNewAccountName(String newAccountName) {
		this.newAccountName = newAccountName;
	}
	public String getNewAccountUsername() {
		return newAccountUsername;
	}
	public void setNewAccountUsername(String newAccountUsername) {
		this.newAccountUsername = newAccountUsername;
	}
	public String getNewAccountPassword() {
		return newAccountPassword;
	}
	public void setNewAccountPassword(String newAccountPassword) {
		this.newAccountPassword = newAccountPassword;
	}
	public String getNewAccountCategory() {
		return newAccountCategory;
	}
	public void setNewAccountCategory(String newAccountCategory) {
		this.newAccountCategory = newAccountCategory;
	}	
}
