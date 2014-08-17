package com.je.enterprise.mievento.domain.transformer.impl;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.entity.UserEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;



public class UserDomainToApiTransformer implements DomainToApiTransformer<UserEntity, User> {

	public User transform(UserEntity userEntity) {
		return new User(userEntity.getMail(),userEntity.getNickName(),userEntity.getPassword());
	}
	
}
