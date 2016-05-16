package com.password.manager.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.events.EventException;

import com.password.manager.bean.Account;
import com.password.manager.bean.Category;
import com.password.manager.listeners.IListenEvents;
import com.password.manager.repositories.AccountRepository;
import com.password.manager.repositories.CategoryRepository;
import com.password.manager.util.Constants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;

public class AddNewCategory extends Dialog implements IListenEvents{

	protected Object    result;
	protected Shell     shlAddNewCategory;
	private   Text      txtAddNewCategory;
	private   Button    btnSaveCategory,btnDeleteCategory;
	private   Label     lblNewLabel,lblNewCategory,lblCheckTheExisting;
	private   Composite cmpButtons,cmpInstructions,cmpCategory;
	private   List      listExistingCategory;// this has to be propagated up
	  
	public AddNewCategory(Shell parent, int style) {
		super(parent, style);
		AccountRepository.getInstance().addListener(this);
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
		listExistingCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				btnDeleteCategory.setEnabled(true);		
			}
		});
		
		
		
		lblNewCategory = new Label(cmpCategory, SWT.NONE);
		lblNewCategory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewCategory.setText("Add New Category :");
		
		txtAddNewCategory = new Text(cmpCategory, SWT.BORDER);
		txtAddNewCategory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtAddNewCategory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {								
				if((txtAddNewCategory!=null) && (txtAddNewCategory.getText()!="")){
					btnSaveCategory.setEnabled(true);					 	
				}
				else{
					btnSaveCategory.setEnabled(false);						
				}									
			}
			public void keyReleased(KeyEvent ke) {
				if((txtAddNewCategory!=null) && (txtAddNewCategory.getText()!="")){
					btnSaveCategory.setEnabled(true);					 	
				}
				else{
					btnSaveCategory.setEnabled(false);						
				}				    
			}
		});	
		
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
		lblCheckTheExisting.setText(Constants.CATEGORY_INSTRUCTIONS);
		
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
		btnSaveCategory.setText(Constants.SAVE_CATEGORY_TOOLTIP);
		btnSaveCategory.setEnabled(false);
		btnSaveCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {			
				try{
					if((txtAddNewCategory.getText()!=null) && (txtAddNewCategory.getText()!=""))
					{
						CategoryRepository.getInstance().Add(txtAddNewCategory.getText());
						listExistingCategory.setItems(CategoryRepository.getInstance().GetAll());
						txtAddNewCategory.setText("");
						btnSaveCategory.setEnabled(false);
						
					}else{
						MessageBox mBox = new MessageBox(shlAddNewCategory);
						mBox.setMessage(Constants.EMPTY_FIELDS);
						mBox.open();
					}	
					
				}catch(EventException ex){
					ex.printStackTrace();
				}
							
			}
		});
		btnDeleteCategory = new Button(cmpButtons, SWT.NONE);
		GridData gd_btnDeleteCategory = new GridData(SWT.RIGHT, SWT.RIGHT, false, false, 1, 1);
		gd_btnDeleteCategory.widthHint = 215;
		btnDeleteCategory.setLayoutData(gd_btnDeleteCategory);
		btnDeleteCategory.setText(Constants.DELETE_CATEGORY_TIP);	
		btnDeleteCategory.setEnabled(false);
		btnDeleteCategory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){				
				if(listExistingCategory.getSelectionCount()>0){					
					String formattedCategoryName =  listExistingCategory.getSelection()[0];					
					if(!CategoryRepository.getInstance().hasAnyAccounts(formattedCategoryName)){
						CategoryRepository.getInstance().removeCategories(formattedCategoryName);
						listExistingCategory.remove(listExistingCategory.getSelectionIndex());
					}
					else{
						MessageBox mBox = new MessageBox(shlAddNewCategory);
						mBox.setMessage(Constants.CATEGORY_HAS_ACCOUNTS);
						mBox.open();
					}
				}
				else{
					MessageBox mBox = new MessageBox(shlAddNewCategory);
					mBox.setMessage(Constants.SELECT_CATEGORY);
					mBox.open();
				}								
			}
		});						
	}
	@Override
	public void categoryAdded(Category category) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void categoryDeleted(String formattedCategoryName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void accountAdded(Account account, Category category) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAccount(String accountName,Category category) {		
		String catName = category.getName();
		System.out.println("Cat Name..." +  catName);
		String [] Items = listExistingCategory.getItems();
		for (String itm : Items){
			if(itm.startsWith(catName)){									
				listExistingCategory.add(category.accountCount(), listExistingCategory.indexOf(itm));				
				listExistingCategory.remove(itm);								
			} 
		}	
	}
}
