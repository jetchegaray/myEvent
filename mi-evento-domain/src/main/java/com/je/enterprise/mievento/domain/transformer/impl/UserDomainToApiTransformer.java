package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.entity.common.UserEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class UserDomainToApiTransformer extends DomainToApiTransformer<UserEntity,User> {

	@Override
	public User transform(UserEntity userEntity) {
		return new User(userEntity.getEmail(),userEntity.getPassword(),true);
	}
	
}
