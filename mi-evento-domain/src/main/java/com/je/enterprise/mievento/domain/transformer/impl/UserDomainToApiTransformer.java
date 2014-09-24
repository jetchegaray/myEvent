package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.user.User;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class UserDomainToApiTransformer extends DomainToApiTransformer<UserEntity,User> {

	private EventDomainToApiTransformer eventDomainToApiTransformer;
	
	@Autowired
	public UserDomainToApiTransformer(
			EventDomainToApiTransformer eventDomainToApiTransformer) {
		this.eventDomainToApiTransformer = eventDomainToApiTransformer;
	}


	@Override
	public User transform(UserEntity userEntity) {
		List<Event> events = this.eventDomainToApiTransformer.transform(userEntity.getEvents());
		return new User(userEntity.getEmail(),userEntity.getPassword(),true,events);
	}
	
}
