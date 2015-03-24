package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ApiPlacesServicies;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ResponseContainerObjects;
import com.je.enterprise.mievento.domain.service.impl.CountryService;
import com.je.enterprise.mievento.domain.service.impl.ProviderService;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Service
public class FullProvidersServiceData {

	private static final Logger logger = Logger.getLogger(FullProvidersServiceData.class);
	
	private ApiPlacesServicies apiPlacesServicies;
	private KeyWordsHandler keyWordsHandler;
	private ProviderService providerService;
	private TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList;
	private CountryService countryService;
	
	@Autowired
	public FullProvidersServiceData(ApiPlacesServicies apiPlacesServicies,KeyWordsHandler keyWordsHandler,ProviderService providerService,
			TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList,CountryService countryService) {
		this.apiPlacesServicies = apiPlacesServicies;
		this.keyWordsHandler = keyWordsHandler;
		this.providerPlacesTransformerList = providerPlacesTransformerList;
		this.providerService = providerService;
		this.countryService = countryService;
	}

	//couta 1k request/day
	@Scheduled(cron = "* */5 * * * ?")
	public void serviceProcessData() {
		
		List<DetailPlace> places = this.getData();
		List<ProviderEntity> entities = processData(places);
		saveData(entities);
	}

	
	
	private List<DetailPlace> getData() {
		
		List<DetailPlace> detailPlaces = Lists.<DetailPlace>newArrayList();
		Set<CityEntity> cities = countryService.getAllCitiesInCountry(CountryCode.AR);
		Set<CityEntity> alredyCity = this.providerService.getAllCitiesThereProviders(CountryCode.AR);
		
		Set<CityEntity> defintiveCities = Sets.difference(cities, alredyCity);
		
		for (CityEntity city : defintiveCities) {
				String latlng = city.getLatLongToSearch();
				for (String keyWord : keyWordsHandler.getKeywords()) {
					
					ResponseContainerObjects<SearchPlace> response = apiPlacesServicies.getPlaces(latlng, keyWord);
					if (response.getStatus().equalsIgnoreCase("OVER_QUERY_LIMIT")){
						return detailPlaces;
					}
					
					for (SearchPlace searchPlace : response.getData()) {
						DetailPlace detailPlace = apiPlacesServicies.getDetailPlace(searchPlace.getReference());
						
						try {
							Validate.notBlank(detailPlace.getPhone());
							detailPlaces.add(detailPlace);
						} catch (Exception e) {
							//nullPointer or illegalArgument
							continue;
						}
						
					}
				}
		}
		return detailPlaces;
	}

	
	private List<ProviderEntity> processData(List<DetailPlace> places) {
		
		try{
			
			List<ProviderEntity> entities = this.providerPlacesTransformerList.transformApiToDomain(places);
			Validate.notNull(entities);
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
					logger.error("Error Save in DataBase provider : {} from places api : {} "+ providerEntity.getBusinessId() +  ExceptionUtils.getStackTrace(e));
				}
			}
		}catch(Exception ex){
			logger.debug(" Save Data entities is null : ");
		}
	}


	
}
