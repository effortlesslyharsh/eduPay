package com.edupay.authentication.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edupay.authentication.data.dao.EduPayUserDAO;
import com.edupay.authentication.data.model.EduPayUser;
@Service("userService")
@Transactional
public class EduPayUserServiceImpl implements EduPayUserService,UserDetailsService{

	@Autowired
	private EduPayUserDAO eduPayUserDAO;
	@Override
	public EduPayUser getUserByUsername(String userName) {
		return eduPayUserDAO.getUserbyUserName(userName);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return eduPayUserDAO.getUserbyUserName(username);
	}

}
