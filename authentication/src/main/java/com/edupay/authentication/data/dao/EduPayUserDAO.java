package com.edupay.authentication.data.dao;

import com.edupay.authentication.data.model.EduPayUser;

public interface EduPayUserDAO {
	
	public EduPayUser getUserbyUserName(String userName);
	
	

}
