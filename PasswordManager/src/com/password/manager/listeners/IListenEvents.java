package com.password.manager.listeners;

import com.password.manager.bean.Account;
import com.password.manager.bean.Category;

public interface IListenEvents {
	void categoryAdded(Category category);	
	void categoryDeleted(String formattedCategoryName);
	void accountAdded(Account account, Category category); 
	void deleteAccount(String accountName,Category category);
	
}
