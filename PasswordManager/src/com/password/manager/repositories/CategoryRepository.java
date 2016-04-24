package com.password.manager.repositories;

import java.util.ArrayList;
import com.password.manager.bean.Category;
import com.password.manager.bean.QueryData;
import com.password.manager.dao.impl.DBActionsImpl;
import com.password.manager.listeners.IListenCategoryEvents;

public class CategoryRepository {
	
	private ArrayList<Category> categories = new ArrayList<Category>();	
	private QueryData qData = new QueryData(); 
	private DBActionsImpl dbActions = new DBActionsImpl();
	private static CategoryRepository _categoryRepository = new CategoryRepository();
	private ArrayList<IListenCategoryEvents> categoryEventListeners = new ArrayList<IListenCategoryEvents>();
	
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
				categories.add(cat);
			}
		}		
	}
	public void Add(String categoryName){
		qData.setSelectedCategory(categoryName);
		dbActions.insertIntocategory(qData);
		Category cat = new Category(categoryName);
		categories.add(cat);
		 for (IListenCategoryEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryAdded(cat);	
    }
	public void removeCategories(String categoryString,int index){
		
		Category cat = new Category(categoryString);
		String categoryName = cat.removeTrailingStrings();
		qData.setCategoryTobeRemoved(categoryName);
		dbActions.deleteFromCategory(qData);
		for (IListenCategoryEvents categoryEventListener : categoryEventListeners)
			 categoryEventListener.categoryDeleted(index);	
	}	
	public String[] GetAll(){
		String[] items = new String[categories.size()];
		int index=0;
		for(Category cat: categories){
		  items[index++] = cat.toString();
		}
		return items;
	}
	public void addListener(IListenCategoryEvents listener){
		categoryEventListeners.add(listener);	
	}
	public boolean isAccountExistforCategory(String categoryName){
		boolean isExist = false;
		int cnt = dbActions.accountCount(categoryName);
		if(cnt>0){
			isExist = true;
		}		
		return isExist;
	}
}
