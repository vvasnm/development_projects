package com.password.manager.repositories;

import java.util.ArrayList;

import com.password.manager.bean.Category;
import com.password.manager.bean.QueryData;
import com.password.manager.dao.impl.DBActionsImpl;

public class CategoryRepository {
	
	private ArrayList<Category> categories = new ArrayList<Category>();
	private QueryData qData = new QueryData(); 
	private DBActionsImpl dbActions = new DBActionsImpl();
	
	public CategoryRepository()
	{
		qData = dbActions.getCategoriesFromDB();
		if(qData.getCategory()!=null){		
			for(String itm: qData.getCategory()){
				Category cat =	new Category(itm);
				int cnt = dbActions.accountCount(itm);
				cat.setAccountCount(cnt);
				categories.add(cat);
			}
		}		
	}
	
	public void Add(String categoryName)
	{
		qData.setSelectedCategory(categoryName);
		dbActions.insertIntocategory(qData);
		categories.add(new Category(categoryName));					
	}
	
	public String[] GetAll(){
		String[] items = new String[categories.size()];
		int index=0;
		for(Category cat: categories)
		{
		  items[index++] = cat.toString();		  
		}
		return items;
	}
	
	

}
