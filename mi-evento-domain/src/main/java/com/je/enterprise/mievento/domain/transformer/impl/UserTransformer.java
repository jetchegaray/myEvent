package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.user.User;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Component
public class UserTransformer extends Transformer<UserEntity,User> {

	private TransformerList<EventEntity, Event> eventTransformerList;
	
	@Autowired
	public UserTransformer(
			TransformerList<EventEntity, Event> eventTransformerList) {
		this.eventTransformerList = eventTransformerList;
	}


	@Override
	public User transformDomainToApi(UserEntity domainObject) {
		List<Event> events = this.eventTransformerList.transformDomainToApi(domainObject.getEvents());
		return new User(String.valueOf(domainObject.getId()),domainObject.getNickName(),domainObject.getEmail(),domainObject.getPassword(),true,events);
	}


	@Override
	public UserEntity transformApiToDomain(User apiObject) {
		List<EventEntity> eventsEntities = this.eventTransformerList.transformApiToDomain(apiObject.getEvents());
		UserEntity userEntity =  new UserEntity(apiObject.getEmail(),apiObject.getNickName(),apiObject.getPassword(),true);
		userEntity.setEvents(eventsEntities);
		return userEntity;
	}
	
}
