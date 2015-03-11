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

import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.location.StreetAddressEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.AddressComponent;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class ProviderPlacesTransformer extends
		Transformer<ProviderEntity, DetailPlace> {
	
	private Map<ProviderType, List<String>> keywords;
	private Logger logger = LoggerFactory.getLogger(ProviderPlacesTransformer.class);

	@Autowired
	public ProviderPlacesTransformer(ProviderTypeKeyword providerTypeKeyword) {
		keywords = providerTypeKeyword.getProviderTypeKeyword();
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
		String phone = cellPhone;
		BigDecimal price = BigDecimal.ZERO;
		BigDecimal estimatedPrice = BigDecimal.ZERO;
		List<String> photos = detailPlace.getPhotoReferences();
		
		if (photos.isEmpty()){
			logger.debug("Photos vacias para el id : {}",detailPlace.getReference());
		}
		
		ProviderType providerType = getProviderType(detailPlace);
		//only here to eliminate duplicates id
		ProviderEntity entity = new ProviderEntity(detailPlace.getId(), businessName,description,location,email,cellPhone,phone,price,estimatedPrice,photos,providerType,null);
		
		return entity;
	}

	private LocationEntity getLocation(DetailPlace detailPlace) {
		
		CountryCode countryCode = null;
		String province = StringUtils.EMPTY;
		String city = StringUtils.EMPTY;
		String street = StringUtils.EMPTY;
		BigDecimal number = BigDecimal.ZERO;
		String additionalInfo = StringUtils.EMPTY;
		String neighborhood = StringUtils.EMPTY;
		
		for (AddressComponent addressComponent : detailPlace.getAddress()) {
			boolean passIt = false;
			if (addressComponent.isCountry()){
				countryCode = CountryCode.valueOf(addressComponent.getShortName());
				passIt = true;
			}
			if (addressComponent.isCity()){
				city = addressComponent.getLongName();
				passIt = true;
			}
			if (addressComponent.isProvince()){
				province = addressComponent.getLongName();
				passIt = true;
			}
			if (addressComponent.isStreet()){
				street = addressComponent.getLongName();
				passIt = true;
			}
			if (addressComponent.isNeighborhood()){
				province = addressComponent.getLongName();
				passIt = true;
			}
			if (addressComponent.isNumber()){
				number = BigDecimal.valueOf(Long.valueOf(addressComponent.getLongName()));
				passIt = true;
			}
			
			if (!passIt){
				logger.info(String.format(" The detail object id : %s, with addresComponent = %s DOESNT MATCH TO ANYONE IN MY MODEL. total dir from api is = %s",detailPlace.getReference(),addressComponent,detailPlace.getAddress()));
			}
		}
		
		StreetAddressEntity streetAddress = new StreetAddressEntity(street, number, additionalInfo, neighborhood);
		return new LocationEntity(countryCode, province, city, streetAddress);
	}

	//FIXME
	private ProviderType getProviderType(DetailPlace detailPlace) {
		
		String detailName = detailPlace.getName();
		for (Entry<ProviderType, List<String>> entry : this.keywords.entrySet()) {
				
			for (String value : entry.getValue()) {
				if (detailName.toLowerCase().contains(value)){
					return entry.getKey();
				}
			}
		}
		
		return null;
	}

}
