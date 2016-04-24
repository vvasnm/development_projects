package com.password.manager.bean;

import org.eclipse.swt.widgets.List;

public class QueryData {	
		
	
	
	private String pmHead = null;
	private int pmSno =0;
	private String pmExistingHead = null;
	private String pmUsername = null;
	private String pmPassword = null;
	private String label = null;
	private String [] account = null;
	private String pmReNewPassword = null;
	private String whereClauseField = null;
	private String userNameDB = null;
	private String passWordDB = null;
	private String accountDB = null;
	private int rowCnt =0;
	private String [] Category = null;
	private String selectedCategory = null;
	private String selectedAccount = null;
	private String modifiedPassword = null;
	private List listCat = null;
	private String categoryTobeRemoved = null;
	
	
	public String getCategoryTobeRemoved() {
		return categoryTobeRemoved;
	}
	public void setCategoryTobeRemoved(String categoryTobeRemoved) {
		this.categoryTobeRemoved = categoryTobeRemoved;
	}
	public String getPmReNewPassword() {
		return pmReNewPassword;
	}
	public void setPmReNewPassword(String pmReNewPassword) {
		this.pmReNewPassword = pmReNewPassword;
	}	
	public String[] getAccount() {
		return account;
	}
	public void setAccount(String[] account) {
		this.account = account;
	}    
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}	
	public String getPmHead() {
		return pmHead;
	}
	public void setPmHead(String pmHead) {
		this.pmHead = pmHead;
	}
	public int getPmSno() {
		return pmSno;
	}
	public void setPmSno(int pmSno) {
		this.pmSno = pmSno;
	}
	public String getPmExistingHead() {
		return pmExistingHead;
	}
	public void setPmExistingHead(String pmExistingHead) {
		this.pmExistingHead = pmExistingHead;
	}
	public String getPmUsername() {
		return pmUsername;
	}
	public void setPmUsername(String pmUsername) {
		this.pmUsername = pmUsername;
	}
	public String getPmPassword() {
		return pmPassword;
	}
	public void setPmPassword(String pmPassword) {
		this.pmPassword = pmPassword;
	}
	public String getWhereClauseField() {
		return whereClauseField;
	}
	public void setWhereClauseField(String whereClauseField) {
		this.whereClauseField = whereClauseField;
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
	public String getAccountDB() {
		return accountDB;
	}
	public void setAccountDB(String accountDB) {
		this.accountDB = accountDB;
	}
	public int getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
// category variables	
	public String[] getCategory() {
		return Category;
	}
	public void setCategory(String[] category) {
		Category = category;
	}
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
// Account variables
	public String getSelectedAccount() {
		return selectedAccount;
	}
	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}
	public String getModifiedPassword() {
		return modifiedPassword;
	}
	public void setModifiedPassword(String modifiedPassword) {
		this.modifiedPassword = modifiedPassword;
	}
	public List getListCat() {
		return listCat;
	}
	public void setListCat(List listCat) {
		this.listCat = listCat;
	}
}
