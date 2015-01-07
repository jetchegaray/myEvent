package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.exception.customize.NotExistanceProvidersException;
import com.je.enterprise.mievento.domain.service.filters.CriteriaFilterProvider;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Service
public class ProviderService {

	private CRUDHelper<ProviderEntity, ObjectId> crudHelper;

	@Autowired
	public ProviderService(CRUDHelper<ProviderEntity, ObjectId> crudHelperProvider) {
		this.crudHelper = crudHelperProvider;
	}
	
	public List<ProviderEntity> getAll(){
		
		List<ProviderEntity> providers = this.crudHelper.getAll();	
		validate(providers);
		return providers; 
	}
	
	
	public List<ProviderEntity> getBy(CriteriaFilterProvider criteriaFilterProvider){
		ProviderDAO providerDAO = (ProviderDAO)this.crudHelper.getDao();
		List<ProviderEntity> providers = providerDAO.findBy(criteriaFilterProvider);
		validate(providers);
		return providers;
	}
	
	
	private void validate(List<ProviderEntity> providers) {
		try{
			Validate.notNull(providers);
		}catch(NullPointerException ex){
			throw new NotExistanceProvidersException();
		}
	}

	
	public ProviderEntity getMostOfAllBy(CriteriaFilterProvider criteriaFilterProvider){
		List<ProviderEntity> providers = this.getBy(criteriaFilterProvider);
		if (providers.isEmpty()){
			return providers.get(0);
		}
		
		return null;
	}
}
