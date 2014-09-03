package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.UserEntity;
import com.je.enterprise.mievento.domain.exception.InvalidCredentialException;
import com.je.enterprise.mievento.domain.exception.InvalidPasswordException;
import com.je.enterprise.mievento.domain.exception.UserAlredyExistsException;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@org.springframework.stereotype.Service
public class UserService {

	private CRUDHelper<UserEntity, ObjectId> crudHelper;

	@Autowired
	public UserService(CRUDHelper<UserEntity, ObjectId> crudHelper) {
		this.crudHelper = crudHelper;
	}

	public void signUp(String email, String password) {
		if (findByMail(email) == null) {
			crudHelper.create(new UserEntity(email, password, true));
		}
		throw new UserAlredyExistsException();
	}

	
	public String login(String mail, String password) {

		UserEntity userEntity = this.findByMail(mail);
		try {
			Validate.notNull(userEntity);

		} catch (NullPointerException ex) {
			throw new InvalidCredentialException();
		}
		if (password.equalsIgnoreCase(userEntity.getPassword())){
			throw new InvalidPasswordException();
		}
		return userEntity.getId().toString();
	}

	
	public UserEntity findByMail(String mail) {
		UserDAO userDAO = (UserDAO) this.crudHelper.getDao();
		return userDAO.findByMail(mail);
	}

	public List<UserEntity> getAll() {
		return crudHelper.getAll();
	}

}
