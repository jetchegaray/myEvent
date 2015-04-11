package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.StatusResponse;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ApiPlacesServicies;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ResponseContainerObject;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ResponseContainerObjects;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type.BuilderConditionRulesProvider;
import com.je.enterprise.mievento.domain.external.apiPlaces.transformer.type.ConditionRuleProviderKeyWord;
import com.je.enterprise.mievento.domain.service.impl.CountryService;
import com.je.enterprise.mievento.domain.service.impl.ProviderService;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Service
public class FullProvidersServiceData {

	private static final Logger logger = Logger.getLogger(FullProvidersServiceData.class);
	
	private ApiPlacesServicies apiPlacesServicies;
	private ProviderService providerService;
	private TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList;
	private CountryService countryService;
	
	@Autowired
	public FullProvidersServiceData(ApiPlacesServicies apiPlacesServicies,ProviderService providerService,
			TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList,CountryService countryService) {
		this.apiPlacesServicies = apiPlacesServicies;
		this.providerPlacesTransformerList = providerPlacesTransformerList;
		this.providerService = providerService;
		this.countryService = countryService;
	}

	
	//couta 1k request/day
//	@Scheduled(cron = "0 0  * * ?")
	@Scheduled(cron = "0 32 12 * * ?")
	public void serviceProcessData() {
		
		List<DetailPlace> places = this.getData();
		List<ProviderEntity> entities = processData(places);
		saveData(entities);
	}
	
	
	
	private List<DetailPlace> getData() {
		
		List<DetailPlace> detailPlaces = Lists.<DetailPlace>newArrayList();
		Set<String> keyWords = ConditionRuleProviderKeyWord.getKeyWords();
		
		Set<CityEntity> cities = countryService.getAllCitiesInCountry(CountryCode.AR);
		Set<CityEntity> alredyCity = this.providerService.getAllCitiesThereProviders(CountryCode.AR);
		
		Set<CityEntity> defintiveCities = Sets.difference(cities, alredyCity);

		for (CityEntity city : defintiveCities) {
				String latlng = city.getLatLongToSearch();
				for (String keyWord : keyWords) {
					
					ResponseContainerObjects<SearchPlace> response = apiPlacesServicies.getPlaces(latlng, keyWord);
					if (response.getStatus().equalsIgnoreCase(StatusResponse.OVER_QUERY_LIMIT.getName())){
						return detailPlaces;
					}
					//filtro resultados que sirvan.
					List<SearchPlace> usefullPlaces = getOnlyBusinessProviderType(response.getData());
					if (usefullPlaces.isEmpty()){
						continue;
					}
					
					for (SearchPlace searchPlace : usefullPlaces) {
						ResponseContainerObject<DetailPlace> responseDetail = apiPlacesServicies.getDetailPlace(searchPlace.getReference());
						if (response.getStatus().equalsIgnoreCase(StatusResponse.OVER_QUERY_LIMIT.getName())){
							return detailPlaces;
						}
						
						if (! responseDetail.getStatus().equals(StatusResponse.OK.getName())){
							continue;
						}
						
						DetailPlace detailPlace = responseDetail.getData();
						List<String> locationsPhotos = Lists.newArrayList();
						if (detailPlace.getPhotoReferences() != null){
							for (String reference : detailPlace.getPhotoReferences()) {
								String photoLocation = apiPlacesServicies.getPhoto(reference);
								if (photoLocation != null){
									locationsPhotos.add(photoLocation);
								}
							}
							detailPlace.setPhotoLocations(locationsPhotos);
						}
						detailPlaces.add(detailPlace);
					}
				}
		}
		return detailPlaces;
	}




	private List<ProviderEntity> processData(List<DetailPlace> places) {
		
		try{
			
			List<ProviderEntity> entities = this.providerPlacesTransformerList.transformApiToDomain(places);
			Validate.notNull(entities);
			logger.debug(" Entities size : "+entities.size());
			return entities;
			
		}catch(Exception ex){
			logger.debug(" Transformer places to entities error : "+ ExceptionUtils.getStackTrace(ex));
			return null;
		}
	}

	
	private void saveData(List<ProviderEntity> entities) {
		try{
			Validate.notNull(entities);
			for (ProviderEntity providerEntity : entities) {
				try {
					this.providerService.create(providerEntity);
				} catch (Exception e) {
					logger.error("Error Save in DataBase provider : %s from places api : %s "+ providerEntity.getBusinessId() +  ExceptionUtils.getStackTrace(e));
					continue;
				}
			}
		}catch(Exception ex){
			logger.debug(" Save Data entities is null : ",ex);
		}
	}

	

	private List<SearchPlace> getOnlyBusinessProviderType(List<SearchPlace> data) {
		
		List<SearchPlace> searchRealPlaces = Lists.<SearchPlace>newArrayList();
		for (SearchPlace searchPlace : data) {
			
			 Map<ProviderType,List<ConditionRuleProviderKeyWord>> rulesByType = BuilderConditionRulesProvider.getRules();
			 String detailName = searchPlace.getName();
			 
			 for (Entry<ProviderType, List<ConditionRuleProviderKeyWord>> entry : rulesByType.entrySet()) {
				 List<ConditionRuleProviderKeyWord> rules = entry.getValue();
				 boolean findIt = false;
				 
				 for (ConditionRuleProviderKeyWord conditionRuleProviderKeyWord : rules) {
					 Pair<String, String> args = conditionRuleProviderKeyWord.getArguments();
					 if (conditionRuleProviderKeyWord.isAND()){
						 if (StringUtils.containsIgnoreCase(detailName, args.getLeft()) && StringUtils.containsIgnoreCase(detailName, args.getRight())){
							 searchRealPlaces.add(searchPlace);
							 findIt = true;
						 }
					 }else if (conditionRuleProviderKeyWord.isOR()){
						 if (StringUtils.containsIgnoreCase(detailName, args.getLeft()) || StringUtils.containsIgnoreCase(detailName, args.getRight())){
							 searchRealPlaces.add(searchPlace);
							 findIt = true;
						 }
					 }
				}
				if (findIt){
					break;
				}
			 }
		}
		return searchRealPlaces;
	}

	
}
