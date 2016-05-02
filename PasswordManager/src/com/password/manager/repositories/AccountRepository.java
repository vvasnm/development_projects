package com.password.manager.repositories;

import java.util.ArrayList;
import java.util.Iterator;

import com.password.manager.bean.Account;
import com.password.manager.bean.Category;
import com.password.manager.bean.QueryData;
import com.password.manager.dao.impl.DBActionsImpl;
import com.password.manager.listeners.IListenEvents;

public class AccountRepository {

	private ArrayList<Account> Accounts = new ArrayList<Account>();	
	private QueryData qData = new QueryData(); 	
	private DBActionsImpl dbActions = new DBActionsImpl();
	private static AccountRepository accountRepository = new AccountRepository();
	private ArrayList<IListenEvents> eventListeners = new ArrayList<IListenEvents>();
	
	public static AccountRepository getInstance(){
		return accountRepository;
	}	
	private AccountRepository(){
		qData = dbActions.getCategoriesFromDB();
		if(qData.getCategory()!=null){		
			for(String itm: qData.getCategory()){								
				int cnt = dbActions.accountCount(itm);
				if(cnt>0){
				 qData = dbActions.getExistingAccounts(itm);
				 if(qData.getAccount()!=null){
					 for(String account: qData.getAccount()){
						 Account acc = new Account(account);
						 Accounts.add(acc);
					 }
				 }										
				}				
			}
		}		
	}
	
	public void addAccount(String accountName, String accUsername,String accPassword, String accCategory){
		Account account = new Account(accountName);
		account.setNewAccountName(accountName);
		account.setNewAccountUsername(accUsername);
		account.setNewAccountPassword(accPassword);
		account.setNewAccountCategory(accCategory);
		dbActions.createNewAccount(account);		
		Accounts.add(account);
		 for (IListenEvents eventListener : eventListeners){
			 eventListener.accountAdded(account);
		 }			 
    }
	public void removeAccount (Account account){
		
	}
	public String[] GetAll(){
		String[] items = new String[Accounts.size()];
		int index=0;
		for(Account acc : Accounts )
		{
			items[index++] = acc.getNewAccountName();
		}    
		return items;
    }
	public void getAccountDetails(String formattedCatString){
		
		
		
	}
	
	boolean isAccountAlreadyExist(String cagegoryName,String accountName){
		boolean isExist = false;				
		qData = dbActions.getExistingAccounts(cagegoryName);
		String [] accounts = qData.getAccount();
		if(accounts!=null){
			for(String acc : accounts ){
				if(acc.equals(accountName)){
					isExist = true;
					break;
				}
			}	
		}		
		return isExist;	
	}
}
