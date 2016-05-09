package com.password.manager.bean;

public class Category {
	
	
	private int totalAccounts;
	private String _name;
	private String _formattedName="";
	//private String unFormattedName = "";
	
	public Category(String name)
	{
		_name = name;
	}
	
	public void setAccountCount(int totalAccounts)
	{
		 this.totalAccounts = totalAccounts;
		 _formattedName = "";		 
	}
	
	public int getAccountCount()
	{
		 return totalAccounts;
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
		return getFormattedName();
	}
	
	public String getFormattedName()
	{
		if (_formattedName.isEmpty())
		{
			_formattedName = _name.toUpperCase() + " ( " + getAccountCount() +" )" ;
		}
		return  _formattedName;		
	}
	
	
}
