package com.edupay.authentication.data.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.edupay.authentication.data.model.EduPayUser;
@Repository("eduPayUserDAO")
public class EduPayUserDAOImpl extends EduPayCommonDAO  implements EduPayUserDAO{

	@Override
	public EduPayUser getUserbyUserName(String userName) {
		StringBuilder stringBuilder = new StringBuilder(300);
		stringBuilder.append(" FROM EduPayUser where userName = :name ");
		Query<EduPayUser> query = getSession().createQuery(stringBuilder.toString(), EduPayUser.class);
		query.setParameter("name", userName);
		List<EduPayUser> userlist = query.list();
		if(!CollectionUtils.isEmpty(userlist)) {
			return userlist.get(0);
		}
		return null;
	}

}
