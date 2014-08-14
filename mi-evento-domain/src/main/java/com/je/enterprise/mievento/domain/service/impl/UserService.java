package com.je.enterprise.mievento.domain.service.impl;

import org.bson.types.ObjectId;

import com.je.enterprise.mievento.domain.entity.UserEntity;
import com.je.enterprise.mievento.domain.service.Service;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;


public class UserService implements Service<UserEntity, ObjectId>{

	private CRUDHelper<UserEntity, ObjectId> crudHelper;
	
	public String create(UserEntity entity) {
		return crudHelper.create(entity);
	}

	public void update(UserEntity entity) {
		crudHelper.update(entity);
	}

	public void delete(ObjectId key) {
		crudHelper.delete(key);
	}

}
