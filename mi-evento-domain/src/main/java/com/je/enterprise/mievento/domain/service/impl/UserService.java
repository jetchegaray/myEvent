package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.UserEntity;
import com.je.enterprise.mievento.domain.service.Service;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@org.springframework.stereotype.Service
public class UserService {

	private CRUDHelper<UserEntity, ObjectId> crudHelper;
	
	@Autowired
	public UserService(CRUDHelper<UserEntity, ObjectId> crudHelper) {
		this.crudHelper = crudHelper;
	}

	public String create(UserEntity entity) {
		return crudHelper.create(entity);
	}

	public void update(UserEntity entity) {
		crudHelper.update(entity);
	}

	public void delete(ObjectId key) {
		crudHelper.delete(key);
	}
	
	public List<UserEntity> getAll() {
		return crudHelper.getAll();
	}
	
	public UserEntity findByMail(String mail){
		UserDAO userDAO = (UserDAO) this.crudHelper.getDao();
		return userDAO.findByMail(mail);
	}
	
}
