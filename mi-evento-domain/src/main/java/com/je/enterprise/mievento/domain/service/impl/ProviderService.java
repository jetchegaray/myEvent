package com.je.enterprise.mievento.domain.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderReviewEntity;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.exception.customize.NotExistanceProvidersException;
import com.je.enterprise.mievento.domain.service.filters.CitiesFilterProvider;
import com.je.enterprise.mievento.domain.service.filters.CriteriaFilterProvider;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

@Service
public class ProviderService {

	private CRUDHelper<ProviderEntity, ObjectId> crudHelper;
	private static final Logger logger = Logger.getLogger(ProviderService.class);

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
		if (!providers.isEmpty()){
			return providers.get(0);
		}
		return null;
	}

	
	public void updated(ProviderEntity providerUpdated) {
		try{
			ProviderEntity oldProvider = this.crudHelper.getDao().findOne("businessId",providerUpdated.getBusinessId());
			Validate.notNull(oldProvider);
			
			List<ProviderReviewEntity> reviews = oldProvider.getReviews();
			reviews.addAll(providerUpdated.getReviews());
			oldProvider.setReviews(reviews);
			
			BigDecimal oldEstimatedPrice = oldProvider.getEstimatedPrice();
			oldEstimatedPrice = oldEstimatedPrice.add(providerUpdated.getEstimatedPrice());
			BigDecimal estimatedPriceUpdated = oldEstimatedPrice.divide(BigDecimal.valueOf(reviews.size()), 0 , RoundingMode.HALF_DOWN);
			
			oldProvider.setEstimatedPrice(estimatedPriceUpdated);
			
			this.crudHelper.update(oldProvider);
			
		}catch(Exception ex){
			logger.error(ExceptionUtils.getStackTrace(ex));
		}
	}
	
	
	public Set<CityEntity> getAllCitiesThereProviders(CountryCode country){
		List<ProviderEntity> providers = this.getBy(new CitiesFilterProvider(country));
		
		Set<CityEntity> cities = Sets.<CityEntity>newLinkedHashSet(Iterables.transform(providers, new Function<ProviderEntity, CityEntity>() {
			@Override
			public CityEntity apply(ProviderEntity input) {
				return new CityEntity(input.getLocation().getCity(), null, null);
			}
		}));
		
		return cities;
	}
	

	public void create(ProviderEntity providerEntity) {
		this.crudHelper.create(providerEntity);
	}
	
	public Long totalCount(CriteriaFilterProvider criteriaFilterProvider){
		ProviderDAO providerDAO = (ProviderDAO)this.crudHelper.getDao();
		Long count = Long.valueOf(providerDAO.countBy(criteriaFilterProvider));
		return count;
	}
}
