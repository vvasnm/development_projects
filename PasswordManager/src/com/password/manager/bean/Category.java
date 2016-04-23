package com.password.manager.bean;

public class Category {
	
	private int _totalAccounts;
	private String _name;
	
	public Category(String name)
	{
		_name = name;
	}
	
	public void setAccountCount(int totalAccounts)
	{
		 _totalAccounts = totalAccounts;
	}
	
	public int getAccountCount()
	{
		 return _totalAccounts;
	}
	
	public void setName(String name)
	{
		_name = name;
	}
	
	public String getName()
	{
		return _name;
	}
	
	@Override
	public String toString()
	{
		return _name.toUpperCase() + " ( " + _totalAccounts +" )" ;
		
	}

}
