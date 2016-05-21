package com.password.manager.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import com.password.manager.repositories.UserCeationRepository;
import com.password.manager.util.Constants;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.ResourceManager;

public class NewUserCreationPage extends Dialog {

	protected Object result;
	protected Shell shlNewUserCreation;
	private Text txtSno;
	private Text txtName;
	private Text txtUserID;
	private Text txtPassword;
	private Text txtRetypePassword;
	private Text text;

	public NewUserCreationPage(Shell parent, int style) {
		super(parent, style);		
	}
	
	public Object open() {
		createContents();
		shlNewUserCreation.open();
		shlNewUserCreation.layout();
		Display display = getParent().getDisplay();
		/*Setting the dialog to center of the screen*/	
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlNewUserCreation.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlNewUserCreation.setLocation(x, y);
		new Label(shlNewUserCreation, SWT.NONE);
		while (!shlNewUserCreation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	private void createContents() {
		shlNewUserCreation = new Shell(getParent(), SWT.BORDER | SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shlNewUserCreation.setSize(500, 413);
		shlNewUserCreation.setText("New User Creation Page");
		shlNewUserCreation.setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(shlNewUserCreation, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 50;
		gd_composite.widthHint = 434;
		composite.setLayoutData(gd_composite);
		
		Label lblNewUserCreation = new Label(composite, SWT.NONE);
		lblNewUserCreation.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblNewUserCreation.setBounds(107, 10, 241, 30);
		lblNewUserCreation.setText("New User Creation Page");
		
		Button btnBack = new Button(shlNewUserCreation, SWT.NONE);
		btnBack.setImage(ResourceManager.getPluginImage("PasswordManager", "icons/home2.gif"));
		btnBack.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		GridData gd_btnBack = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnBack.heightHint = 31;
		gd_btnBack.widthHint = 31;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				shlNewUserCreation.close();	
				Shell shell = new Shell();
				LoginPage1 page = new LoginPage1(shell, 0);
				page.open();	
			}
		});
		Composite composite_1 = new Composite(shlNewUserCreation, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.heightHint = 255;
		composite_1.setLayoutData(gd_composite_1);
		
		Label lblSno = new Label(composite_1, SWT.NONE);
		GridData gd_lblSno = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblSno.widthHint = 95;
		lblSno.setLayoutData(gd_lblSno);
		lblSno.setText(" S.No :");
		
		txtSno = new Text(composite_1, SWT.BORDER);
		GridData gd_txtSno = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtSno.widthHint = 275;
		txtSno.setLayoutData(gd_txtSno);
		
		Label lblName = new Label(composite_1, SWT.NONE);
		lblName.setText("   Name :");
		
		txtName = new Text(composite_1, SWT.BORDER);
		GridData gd_txtName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtName.widthHint = 275;
		txtName.setLayoutData(gd_txtName);
		
		Label lblUserID = new Label(composite_1, SWT.NONE);
		lblUserID.setAlignment(SWT.CENTER);
		lblUserID.setText("   User Name :");
		
		txtUserID = new Text(composite_1, SWT.BORDER);
		GridData gd_txtUserID = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtUserID.widthHint = 275;
		txtUserID.setLayoutData(gd_txtUserID);
		
		Label lblPassword = new Label(composite_1, SWT.NONE);
		lblPassword.setAlignment(SWT.CENTER);
		lblPassword.setText("   Password :");
		
		txtPassword = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		GridData gd_txtPassword = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtPassword.widthHint = 275;
		txtPassword.setLayoutData(gd_txtPassword);
		
		Label lblRetypepassword = new Label(composite_1, SWT.NONE);
		lblRetypepassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRetypepassword.setText("   Re Password :");
		
		txtRetypePassword = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		txtRetypePassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		
		Label lblQuestion1 = new Label(composite_1, SWT.NONE);
		lblQuestion1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblQuestion1.setText("Security Questions");
		
		Label lblQuestion_1 = new Label(composite_1, SWT.NONE);
		lblQuestion_1.setText("   Question :");
		
		Combo combo = new Combo(composite_1, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblAnswer_1 = new Label(composite_1, SWT.NONE);
		lblAnswer_1.setText("   Answer :");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shlNewUserCreation, SWT.NONE);
		
		Button btnCreate = new Button(shlNewUserCreation, SWT.NONE);
		GridData gd_btnCreate = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_btnCreate.widthHint = 103;
		btnCreate.setLayoutData(gd_btnCreate);
		btnCreate.setText("Create User");
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				if(isDataNotNull()){
					if(isRetypePasswordMatched()){
						UserCeationRepository.getInstance().createUserData(txtSno.getText(), txtName.getText(), txtUserID.getText(), txtPassword.getText());
						clearWidgets();
					}
					else{
						MessageBox mBox = new MessageBox(shlNewUserCreation);
						mBox.setMessage(Constants.PASSWORD_MATCH);
						mBox.open();
						clearWidgets();
					}
				}
				else{
					MessageBox mBox = new MessageBox(shlNewUserCreation);
					mBox.setMessage(Constants.EMPTY_FIELDS);
					mBox.open();
					clearWidgets();
				}	
			}
		});

	}
	
	private boolean isDataNotNull(){
		boolean isNotNull = false;		
		if((txtSno.getText()!=null) && ((txtSno.getText()!=""))
			&& (txtName.getText()!=null) && (txtName.getText()!="")
			&& (txtUserID.getText()!=null) && (txtUserID.getText()!="") 
			&& (txtPassword.getText()!=null) && (txtPassword.getText()!="")
			&& (txtRetypePassword.getText()!=null) && (txtRetypePassword.getText()!="")){
			
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
	
	private void clearWidgets(){
		txtSno.setText("");
		txtName.setText("");
		txtUserID.setText("");
		txtPassword.setText("");
		txtRetypePassword.setText("");			
	}
}
