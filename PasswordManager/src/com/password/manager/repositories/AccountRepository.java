package com.password.manager.repositories;

import java.util.ArrayList;
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
	private AccountRepository(){}
	
	public void addAccount(String accountName, String accUsername,String accPassword, String accCategory){
		Account account = new Account(accountName);
		account.setNewAccountName(accountName);
		account.setNewAccountUsername(accUsername);
		account.setNewAccountPassword(accPassword);
		account.setNewAccountCategory(accCategory);
		Category cat = new Category(accCategory);
		if(!isAccountAlreadyExist(account.getNewAccountCategory(),account.getNewAccountName())){
			dbActions.createNewAccount(account);		
			Accounts.add(account);
			 for (IListenEvents eventListener : eventListeners){
				 eventListener.accountAdded(account,cat);
			 }
		}
					 
    }
	public void removeAccount (Account account){
		
	}	
	public String[] GetAll(String formattedCategory){
		String delimiter = " [(] ";
		String [] cat = formattedCategory.split(delimiter);		
		qData = dbActions.getExistingAccounts(cat[0]);
		String [] items = new String [qData.getAccount().length];
		int index=0;
		if(qData.getAccount()!=null){		 
	    	for(String account: qData.getAccount()){				    		 											 
				 items[index++] = account;				 																		 
		    }
	    }			  
		return items;
    }
	public String [] getAccountDetails(String account){
		Account  Acc =  dbActions.getAccountData(account);
		String [] items = new String [3];
		items[0] = Acc.getNewAccountName();
		items[1] = Acc.getNewAccountUsername();
		items[2] = Acc.getNewAccountPassword();	
		return items;
	}
	
	private boolean isAccountAlreadyExist(String cagegoryName,String accountName){
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
