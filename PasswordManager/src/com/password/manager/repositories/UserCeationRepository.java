package com.password.manager.repositories;

import com.password.manager.bean.UserData;
import com.password.manager.util.PMUtilities;

public class UserCeationRepository {

	private UserData userData = new UserData();
	private PMUtilities pmUtil = new PMUtilities();
	private static UserCeationRepository userCeationRepository = new UserCeationRepository();
	
	public static UserCeationRepository getInstance(){
		return userCeationRepository;
	}
	
	UserCeationRepository(){}
	
	public void createUserData(String sno,String name,String userID,String password){
		System.out.println("Button Clicked...:-)");
		pmUtil.createUserProfile(sno, name, userID, password);
	}
	
}
