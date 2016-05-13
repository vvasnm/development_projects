package com.password.manager.dao;

import com.password.manager.bean.Account;
import com.password.manager.bean.QueryData;

public interface DBActions {
	//Account Related Methods
	public void createNewAccount(Account acc);
	public Account getAccountData(String account);
	public void updateAccount(String accountPassword,String accountName);
	public void deleteAccount(String account);
	//Category Related methods
	public void insertIntocategory(QueryData qData);
	public void deleteFromCategory(QueryData qData);
}
