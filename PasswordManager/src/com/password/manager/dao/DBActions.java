package com.password.manager.dao;

import com.password.manager.bean.Account;
import com.password.manager.bean.QueryData;

public interface DBActions {
	//Account Related Methods
	public void createNewAccount(Account acc);
	public QueryData getAccountData(QueryData qData);
	public void updateAccount(QueryData qData);
	public void deleteAccount(QueryData qData);
	//Category Related methods
	public void insertIntocategory(QueryData qData);
	public void deleteFromCategory(QueryData qData);
}
