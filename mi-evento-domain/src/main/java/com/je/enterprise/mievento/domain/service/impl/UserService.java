package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.exception.InvalidCredentialException;
import com.je.enterprise.mievento.domain.exception.InvalidPasswordException;
import com.je.enterprise.mievento.domain.exception.UserAlredyExistsException;
import com.je.enterprise.mievento.domain.exception.UserNotExistException;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Service
public class UserService {

	private CRUDHelper<UserEntity, ObjectId> crudHelper;

	@Autowired
	public UserService(CRUDHelper<UserEntity, ObjectId> crudHelperUser) {
//		this.crudHelper = new CRUDHelper<UserEntity, ObjectId>(userDao);
		this.crudHelper = crudHelperUser;
	}
	
	public void signUp(String email, String password) {
		if (findByMail(email) == null) {
			crudHelper.create(new UserEntity(email, password, true));
		} else {
			throw new UserAlredyExistsException();
		}
	}

	public String login(String mail, String password) {
		UserEntity userEntity = this.findByMail(mail);
		try {
			Validate.notNull(userEntity);

		} catch (NullPointerException ex) {
			throw new InvalidCredentialException();
		}
		if (!password.equalsIgnoreCase(userEntity.getPassword())) {
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
	
	public void update(UserEntity user){
		crudHelper.update(user);
	}
	
	public void delete(UserEntity user){
		UserEntity userEntity = this.findByMail(user.getEmail());
		try {
			Validate.notNull(userEntity);

		} catch (NullPointerException ex) {
			throw new UserNotExistException();
		}
		crudHelper.delete(userEntity.getId());
	}

}
