package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.User;
import com.je.enterprise.mievento.domain.entity.common.UserEntity;
import com.je.enterprise.mievento.domain.transformer.ApiToDomainTransformer;


@Component
public class UserApiToDomainTransformer extends ApiToDomainTransformer<UserEntity, User> {

	@Override
	public UserEntity transform(User user) {
		return new UserEntity(user.getEmail(),user.getPassword(),true);
	}
}
