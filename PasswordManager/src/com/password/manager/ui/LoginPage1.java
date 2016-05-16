package com.password.manager.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.wb.swt.SWTResourceManager;
import com.password.manager.bean.UserData;
import com.password.manager.util.Constants;
import com.password.manager.util.DBConstants;
import com.password.manager.util.PMUtilities;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.ResourceManager;

public class LoginPage1 extends Dialog {

	protected Object result;
	protected Shell shlPasswordVault;
	private Text txtUsername,txtPassword;
	private Button btnLogin;
	private Composite cmpMain,comDetails,cmpNewUserSignup;
	private Label lblUsername,lblpassword,lblPasswordVault,lblNewUser;
	private Link lnkSignup;
	
	public LoginPage1(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}	
	PMUtilities util_1 = new PMUtilities();
	public Object open() {
		createContents();
		shlPasswordVault.open();
		shlPasswordVault.layout();
		Display display = getParent().getDisplay();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlPasswordVault.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;		
		shlPasswordVault.setLocation(x, y);
		while (!shlPasswordVault.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	private void createContents() {
		shlPasswordVault = new Shell(getParent(), SWT.CLOSE | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shlPasswordVault.setSize(412, 342);
		shlPasswordVault.setText("password VAULT");
		shlPasswordVault.setLayout(new GridLayout(1, false));
//cmpMain		
		cmpMain = new Composite(shlPasswordVault, SWT.NONE);
		cmpMain.setLayout(new GridLayout(1, false));
		GridData gd_cmpMain = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_cmpMain.heightHint = 305;
		gd_cmpMain.widthHint = 396;
		cmpMain.setLayoutData(gd_cmpMain);
//lblPasswordVault		
		lblPasswordVault = new Label(cmpMain, SWT.NONE);
		lblPasswordVault.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblPasswordVault.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblPasswordVault.setText("PASSWORD VAULT");
//comDetails		
		comDetails = new Composite(cmpMain, SWT.NONE);
		comDetails.setLayout(new GridLayout(2, false));
		GridData gd_comDetails = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_comDetails.heightHint = 108;
		comDetails.setLayoutData(gd_comDetails);
		new Label(comDetails, SWT.NONE);
		new Label(comDetails, SWT.NONE);
//lblUsername		
		lblUsername = new Label(comDetails, SWT.NONE);
		lblUsername.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username :");
//txtUsername		
		txtUsername = new Text(comDetails, SWT.BORDER);
		GridData gd_txtUsername = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtUsername.widthHint = 265;
		txtUsername.setLayoutData(gd_txtUsername);
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtUsername!=null) && (txtUsername.getText()!="")){
					txtPassword.setEnabled(true);					 	
				}
				else{
					txtPassword.setEnabled(false);
				   }									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtUsername!=null) && (txtUsername.getText()!="")){
					txtPassword.setEnabled(true);					 	
				}
				else{
					txtPassword.setEnabled(false);				   
				}				    
			}
		});
//lblpassword		
		lblpassword = new Label(comDetails, SWT.NONE);
		lblpassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblpassword.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblpassword.setText("Password :");
//txtPassword		
		txtPassword = new Text(comDetails, SWT.BORDER | SWT.PASSWORD);
		GridData gd_txtPassword = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtPassword.widthHint = 251;
		txtPassword.setLayoutData(gd_txtPassword);
		txtPassword.setEnabled(false);
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtPassword!=null) && (txtPassword.getText()!="")){
					btnLogin.setEnabled(true);					 	
				}
				else{
					btnLogin.setEnabled(false);
				   }									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtPassword!=null) && (txtPassword.getText()!="")){
					btnLogin.setEnabled(true);					 	
				}
				else{
					btnLogin.setEnabled(false);				   
				}				    
			}
		});
//cmpNewUserSignup		
		cmpNewUserSignup = new Composite(cmpMain, SWT.NONE);
		cmpNewUserSignup.setLayout(new GridLayout(2, false));
		GridData gd_cmpNewUserSignup = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_cmpNewUserSignup.heightHint = 62;
		cmpNewUserSignup.setLayoutData(gd_cmpNewUserSignup);
//lblNewUser	
		lblNewUser = new Label(cmpNewUserSignup, SWT.NONE);
		lblNewUser.setText("New User?");
//Link lnkSignup		
		lnkSignup = new Link(cmpNewUserSignup, SWT.NONE);
		lnkSignup.setText("<a>Sign Up here</a>");
		lnkSignup.addSelectionListener(new SelectionAdapter() {			
			public void widgetSelected(SelectionEvent e) {				
				Shell shell = new Shell();
				//NewUserCreation newuser = new NewUserCreation(shell, 0);
				NewUserCreationPage newuser = new NewUserCreationPage(shell, 0);
				shlPasswordVault.close();
				newuser.open();	
			}
		});
//btnLogin		
		btnLogin = new Button(cmpMain, SWT.NONE);
		btnLogin.setImage(ResourceManager.getPluginImage("PasswordManager", "icons/lock.gif"));
		btnLogin.setToolTipText("Enter username & password to login to password VAULT ");
		GridData gd_btnLogin = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnLogin.widthHint = 121;
		btnLogin.setLayoutData(gd_btnLogin);
		btnLogin.setText("Login Here");	
		btnLogin.setEnabled(false);
		btnLogin.addSelectionListener(new SelectionAdapter() {			
			public void widgetSelected(SelectionEvent e) {				
				Shell shell = new Shell();
				UserData pmdata = new UserData();
				
				DetailsPage1 mPage = new DetailsPage1(shell, 0);								
				pmdata.setPassword(txtPassword.getText());				
				pmdata.setUsername(txtUsername.getText());															
				String folder = System.getenv("TEMP");
				int rowsCnt = util_1.rowCount(DBConstants.USER_TABLE);
				if(rowsCnt>0){
					if (util_1.isUserValid(pmdata)) {						
						shlPasswordVault.close();													
						if(shell!=null){
						    mPage.open();
							util_1.writeLogFile("Login Successful,Opening the details page.");
							util_1.clearFiles(folder);	
						}						
					}	
					else {
						util_1.writeLogFile("\nIncorrect Passowrd.");
						txtPassword.setText("");
						txtUsername.setText("");
						MessageBox mBox = new MessageBox(shlPasswordVault, 0);
						mBox.setMessage(Constants.INCORRECT_CREDNTIALS);
						mBox.open();						
					}
				}
				else{
					util_1.writeLogFile("\nNo User Exist.");
					txtPassword.setText("");
					txtUsername.setText("");
					MessageBox mBox = new MessageBox(shlPasswordVault, 0);
					mBox.setMessage(Constants.NO_DATA_ERROR);
					mBox.open();
				}	
			}
		});	
	}
}
