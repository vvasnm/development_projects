package com.password.manager.dao;

import com.password.manager.bean.QueryData;

public interface DBActions {
	
	public QueryData selectPMDetails(QueryData qData);
	public void insertPMDetails(QueryData qData);
	public void updatePMDetails(QueryData qData);
	public void deletePMDetails(QueryData qData);
	public void createPMDataTable();
	public void insertIntocategory(QueryData qData);	
}
