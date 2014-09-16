package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
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
	private MailService mailService;
	private static final Logger logger = Logger.getLogger(UserService.class);
	

	@Autowired
	public UserService(CRUDHelper<UserEntity, ObjectId> crudHelperUser,MailService mailService) {
		this.crudHelper = crudHelperUser;
		this.mailService = mailService;
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

	
	public void sendMail(String email) {
		UserEntity userEntity = this.findByMail(email);
		
		String newPassword = RandomStringUtils.randomAlphanumeric(12);
		userEntity.setPassword(newPassword);
		userEntity.setActivate(false);
		this.update(userEntity);
		
		try {
			this.mailService.send(userEntity);
		} catch (MessagingException e) {
			//TODO convertir a una exception propia.	
			logger.info(e.getMessage());
			
		}
	}
	

}
