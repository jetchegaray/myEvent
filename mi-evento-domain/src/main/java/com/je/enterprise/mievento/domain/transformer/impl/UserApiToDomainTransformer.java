package com.je.enterprise.mievento.domain.transformer.impl;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.entity.UserEntity;
import com.je.enterprise.mievento.domain.transformer.ApiToDomainTransformer;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;



public class UserApiToDomainTransformer extends ApiToDomainTransformer<UserEntity, User> {

	@Override
	public UserEntity transform(User user) {
		return new UserEntity(user.getMail(),user.getPassword(),true);
	}
}
