package com.password.manager.repositories;


import com.password.manager.util.PMUtilities;

public class UserCeationRepository {
	
	private PMUtilities pmUtil = new PMUtilities();
	private static UserCeationRepository userCeationRepository = new UserCeationRepository();
	
	public static UserCeationRepository getInstance(){
		return userCeationRepository;
	}
	
	UserCeationRepository(){}
	
	public void createUserData(String sno,String name,String userID,String password){		
		pmUtil.createUserProfile(sno, name, userID, password);
	}
	
}
