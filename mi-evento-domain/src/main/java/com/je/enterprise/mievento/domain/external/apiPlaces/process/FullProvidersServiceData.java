package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ApiPlacesServicies;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@Service
public class FullProvidersServiceData {

	private static final Logger logger = Logger.getLogger(FullProvidersServiceData.class);
	
	private ApiPlacesServicies apiPlacesServicies;
	private KeyWordsHandler keyWordsHandler;
	private CitiesByCountryHandler citiesByCountryHandler;
	private ProviderDAO providerDAO;
	private CitiesInfoCache citiesInfoCache;
	private TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList;
	
	@Autowired
	public FullProvidersServiceData(ApiPlacesServicies apiPlacesServicies,KeyWordsHandler keyWordsHandler,CitiesByCountryHandler citiesByCountryHandler,
			TransformerList<ProviderEntity, DetailPlace> providerPlacesTransformerList,ProviderDAO providerDAO,CitiesInfoCache citiesInfoCache) {
		this.apiPlacesServicies = apiPlacesServicies;
		this.keyWordsHandler = keyWordsHandler;
		this.citiesByCountryHandler = citiesByCountryHandler;
		this.providerPlacesTransformerList = providerPlacesTransformerList;
		this.providerDAO = providerDAO;
		this.citiesInfoCache = citiesInfoCache;
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
		Map<CountryCode, List<String>> citiesByCountry = citiesByCountryHandler.getCitiesByCountry();
		for (CountryCode country : CountryCode.values()) {
			for (String city : citiesByCountry.get(country)) {
				String latlng = citiesInfoCache.getLatlngFromCityAbrv(city);
				for (String keyWord : keyWordsHandler.getKeywords()) {
					
					List<SearchPlace> searchPlaces = apiPlacesServicies.getPlaces(latlng, keyWord);
					for (SearchPlace searchPlace : searchPlaces) {
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
	
		for (ProviderEntity providerEntity : entities) {
			try {
					providerDAO.save(providerEntity);
			} catch (Exception e) {
				logger.error("Error Save in DataBase provider : {} from places api : {} "+ providerEntity.getBusinessId() +  ExceptionUtils.getStackTrace(e));
			}
		}
		
	}


	
}
