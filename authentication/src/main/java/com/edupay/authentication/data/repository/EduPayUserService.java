package com.edupay.authentication.data.repository;

import com.edupay.authentication.data.model.EduPayUser;

public interface EduPayUserService {
	public EduPayUser getUserByUsername(String userName);

}
