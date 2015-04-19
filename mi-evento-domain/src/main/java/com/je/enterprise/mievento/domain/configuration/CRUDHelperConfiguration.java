package com.je.enterprise.mievento.domain.configuration;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.domain.dao.impl.BlackListCityDAO;
import com.je.enterprise.mievento.domain.dao.impl.CountryDAO;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.dao.impl.UserDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.UserEntity;
import com.je.enterprise.mievento.domain.entity.geo.BlackListCityEntity;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
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
	
	@Bean(name = "crudHelperCountry")
	public CRUDHelper<CountryEntity, ObjectId> crudHelperCountry(CountryDAO countryDAO){
		return new CRUDHelper<CountryEntity, ObjectId>(countryDAO);
	}
	
	@Bean(name = "crudHelperBlackList")
	public CRUDHelper<BlackListCityEntity, ObjectId> crudHelperBlackList(BlackListCityDAO blackListCityDAO){
		return new CRUDHelper<BlackListCityEntity, ObjectId>(blackListCityDAO);
	}
	
}
