package com.je.enterprise.mievento.domain.configuration;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Configuration
public class CRUDHelperConfiguration {

	@Bean(name = "crudHelperUser")
	public CRUDHelper<UserEntity, ObjectId> CRUDHelperUser(UserDAO userDAO){
		return new CRUDHelper<UserEntity, ObjectId>(userDAO);
	}
	
	@Bean(name = "crudHelperProvider")
	public CRUDHelper<ProviderEntity, ObjectId> crudHelperProvider(ProviderDAO providerDAO){
		return new CRUDHelper<ProviderEntity, ObjectId>(providerDAO);
	}
	
}
