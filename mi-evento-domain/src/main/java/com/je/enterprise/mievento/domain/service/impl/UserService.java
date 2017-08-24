package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.exception.customize.InvalidCredentialException;
import com.je.enterprise.mievento.domain.exception.customize.InvalidPasswordException;
import com.je.enterprise.mievento.domain.exception.customize.UserAlredyExistsException;
import com.je.enterprise.mievento.domain.exception.customize.UserDoesNotExistException;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Service
public class UserService {

	private CRUDHelper<UserEntity, ObjectId> crudHelper;
	private MailService mailService;
	private static final Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	public UserService(CRUDHelper<UserEntity, ObjectId> crudHelperUser,
			MailService mailService) {
		this.crudHelper = crudHelperUser;
		this.mailService = mailService;
	}

	public void signUp(String nickName,String email, String password) {
		if (findByMail(email) == null) {
			crudHelper.create(new UserEntity(nickName, email, password, true));
		} else {
			throw new UserAlredyExistsException();
		}
	}

	public UserEntity login(String mail, String password) {
		UserEntity userEntity = this.findByMail(mail);
		try {
			Validate.notNull(userEntity);

		} catch (NullPointerException ex) {
			throw new InvalidCredentialException();
		}
		if (!password.equalsIgnoreCase(userEntity.getPassword())) {
			throw new InvalidPasswordException();
		}
		return userEntity;
	}

	public UserEntity findByMail(String mail) {
		UserDAO userDAO = (UserDAO) this.crudHelper.getDao();
		return userDAO.findByMail(mail);
	}

	public List<UserEntity> getAll() {
		return crudHelper.getAll();
	}

	public void update(UserEntity user) {
		
		try {
			
			UserEntity userEntity = this.findByMail(user.getEmail());
			Validate.notNull(userEntity);
			userEntity.setEvents(user.getEvents());
			crudHelper.update(userEntity);
			
		} catch (NullPointerException ex) {
			ex.getStackTrace();
		}catch (Exception e){
			throw new UserDoesNotExistException();
		}
		
	}
	
	public void delete(UserEntity user) {
		
		try {
			
			UserEntity userEntity = this.findByMail(user.getEmail());
			Validate.notNull(userEntity);
			crudHelper.delete(userEntity.getId());
			
		} catch (NullPointerException ex) {
			throw new UserDoesNotExistException();
		}
	
	}

//	public void sendMail(String email) {
//		UserEntity userEntity = this.findByMail(email);
//
//		String newPassword = RandomStringUtils.randomAlphanumeric(12);
//		userEntity.setPassword(newPassword);
//		userEntity.setActivate(false);
//		this.update(userEntity);
//
//		this.mailService.sendPassword(userEntity);
//	}

}
