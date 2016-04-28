package com.password.manager.bean;

public class Category {
	
	
	private int totalAccounts;
	private String _name;
	
	public Category(String name)
	{
		_name = name;
	}
	
	public void setAccountCount(int totalAccounts)
	{
		 this.totalAccounts = totalAccounts;
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
		System.out.println("_totalAccounts.." + getAccountCount());
		return _name.toUpperCase() + " ( " + getAccountCount() +" )" ;
		
	}

	public String removeTrailingStrings(){
		String  categoryValue = _name;					
		String delims = " [(] ";
		String[] category = categoryValue.split(delims);
		return category[0];
	}
	

}
