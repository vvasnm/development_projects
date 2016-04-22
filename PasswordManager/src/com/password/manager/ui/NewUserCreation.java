package com.password.manager.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import com.password.manager.bean.PMData;
import com.password.manager.util.Utilities;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewUserCreation extends Dialog {

	protected Object result;
	protected Shell shlNewUserCreation;
	private Text txtSNo;
	private Text txtName;
	private Text txtUserID;
	private Text txtPassword;
	private Composite cmpButtons;
	
	public NewUserCreation(Shell shlPasswordManager, int style){
		super(shlPasswordManager, style);		
	}
	public Object open() {
		createContents();
		shlNewUserCreation.open();
		shlNewUserCreation.layout();
		shlNewUserCreation.pack();
		Display display = getParent().getDisplay();
		/*Setting the dialog to center of the screen*/	
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlNewUserCreation.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlNewUserCreation.setLocation(x, y);
		while (!shlNewUserCreation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	private void createContents(){
		shlNewUserCreation = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.APPLICATION_MODAL);
		shlNewUserCreation.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		shlNewUserCreation.setSize(272, 255);
		shlNewUserCreation.setText("New User Creation Page");
		shlNewUserCreation.setLayout(new GridLayout(1, true));
		
		Composite cmpTitle = new Composite(shlNewUserCreation, SWT.NONE);
		cmpTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		cmpTitle.setLayout(new GridLayout(1, true));
		GridData gd_cmpTitle = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_cmpTitle.widthHint = 251;
		cmpTitle.setLayoutData(gd_cmpTitle);
		
		Label lblNewUserCreation = new Label(cmpTitle, SWT.NONE);
		lblNewUserCreation.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblNewUserCreation.setAlignment(SWT.CENTER);
		GridData gd_lblNewUserCreation = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_lblNewUserCreation.widthHint = 246;
		lblNewUserCreation.setLayoutData(gd_lblNewUserCreation);
		lblNewUserCreation.setText("New User Creation Page");
		
		Composite cmpDetails = new Composite(shlNewUserCreation, SWT.NONE);
		cmpDetails.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		cmpDetails.setLayout(new GridLayout(2, false));
		GridData gd_cmpDetails = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_cmpDetails.widthHint = 253;
		gd_cmpDetails.heightHint = 132;
		cmpDetails.setLayoutData(gd_cmpDetails);
		
		Label lblSno = new Label(cmpDetails, SWT.NONE);
		lblSno.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		lblSno.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblSno.setText("S.No");
		
		txtSNo = new Text(cmpDetails, SWT.BORDER);
		txtSNo.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_txtSNo = new GridData (SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_txtSNo.widthHint = 130;
		txtSNo.setLayoutData(gd_txtSNo);
		
		Label lblName = new Label(cmpDetails, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblName.setText("Name");
		
		txtName = new Text(cmpDetails, SWT.BORDER);
		txtName.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_txtName = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_txtName.widthHint = 130;
		txtName.setLayoutData(gd_txtName);
		
		Label lblUserId = new Label(cmpDetails, SWT.NONE);
		lblUserId.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblUserId.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblUserId.setText("User ID");
		
		txtUserID = new Text(cmpDetails, SWT.BORDER);
		txtUserID.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_txtUserID = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_txtUserID.widthHint = 130;
		txtUserID.setLayoutData(gd_txtUserID);
		
		Label lblPassword = new Label(cmpDetails, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblPassword.setText("Password");
		
		txtPassword = new Text(cmpDetails,SWT.PASSWORD | SWT.BORDER);
		txtPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		GridData gd_txtPassword = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_txtPassword.widthHint = 130;
		txtPassword.setLayoutData(gd_txtPassword);
		
		cmpButtons = new Composite(shlNewUserCreation, SWT.NONE);
		cmpButtons.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		cmpButtons.setLayout(new GridLayout(2, true));
		GridData gd_cmpButtons = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gd_cmpButtons.widthHint = 253;
		cmpButtons.setLayoutData(gd_cmpButtons);
// The action should check if there is already user exists (Unique id/user name/user id)	
		Button btnCreateUser = new Button(cmpButtons, SWT.NONE);
		btnCreateUser.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, true, 1, 1));
		btnCreateUser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				Shell shell  = new Shell();
				PMData pmdata = new PMData();	
				LoginPage1 page = new LoginPage1(shell, 0);
				Utilities util = new Utilities();
//Getting the values and setting the values to PMData Pojo.							
				setUserDetails(pmdata);
				util.createUserProfile(pmdata,shlNewUserCreation);
				shlNewUserCreation.close();
				page.open();	
			}
		});
		btnCreateUser.setText("Create");
		Button btnBack = new Button(cmpButtons, SWT.NONE);
		btnBack.setText("Back   ");
		btnBack.addSelectionListener(new SelectionListener() {			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				shlNewUserCreation.close();	
				Shell shell = new Shell();
				LoginPage1 page = new LoginPage1(shell, 0);
				page.open();
			}		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
			}
		});
	}
	private void setUserDetails(PMData pmdata) {
		pmdata.setNewUserSno(txtSNo.getText());
		pmdata.setNewUser(txtName.getText());
		pmdata.setNewUserID(txtUserID.getText());
		pmdata.setNewUserPassword(txtPassword.getText());		
	}
}
