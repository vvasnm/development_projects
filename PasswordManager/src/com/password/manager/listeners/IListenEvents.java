package com.password.manager.listeners;

import com.password.manager.bean.Account;
import com.password.manager.bean.Category;

public interface IListenEvents {
	void categoryAdded(Category category);
	//void categoryDeleted(int index);
	void categoryDeleted(Category category);
	void accountAdded(Account account); 
	void deleteAccount(Account account);
	void viewAccountDetails(Account account);
}
