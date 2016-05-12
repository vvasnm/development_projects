package com.password.manager.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.password.manager.bean.Category;
import com.password.manager.bean.QueryData;
import com.password.manager.dao.impl.DBActionsImpl;
import com.password.manager.listeners.IListenEvents;

public class CategoryRepository {

	private QueryData qData = new QueryData(); 
	private DBActionsImpl dbActions = new DBActionsImpl();
	private static CategoryRepository _categoryRepository = new CategoryRepository();
	private ArrayList<IListenEvents> categoryEventListeners = new ArrayList<IListenEvents>();
	private HashMap <String,Category> hashCategories = new HashMap<String,Category>();
	
	public static CategoryRepository getInstance(){
		return _categoryRepository;
	}	
	private CategoryRepository(){
		qData = dbActions.getCategoriesFromDB();
		if(qData.getCategory()!=null){		
			for(String itm: qData.getCategory()){
				Category cat =	new Category(itm);
				int cnt = dbActions.accountCount(itm);				
				cat.setAccountCount(cnt);								
				hashCategories.put(itm, cat);			
			}
		}		
	}
	public String [] setAllCategories(){
		String[] items = new String[hashCategories.size()];
		int index=0;
		for(Iterator<Category> it = hashCategories.values().iterator(); it.hasNext();)
		{
			items[index++] = it.next().getName().toUpperCase();
		}    
		return items;
    }
	public void Add(String categoryName){
		qData.setSelectedCategory(categoryName);
		dbActions.insertIntocategory(qData);
		Category cat = new Category(categoryName);
		hashCategories.put(cat.getName(), cat);
		 for (IListenEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryAdded(cat);	
    }
    public void removeCategories(String formattedCategoryName){
    	Category matchingCat = getCategoryByFormattedName(formattedCategoryName);
    	if (matchingCat == null)
    	{
    		return;
    	}
    	String catName = matchingCat.getName();
		qData.setCategoryTobeRemoved(catName);
		dbActions.deleteFromCategory(qData);		
		hashCategories.remove(catName);
		for (IListenEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryDeleted(formattedCategoryName);	
	}		
	public String[] GetAll(){
		String[] items = new String[hashCategories.size()];
		int index=0;
		for(Iterator<Category> it = hashCategories.values().iterator(); it.hasNext();)
		{
			items[index++] = it.next().toString();
		}    
		return items;
    }
	
	public void addListener(IListenEvents listener){
		categoryEventListeners.add(listener);	
	}
	public boolean hasCategory(String formattedCategoryName){
		return getCategoryByFormattedName(formattedCategoryName) != null;		
	}
	
	public boolean hasAnyAccounts(String formattedCategoryName){		
		Category matchingCategory = getCategoryByFormattedName(formattedCategoryName);
		if (matchingCategory == null)
		{
			return false;
		}
		return matchingCategory.getAccountCount()  > 0;
	}
	
	
	private Category getCategoryByFormattedName(String formattedCategoryName){
		Category matchingCategory = null;
		for (Iterator<Category> it = hashCategories.values().iterator(); it.hasNext(); ) {
    	    Category currentCat = it.next();
    	    if (currentCat.getFormattedName().equals(formattedCategoryName))    	   
    	    {    	    	
    	    	matchingCategory = currentCat;    	    	
    	    	break;
    	    }
    	}
		return matchingCategory;
	}	
}
