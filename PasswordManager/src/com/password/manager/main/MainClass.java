package com.password.manager.main;

import org.eclipse.swt.widgets.Shell;

import com.password.manager.dao.impl.CreateTablesImpl;
import com.password.manager.ui.DetailsPage1;
//import com.password.manager.ui.LoginPage1;
public class MainClass {
	public static void main(String[] args) {									
		CreateTablesImpl createtables = new CreateTablesImpl();
		createtables.CreateAllTables();		
		Shell shell = new Shell();	
		//LoginPage1 login = new LoginPage1(shell, 0);
		DetailsPage1 login = new DetailsPage1(shell, 0);
		login.open();		
	}
}
