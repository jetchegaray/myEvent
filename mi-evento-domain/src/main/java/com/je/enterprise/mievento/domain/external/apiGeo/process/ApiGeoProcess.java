//package com.je.enterprise.mievento.domain.external.apiGeo.process;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.PostConstruct;
//
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.google.common.collect.Lists;
//import com.je.enterprise.mievento.domain.dao.impl.CountryDAO;
//import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
//import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;
//import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
//import com.je.enterprise.mievento.domain.external.apiGeo.services.CountryServices;
//import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOServicies;
//import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOServicies.GEOExternalWrapper;
//import com.je.enterprise.mievento.domain.external.apiGeo.transformer.CityGeoTransformer;
//import com.je.enterprise.mievento.domain.external.apiGeo.transformer.StateGeoTransformer;
//import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;
//import com.je.enterprise.mievento.domain.transformer.TransformerList;
//
//@Component
//public class ApiGeoProcess {
//
//	//se corre una sola vez por actualizacion de pais.
//	
//	private GEOServicies geoServicies;
//	private StateGeoTransformer stateGeoTransformer;
//	private CountryDAO countryDAO;
//	private TransformerList<CityEntity, GEOExternalWrapper> citiesGeoTransformer = new TransformerList<CityEntity, GEOExternalWrapper>(new CityGeoTransformer());
//	private CountryServices countryServices = new CountryServices();
//	private CRUDHelper<CountryEntity, ObjectId> crudHelper;
//	
//	@Autowired
//	public ApiGeoProcess(GEOServicies geoServicies,StateGeoTransformer stateGeoTransformer,
//			CountryDAO countryDAO) {
//		this.geoServicies = geoServicies;
//		this.stateGeoTransformer = stateGeoTransformer;
//		this.countryDAO = countryDAO;
//	}
//
//
//
//
//	@PostConstruct
//	public void load(){
//		Map<CountryEntity, String> countryGeoId = countryServices.getCountryGeoId();
//		List<CountryEntity> countriesToSave = Lists.newArrayList();
//		
//		for ( Entry<CountryEntity, String> idByCouAntry : countryGeoId.entrySet()) {
//			CountryEntity countryEntity = idByCountry.getKey();
//			List<StateEntity> statesToSave = Lists.newArrayList();
//			
//			List<GEOExternalWrapper> statesById = geoServicies.getChildrenById(idByCountry.getValue());
//			
//			for (GEOExternalWrapper geoState : statesById) {
//				
//				List<GEOExternalWrapper> citiesById = geoServicies.getChildrenById(geoState.getGeonameId());
//				
//				StateEntity stateEntity = this.stateGeoTransformer.transformAndValidateApiToDomain(geoState);
//				List<CityEntity> citiesEntities = this.citiesGeoTransformer.transformApiToDomain(citiesById);
//				stateEntity.setCities(citiesEntities);
//				statesToSave.add(stateEntity);
//			}
//			countryEntity.setStates(statesToSave);
//			countriesToSave.add(countryEntity);
//		}
//		
//		this.countryDAO.save(countriesToSave);
//	}
//}
