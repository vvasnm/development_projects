package com.password.manager.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.password.manager.bean.Category;
import com.password.manager.bean.QueryData;
import com.password.manager.dao.impl.DBActionsImpl;
import com.password.manager.listeners.IListenEvents;

public class CategoryRepository {
	
	//private ArrayList<Category> categories = new ArrayList<Category>();	
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
				System.out.println("Count..."+ cat.getAccountCount());
				hashCategories.put(itm, cat);
				//categories.add(cat);
			}
		}		
	}
	public void Add(String categoryName){
		qData.setSelectedCategory(categoryName);
		dbActions.insertIntocategory(qData);
		Category cat = new Category(categoryName);
		//categories.add(cat);
		hashCategories.put(cat.getName(), cat);
		 for (IListenEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryAdded(cat);	
    }
    public void removeCategories(String categoryName){
		
		Category cat = new Category(categoryName);
		String cat_1 = cat.removeTrailingStrings();
		qData.setCategoryTobeRemoved(cat_1);
		dbActions.deleteFromCategory(qData);		
		hashCategories.remove(categoryName);
		for (IListenEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryDeleted(cat);	
	}	
	/*public void removeCategories(String categoryString,int index){
		
		Category cat = new Category(categoryString);
		String categoryName = cat.removeTrailingStrings();
		qData.setCategoryTobeRemoved(categoryName);
		dbActions.deleteFromCategory(qData);
		for (IListenEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryDeleted(index);	
	}	*/
	/*public String[] GetAll(){
		String[] items = new String[categories.size()];
		int index=0;
		for(Category cat: categories){
		  items[index++] = cat.toString();
		}
		return items;
	}*/
	
	public String[] GetAll(){
	String[] items = new String[hashCategories.size()];	
    Iterator<String> keySetIterator = hashCategories.keySet().iterator();
	int index=0;	
	while(keySetIterator.hasNext()){
		String key = keySetIterator.next();
		Category cat = (Category)hashCategories.get(key);		
		items[index++] = cat.toString();	
		
	  }
	return items;
    }
	
	public void addListener(IListenEvents listener){
		categoryEventListeners.add(listener);	
	}
	public boolean isAccountExistforCategory(String categoryString){
		boolean isExist = false;
		Category cat = new Category(categoryString);
		String categoryName = cat.removeTrailingStrings();
		int cnt = dbActions.accountCount(categoryName);
		if(cnt>0){
			isExist = true;
		}		
		return isExist;
	}
}
