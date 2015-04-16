package com.je.enterprise.mievento.domain.external.apiGeo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.dao.impl.CountryDAO;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
import com.je.enterprise.mievento.domain.external.apiGeo.services.CountryHelper;
import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOExternalWrapper;
import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOServicies;
import com.je.enterprise.mievento.domain.external.apiGeo.transformer.CityGeoExternalTransformer;
import com.je.enterprise.mievento.domain.external.apiGeo.transformer.StateGeoExternalTransformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@ContextConfiguration(locations = {"classpath:com/je/enterprise/mievento/test/test-domain-context.xml"})
public class LoadGeoNames extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private GEOServicies geoServicies;
	@Autowired
	private StateGeoExternalTransformer stateGeoTransformer;
	@Autowired
	private CityGeoExternalTransformer cityGeoTransformer;
	@Autowired
	private CountryDAO countryDAO;
	
	private TransformerList<CityEntity, GEOExternalWrapper> citiesGeoTransformer;
	private Map<CountryEntity, String> countryGeoId; 
	
	
	@Before
	public void setUp(){
//		RestTemplate template = new RestTemplate();
//		this.geoServicies = new GEOServicies(template);
//		
		CountryHelper countryHelper = new CountryHelper();
		this.countryGeoId = countryHelper.getCountryGeoId();
		
		this.citiesGeoTransformer = new TransformerList<CityEntity, GEOExternalWrapper>(this.cityGeoTransformer); 
//		this.stateGeoTransformer = new StateGeoTransformer();
	}
	
	
	@Test
	public void load_Save_allCountries(){
		
		List<CountryEntity> countriesToSave = Lists.newArrayList();
		
		for ( Entry<CountryEntity, String> idByCountry : this.countryGeoId.entrySet()) {
			CountryEntity countryEntity = idByCountry.getKey();
			List<StateEntity> statesToSave = Lists.newArrayList();
			
			List<GEOExternalWrapper> statesById = geoServicies.getChildrenById(idByCountry.getValue());
			
			for (GEOExternalWrapper geoState : statesById) {
				
				List<GEOExternalWrapper> citiesById = geoServicies.getChildrenById(geoState.getGeonameId());
				if (citiesById.size() == 1 && countryEntity.getCountryCodeISOTwoLetter().equals("AR")){ //capital city.
					String id = citiesById.get(0).getGeonameId();
					citiesById = geoServicies.getChildrenById(id);
				}
				
				StateEntity stateEntity = this.stateGeoTransformer.transformAndValidateApiToDomain(geoState);
				List<CityEntity> citiesEntities = this.citiesGeoTransformer.transformApiToDomain(citiesById);
				stateEntity.setCities(citiesEntities);
				statesToSave.add(stateEntity);
			}
			countryEntity.setStates(statesToSave);
			countriesToSave.add(countryEntity);
		}
		
		this.countryDAO.save(countriesToSave);
	}
	
	
}
