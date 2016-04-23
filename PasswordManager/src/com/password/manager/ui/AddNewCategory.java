package com.password.manager.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import com.password.manager.repositories.CategoryRepository;
import com.password.manager.util.Constants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;

public class AddNewCategory extends Dialog {

	protected Object result;
	protected Shell shlAddNewCategory;
	private Text txtAddNewCategory;
	private Button btnSaveCategory,btnDeleteCategory;
	private Label lblNewLabel,lblNewCategory,lblCheckTheExisting;
	private Composite cmpButtons,cmpInstructions,cmpCategory;
	private List listExistingCategory;// this has to be propagated up
	  
	public AddNewCategory(Shell parent, int style) {
		super(parent, style);		
	}
	public Object open() {
		createContents();
		shlAddNewCategory.open();
		shlAddNewCategory.layout();
		Display display = getParent().getDisplay();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlAddNewCategory.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shlAddNewCategory.setLocation(x, y);
		while (!shlAddNewCategory.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	private void createContents() {	
		shlAddNewCategory = new Shell(getParent(), SWT.MIN | SWT.TITLE | SWT.PRIMARY_MODAL);
		shlAddNewCategory.setSize(450, 400);
		shlAddNewCategory.setText("Add New Category Dialog");
		shlAddNewCategory.setLayout(new GridLayout(1, false));
		
		cmpCategory = new Composite(shlAddNewCategory, SWT.NONE);
		cmpCategory.setLayout(new GridLayout(2, false));
		GridData gd_cmpCategory = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cmpCategory.heightHint = 239;
		gd_cmpCategory.widthHint = 434;
		cmpCategory.setLayoutData(gd_cmpCategory);
		
		lblNewLabel = new Label(cmpCategory, SWT.NONE);
		lblNewLabel.setText("Existing Category :");
		
		listExistingCategory = new List(cmpCategory, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_listExistingCategory = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_listExistingCategory.heightHint = 173;
		listExistingCategory.setLayoutData(gd_listExistingCategory);		
		listExistingCategory.setItems(CategoryRepository.getInstance().GetAll());
		lblNewCategory = new Label(cmpCategory, SWT.NONE);
		lblNewCategory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewCategory.setText("Add New Category :");
		
		txtAddNewCategory = new Text(cmpCategory, SWT.BORDER);
		txtAddNewCategory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		cmpInstructions = new Composite(shlAddNewCategory, SWT.NONE);
		cmpInstructions.setLayout(new GridLayout(1, false));
		GridData gd_cmpInstructions = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 2);
		gd_cmpInstructions.heightHint = 51;
		cmpInstructions.setLayoutData(gd_cmpInstructions);
		
		lblCheckTheExisting = new Label(cmpInstructions, SWT.NONE);
		GridData gd_lblCheckTheExisting = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblCheckTheExisting.heightHint = 39;
		gd_lblCheckTheExisting.widthHint = 427;
		lblCheckTheExisting.setLayoutData(gd_lblCheckTheExisting);
		lblCheckTheExisting.setText("1) Check the Existing Category from List above before creating it.\r\n2) Check is to avoid duplicates.\r\n\r\n");
		
		cmpButtons = new Composite(shlAddNewCategory, SWT.NONE);
		cmpButtons.setLayout(new GridLayout(10, false));
		GridData gd_cmpButtons = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_cmpButtons.widthHint = 436;
		gd_cmpButtons.heightHint = 53;
		cmpButtons.setLayoutData(gd_cmpButtons);
			
		btnSaveCategory = new Button(cmpButtons, SWT.NONE);
		GridData gd_btnSaveCategory = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSaveCategory.widthHint = 210;
		btnSaveCategory.setLayoutData(gd_btnSaveCategory);
		btnSaveCategory.setText("Click to Save Category");
		btnSaveCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				if((txtAddNewCategory.getText()!=null) && (txtAddNewCategory.getText()!=""))
				{
					CategoryRepository.getInstance().Add(txtAddNewCategory.getText());
					listExistingCategory.setItems(CategoryRepository.getInstance().GetAll());
					txtAddNewCategory.setText("");					
				}else{
					MessageBox mBox = new MessageBox(shlAddNewCategory);
					mBox.setMessage(Constants.EMPTY_FIELDS);
					mBox.open();
				}				
			}
		});
		btnDeleteCategory = new Button(cmpButtons, SWT.NONE);
		GridData gd_btnDeleteCategory = new GridData(SWT.RIGHT, SWT.RIGHT, false, false, 1, 1);
		gd_btnDeleteCategory.widthHint = 215;
		btnDeleteCategory.setLayoutData(gd_btnDeleteCategory);
		btnDeleteCategory.setText("Click to Delete Category");							
		btnDeleteCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox mBox = new MessageBox(shlAddNewCategory);
				mBox.setMessage("Under Construction...");
				mBox.open();
			}
		});						
	}	     
}
