package com.password.manager.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import com.password.manager.bean.Account;
import com.password.manager.bean.Category;
import com.password.manager.listeners.IListenEvents;
import com.password.manager.repositories.AccountRepository;
import com.password.manager.repositories.CategoryRepository;
import com.password.manager.util.Constants;
import com.password.manager.util.Utilities;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;

public class DetailsPage1 extends Dialog implements IListenEvents{
   	
	protected Object result;
	protected Shell shlDetails;
	private Text txtAccountName,txtUsername,txtPassword,txtRetypePassword,txtAcc,txtUname,txtPass;
	private Button btnAddAccount;
	private Composite cmpAddAccounts;
	private Combo cmbCategory;
	private List listAccounts;
	private Button btnEdit,btnDeleteAccInfo,btnManageCategory;
	private Label lblSelectTheCategory,lblExistingAccounts;	
	private List lstCategory;
	private Label lblExistingCategory;
	private Button btnUpdate;  
            
    Utilities util_1 = new Utilities();
    
	public DetailsPage1(Shell parent, int style) {
		super(parent, style);	
		CategoryRepository.getInstance().addListener(this);	
		AccountRepository.getInstance().addListener(this);
	}	
	public Object open() {
		createContents();
		shlDetails.open();
		shlDetails.layout();
		Display display = getParent().getDisplay();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlDetails.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlDetails.setLocation(x, y);
		while (!shlDetails.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	private void createContents() {								
		shlDetails = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE);
		shlDetails.setSize(623, 679);
		shlDetails.setText("password Vault");
		shlDetails.setLayout(new GridLayout(2, false));
				
		Composite cmpAccounts = new Composite(shlDetails, SWT.NONE);
		cmpAccounts.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		cmpAccounts.setLayout(new GridLayout(1, false));
		GridData gd_cmpAccounts = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cmpAccounts.heightHint = 400;
		gd_cmpAccounts.widthHint = 314;
		cmpAccounts.setLayoutData(gd_cmpAccounts);
		
		lblExistingCategory = new Label(cmpAccounts, SWT.NONE);
		lblExistingCategory.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblExistingCategory.setText("Existing Category");	

		lstCategory = new List(cmpAccounts, SWT.BORDER);
		GridData gd_lstCategory = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lstCategory.heightHint = 270;
		gd_lstCategory.widthHint = 296;
		lstCategory.setLayoutData(gd_lstCategory);
		lstCategory.setItems(CategoryRepository.getInstance().GetAll());		
		lstCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
			    if(lstCategory.getSelectionCount()>0){			    	
			    	Boolean isAccountExist =  CategoryRepository.getInstance().hasAnyAccounts(lstCategory.getSelection()[0]);
			    	if(isAccountExist){
			    		listAccounts.setItems(AccountRepository.getInstance().GetAll(lstCategory.getSelection()[0]));
				    	listAccounts.setEnabled(true);
			    	}
			    	else{
			    		listAccounts.removeAll();
			    		listAccounts.add(Constants.NO_ACCOUNTS);
			    		btnDeleteAccInfo.setEnabled(false);
			    	}			 	
			    }				
			}
		});			  
		lblSelectTheCategory = new Label(cmpAccounts, SWT.NONE);
		lblSelectTheCategory.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		GridData gd_lblSelectTheCategory = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSelectTheCategory.heightHint = 40;
		gd_lblSelectTheCategory.widthHint = 300;
		lblSelectTheCategory.setLayoutData(gd_lblSelectTheCategory);
		lblSelectTheCategory.setText("**Use \"Manage Cat\" Button to \r\nAdd/Delete Categories**");
		
		btnManageCategory = new Button(cmpAccounts, SWT.NONE);
		GridData gd_btnManageCategory = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnManageCategory.heightHint = 30;
		gd_btnManageCategory.widthHint = 89;
		btnManageCategory.setLayoutData(gd_btnManageCategory);
		btnManageCategory.setText("Manage Cat");
		btnManageCategory.addSelectionListener(new SelectionAdapter() {			
			public void widgetSelected(SelectionEvent e) {											
				AddNewCategory categoryDialog = new AddNewCategory(shlDetails, SWT.NONE);				
				categoryDialog.open();					
			}
		});		
		Composite comAccounts1 = new Composite(shlDetails, SWT.NONE);
		comAccounts1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		comAccounts1.setLayout(new GridLayout(1, false));
		GridData gd_comAccounts1 = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_comAccounts1.heightHint = 257;
		gd_comAccounts1.widthHint = 285;
		comAccounts1.setLayoutData(gd_comAccounts1);
		
		lblExistingAccounts = new Label(comAccounts1, SWT.NONE);
		lblExistingAccounts.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblExistingAccounts.setText("Existing Accounts ");
		
		listAccounts = new List(comAccounts1, SWT.BORDER);
		GridData gd_listAccounts = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listAccounts.widthHint = 260;
		gd_listAccounts.heightHint = 270;
		listAccounts.setLayoutData(gd_listAccounts);
		listAccounts.setEnabled(false);
		listAccounts.setToolTipText("Display Account Information ");
		listAccounts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {								
				if((listAccounts.getSelectionCount()>0)  ){
					String items [] = AccountRepository.getInstance().getAccountDetails(listAccounts.getSelection()[0]);
					txtAcc.setText(items[0]);
					txtUname.setText(items[1]);
					txtPass.setText(items[2]);					
					txtPass.setVisible(false);
					btnDeleteAccInfo.setEnabled(true);					
				}
				else{
					MessageBox mBox = new MessageBox(shlDetails);
					mBox.setMessage(Constants.VIEW_INFO_ERROR);
					mBox.open();
				}
			}
		});			
		Label lblSelectTheAccounts = new Label(comAccounts1, SWT.NONE);
		GridData gd_lblSelectTheAccounts = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSelectTheAccounts.widthHint = 270;
		lblSelectTheAccounts.setLayoutData(gd_lblSelectTheAccounts);
		lblSelectTheAccounts.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD | SWT.ITALIC));
		lblSelectTheAccounts.setText("**Use \"Delete\" Button to Delete \r\nAccount Details**");
		
		btnDeleteAccInfo = new Button(comAccounts1, SWT.NONE);
		GridData gd_btnDeleteAccInfo = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnDeleteAccInfo.heightHint = 30;
		btnDeleteAccInfo.setLayoutData(gd_btnDeleteAccInfo);
		btnDeleteAccInfo.setText("Delete");
		btnDeleteAccInfo.setEnabled(false);
		btnDeleteAccInfo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		cmpAddAccounts = new Composite(shlDetails, SWT.NONE);
		cmpAddAccounts.setLayout(new GridLayout(2, false));
		GridData gd_cmpAddAccounts = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_cmpAddAccounts.widthHint = 316;
		gd_cmpAddAccounts.heightHint = 220;
		cmpAddAccounts.setLayoutData(gd_cmpAddAccounts);
		new Label(cmpAddAccounts, SWT.NONE);
		
		Label lblAddNewAccount = new Label(cmpAddAccounts, SWT.NONE);
		lblAddNewAccount.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblAddNewAccount.setText("Add New Account");
		
		Label lblAccount0 = new Label(cmpAddAccounts, SWT.NONE);
		lblAccount0.setText("Account Name :");
		
		txtAccountName = new Text(cmpAddAccounts, SWT.BORDER);
		txtAccountName.setToolTipText("Enter Account Name");
		GridData gd_txtAccountName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtAccountName.heightHint = 20;
		txtAccountName.setLayoutData(gd_txtAccountName);
		txtAccountName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtAccountName!=null) && (txtAccountName.getText()!="")){
					txtUsername.setEnabled(true);					 	
				}
				else{
					txtUsername.setEnabled(false);
				}									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtAccountName!=null) && (txtAccountName.getText()!="")){
					txtUsername.setEnabled(true);					 	
				}
				else{
					txtUsername.setEnabled(false);				   
				}				    
			}
		});		
		Label lblUsername0 = new Label(cmpAddAccounts, SWT.NONE);
		lblUsername0.setText("Username :");
		
		txtUsername = new Text(cmpAddAccounts, SWT.BORDER);
		txtUsername.setToolTipText("Enter Username");
		GridData gd_txtUsername = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtUsername.heightHint = 20;
		txtUsername.setLayoutData(gd_txtUsername);
		txtUsername.setEnabled(false);
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtUsername!=null) && (txtUsername.getText()!="")){
					txtPassword.setEnabled(true);
					lstCategory.deselectAll();
					listAccounts.removeAll();
					btnDeleteAccInfo.setEnabled(false);	
				}
				else{
					txtPassword.setEnabled(false);
				}									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtUsername!=null) && (txtUsername.getText()!="")){
					txtPassword.setEnabled(true);
					lstCategory.deselectAll();
					listAccounts.removeAll();
				}
				else{
					txtPassword.setEnabled(false);				   
				}				    
			}
		});		
		Label lblPassword0 = new Label(cmpAddAccounts, SWT.NONE);
		lblPassword0.setText("Password :");
		
		txtPassword = new Text(cmpAddAccounts, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setToolTipText("Enter Password");
		GridData gd_txtPassword = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtPassword.heightHint = 20;
		txtPassword.setLayoutData(gd_txtPassword);
		txtPassword.setEnabled(false);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtPassword!=null) && (txtPassword.getText()!="")){
					txtRetypePassword.setEnabled(true);					 	
				}
				else{
					txtRetypePassword.setEnabled(false);
				}									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtPassword!=null) && (txtPassword.getText()!="")){
					txtRetypePassword.setEnabled(true);					 	
				}
				else{
					txtRetypePassword.setEnabled(false);				   
				}				    
			}
		});		
		Label lblReTypePassword = new Label(cmpAddAccounts, SWT.NONE);
		lblReTypePassword.setText("Re Type Pass :");
		
		txtRetypePassword = new Text(cmpAddAccounts, SWT.BORDER | SWT.PASSWORD);
		txtRetypePassword.setToolTipText("Retype password");
		GridData gd_txtRetypePassword = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtRetypePassword.heightHint = 20;
		txtRetypePassword.setLayoutData(gd_txtRetypePassword);
		txtRetypePassword.setEnabled(false);
		txtRetypePassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtRetypePassword!=null) && (txtRetypePassword.getText()!="")){
					cmbCategory.setEnabled(true);
					btnAddAccount.setEnabled(true);	
				}
				else{
					cmbCategory.setEnabled(false);	
					btnAddAccount.setEnabled(false);					
				   }									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtRetypePassword!=null) && (txtRetypePassword.getText()!="")){
					cmbCategory.setEnabled(true);
					btnAddAccount.setEnabled(true);	
				}
				else{
					cmbCategory.setEnabled(false);	
					btnAddAccount.setEnabled(false);
				}				    
			}
		});		
		Label lblCategory = new Label(cmpAddAccounts, SWT.NONE);
		lblCategory.setText("Category :");		
		cmbCategory = new Combo(cmpAddAccounts, SWT.READ_ONLY);
		cmbCategory.setToolTipText("Select  category");
		GridData gd_cmbCategory = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_cmbCategory.heightHint = 20;
		cmbCategory.setLayoutData(gd_cmbCategory);
		cmbCategory.setText("\"\"");
		cmbCategory.setEnabled(false);
		cmbCategory.setItems(CategoryRepository.getInstance().setAllCategories());		
																					
		btnAddAccount = new Button(cmpAddAccounts, SWT.NONE);
		btnAddAccount.setToolTipText("Click to Add Account to DB");
		GridData gd_btnAddAccount = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAddAccount.widthHint = 73;
		gd_btnAddAccount.heightHint = 30;
		btnAddAccount.setLayoutData(gd_btnAddAccount);
		btnAddAccount.setText("Add Acc");
		btnAddAccount.setEnabled(false);
		new Label(cmpAddAccounts, SWT.NONE);
		btnAddAccount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(isDataNotNull()){
					if(isRetypePasswordMatched()){						
						AccountRepository.getInstance().addAccount(txtAccountName.getText().toUpperCase(),
								                                   txtUsername.getText().toUpperCase(), 
								                                   txtPassword.getText().toUpperCase(),
								                                   cmbCategory.getText().toUpperCase());
						
						clearWidgets();
					}
					else{
						MessageBox mBox = new MessageBox(shlDetails);
						mBox.setMessage(Constants.PASSWORD_MATCH);
						mBox.open();
						clearWidgets();
					}						
				}
				else{
					MessageBox mBox = new MessageBox(shlDetails);
					mBox.setMessage(Constants.EMPTY_FIELDS);
					mBox.open();
				}	
			}
		});
		Composite cmpView = new Composite(shlDetails, SWT.NONE);
		cmpView.setLayout(new GridLayout(2, false));
		GridData gd_cmpView = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_cmpView.widthHint = 281;
		gd_cmpView.heightHint = 224;
		cmpView.setLayoutData(gd_cmpView);
		new Label(cmpView, SWT.NONE);
		
		Label lblViewSelectedAccount = new Label(cmpView, SWT.NONE);
		lblViewSelectedAccount.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblViewSelectedAccount.setText("View Selected Account");
		
		Label lblAccount1 = new Label(cmpView, SWT.NONE);
		lblAccount1.setText("Account :");
		
		txtAcc = new Text(cmpView, SWT.BORDER | SWT.READ_ONLY);
		txtAcc.setEnabled(false);
		txtAcc.setEditable(false);
		GridData gd_txtAcc = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtAcc.widthHint = 220;
		gd_txtAcc.heightHint = 20;
		txtAcc.setLayoutData(gd_txtAcc);
		
		Label lblUsername1 = new Label(cmpView, SWT.NONE);
		lblUsername1.setText("Username :");
		
		txtUname = new Text(cmpView, SWT.BORDER | SWT.READ_ONLY);
		txtUname.setEnabled(false);
		txtUname.setEditable(false);
		GridData gd_txtUname = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtUname.heightHint = 20;
		txtUname.setLayoutData(gd_txtUname);
		
		Label lblPassword1 = new Label(cmpView, SWT.NONE);
		lblPassword1.setText("Password :");
		
		txtPass = new Text(cmpView, SWT.BORDER | SWT.READ_ONLY);
		txtPass.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		txtPass.setForeground(SWTResourceManager.getColor(SWT.COLOR_INFO_FOREGROUND));
		txtPass.setEditable(true);
		GridData gd_txtPass = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtPass.widthHint = 140;
		gd_txtPass.heightHint = 20;
		txtPass.setLayoutData(gd_txtPass);
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtPass!=null) && (txtPass.getText()!="")){
					btnUpdate.setEnabled(true);					 	
				}
				else{
					btnUpdate.setEnabled(false);						
				}									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtPass!=null) && (txtPass.getText()!="")){
					btnUpdate.setEnabled(true);					 	
				}
				else{
					btnUpdate.setEnabled(false);						
				}				    
			}
		});		
		btnEdit = new Button(cmpView, SWT.CHECK);
		GridData gd_btnEdit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnEdit.heightHint = 30;
		btnEdit.setLayoutData(gd_btnEdit);
		btnEdit.setToolTipText("Check to Edit the Username and passoword");
		btnEdit.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		btnEdit.setText("Edit ");
		btnEdit.addSelectionListener(new SelectionAdapter() {			
			public void widgetSelected(SelectionEvent e) {				
				Button btn = (Button) e.getSource();
				if(btn.getSelection()){								
					txtPass.setEnabled(true);
					txtPass.setVisible(true);									
				}
				else{
					txtPass.setEnabled(false);				
					txtPass.setVisible(false);
					btnUpdate.setEnabled(false);	
				}
			}
		});
		txtPass.setEnabled(false);		
		Label lblUseAboveCheck = new Label(cmpView, SWT.NONE);
		GridData gd_lblUseAboveCheck = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblUseAboveCheck.widthHint = 184;
		gd_lblUseAboveCheck.heightHint = 60;
		lblUseAboveCheck.setLayoutData(gd_lblUseAboveCheck);
		lblUseAboveCheck.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		lblUseAboveCheck.setText("1) \"Edit\" Chkbox to Edit \r\nPassword\r\n2) \"Update\" to Update");							
		
		btnUpdate = new Button(cmpView, SWT.NONE);
		btnUpdate.setEnabled(false);
		/*btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DBActionsImpl dbActions_2 = new DBActionsImpl();
				QueryData qData_1 = new QueryData(); 
				if((txtPass.getText()!=null) && (txtPass.getText()!="")){
					int cnt = listAccounts.getSelectionCount();
					if(cnt>0){
						String [] selectedAccount = listAccounts.getSelection();
						qData_1.setModifiedPassword(txtPass.getText());
						qData_1.setSelectedAccount(selectedAccount[0]);
						dbActions_2.updateAccount(qData_1);
						clearWidgets();
					}					
				}
				else{
					MessageBox mBox = new MessageBox(shlDetails);
					mBox.setMessage(Constants.EMPTY_FIELDS);
					mBox.open();
				}				
			}
		});*/
		btnUpdate.setText("Update");
		new Label(cmpView, SWT.NONE);
	}	
	private void clearWidgets(){
		txtAccountName.setText("");
		cmbCategory.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
		txtRetypePassword.setText("");
		txtAcc.setText("");
		txtUname.setText("");
		txtPass.setText("");	
	}
	
	private boolean isDataNotNull(){
		boolean isNotNull = false;		
		if((txtAccountName.getText()!=null) && ((txtAccountName.getText()!=""))
			&& (cmbCategory.getText()!=null) && (cmbCategory.getText()!="")
			&& (txtUsername.getText()!=null) && (txtUsername.getText()!="") 
			&& (txtPassword.getText()!=null) && (txtPassword.getText()!="")
			&& (txtRetypePassword.getText()!=null) && (txtRetypePassword.getText()!="")) {
			isNotNull = true;			
		}					
		return isNotNull;
	}	
	private boolean isRetypePasswordMatched(){
		boolean isPasswordMatched = false;		
		if (txtPassword.getText().equals(txtRetypePassword.getText())){
			isPasswordMatched = true;			
		}		
		return isPasswordMatched;
	}	
	
	@Override
	public void categoryAdded(Category cat) {
		lstCategory.add(cat.toString(), lstCategory.getItemCount());	
		cmbCategory.add(cat.getName().toUpperCase());
	}
	@Override
	public void categoryDeleted(String formattedCategoryName) {
	      lstCategory.remove(formattedCategoryName);
	      if(formattedCategoryName!=null){
				String delimiter = " [(] ";
				String [] category =  formattedCategoryName.split(delimiter);
				cmbCategory.remove(category[0]);
			}      
	}
	@Override
	public void accountAdded(Account account,Category cat) {		
		String catName = cat.getName();
		String [] Items = lstCategory.getItems();
		for (String itm : Items){
			if(itm.startsWith(catName))
			{									
				lstCategory.add(cat.accountCount(), lstCategory.indexOf(itm));				
				lstCategory.remove(itm);
				
				
			} 
		}						
	}
		
	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void viewAccountDetails(Account account) {
		// TODO Auto-generated method stub
		
	}		
}

