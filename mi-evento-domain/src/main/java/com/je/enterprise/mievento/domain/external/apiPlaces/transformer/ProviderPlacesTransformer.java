package com.je.enterprise.mievento.domain.external.apiPlaces.transformer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.ProvinceCode;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.AddressComponent;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ApiPlacesServicies;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class ProviderPlacesTransformer extends
		Transformer<ProviderEntity, DetailPlace> {
	
	private ProviderTypeKeyword providerTypeKeyword;
	private ApiPlacesServicies apiPlacesServicies;
	
	private Logger logger = LoggerFactory.getLogger(ProviderPlacesTransformer.class);

	@Autowired
	public ProviderPlacesTransformer(ProviderTypeKeyword providerTypeKeyword,ApiPlacesServicies apiPlacesServicies) {
		this.providerTypeKeyword = providerTypeKeyword;
		this.apiPlacesServicies = apiPlacesServicies;
	}
	
	
	@Override
	protected DetailPlace transformDomainToApi(ProviderEntity domainObject) {
		return null;
	}

	@Override
	protected ProviderEntity transformApiToDomain(DetailPlace detailPlace) {
		
		
		String businessName = detailPlace.getName();
		String description = StringUtils.EMPTY;
		LocationEntity location = getLocation(detailPlace);
		String email = StringUtils.EMPTY;
		String cellPhone = (detailPlace.getPhone() != null) ? detailPlace.getPhone() : detailPlace.getInternationalPhone();
		String phone = StringUtils.EMPTY;
		BigDecimal price = BigDecimal.ZERO;
		BigDecimal estimatedPrice = BigDecimal.ZERO;
		List<String> photos = this.transformToLocationPath(detailPlace.getPhotoReferences());
		
		if (photos.isEmpty()){
			logger.debug("Photos vacias para el id : {}",detailPlace.getReference());
			photos.add("../img/logo.jpg");
		}
		
		ProviderType providerType = getProviderType(detailPlace);
		//only here to eliminate duplicates id
		ProviderEntity entity = new ProviderEntity(detailPlace.getId(), businessName,description,location,email,cellPhone,phone,price,estimatedPrice,photos,providerType,null);
		
		return entity;
	}



	private LocationEntity getLocation(DetailPlace detailPlace) {
		
		CountryCode countryCode = null;
		ProvinceCode province = null;
		String city = StringUtils.EMPTY;
		String street = StringUtils.EMPTY;
		BigDecimal number = BigDecimal.ZERO;
		String additionalInfo = StringUtils.EMPTY;
		String neighborhood = StringUtils.EMPTY;
		
		for (AddressComponent addressComponent : detailPlace.getAddress()) {
			if (addressComponent.isCountry()){
				countryCode = CountryCode.valueOf(addressComponent.getShortName());
			}
			if (addressComponent.isCity()){
				city = addressComponent.getLongName();
			}
			if (addressComponent.isProvince()){
				province = ProvinceCode.valueOf(addressComponent.getShortName());
				if (province == null){
					province = ProvinceCode.getByName(addressComponent.getShortName());
				}
			}
			if (addressComponent.isStreet()){
				street = addressComponent.getLongName();
			}
			if (addressComponent.isNeighborhood()){
				neighborhood = addressComponent.getLongName();
			}
			if (addressComponent.isNumber()){
				number = BigDecimal.valueOf(Long.valueOf(addressComponent.getLongName()));
			}
		}
//		logger.info(String.format(" The detail object id : %s, with addresComponent = %s DOESNT MATCH TO ANYONE IN MY MODEL. total dir from api is = %s",detailPlace.getReference(),addressComponent,detailPlace.getAddress()));
		
		StreetAddressEntity streetAddress = new StreetAddressEntity(street, number, additionalInfo, neighborhood);
		return new LocationEntity(countryCode, province, city, streetAddress);
	}

	
	//FIXME
	private ProviderType getProviderType(DetailPlace detailPlace) {
		
		Map<ProviderType, List<String>> keywords = this.providerTypeKeyword.getProviderTypeKeyword();
		
		String detailName = detailPlace.getName();
		for (Entry<ProviderType, List<String>> entry : keywords.entrySet()) {
				
			for (String value : entry.getValue()) {
				if (detailName.toLowerCase().contains(value)){
					return entry.getKey();
				}
			}
		}
		
		return null;
	}
	
	

	private List<String> transformToLocationPath(List<String> photoReferences) {
		
		List<String> locationsPhotos = Lists.newArrayList();
		if (photoReferences != null){
			for (String reference : photoReferences) {
				locationsPhotos.add(apiPlacesServicies.getPhoto(reference));
			}
		}
		return locationsPhotos;
	}

}
