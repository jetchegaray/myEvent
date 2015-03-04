package com.je.enterprise.mievento.domain.external.apiPlaces.transformer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
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
	
	Map<ProviderType, List<String>> keywords;

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
		ProviderType providerType = getProviderType(detailPlace);
		
		ProviderEntity entity = new ProviderEntity(businessName,description,location,email,cellPhone,phone,price,estimatedPrice,photos,providerType,null);
		//only here to eliminate duplicates
		entity.setId(new ObjectId(detailPlace.getId()));
		
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
			if (addressComponent.isCity()){
				countryCode = CountryCode.valueOf(addressComponent.getShortName());
			}
			if (addressComponent.isCity()){
				city = addressComponent.getLongName();
			}
			if (addressComponent.isProvince()){
				province = addressComponent.getLongName();
			}
			if (addressComponent.isStreet()){
				street = addressComponent.getLongName();
			}
			if (addressComponent.isNeighborhood()){
				province = addressComponent.getLongName();
			}
			if (addressComponent.isNumber()){
				number = BigDecimal.valueOf(Long.valueOf(addressComponent.getLongName()));
			}
		}
		
		StreetAddressEntity streetAddress = new StreetAddressEntity(street, number, additionalInfo, neighborhood);
		return new LocationEntity(countryCode, province, city, streetAddress);
	}

	//FIXME
	private ProviderType getProviderType(DetailPlace detailPlace) {
		
		String detailName = detailPlace.getName();
		for (Entry<ProviderType, List<String>> entry : this.keywords.entrySet()) {
			if (entry.getValue().contains(detailName)){
				return entry.getKey();
			}
		}
		
		return null;
	}

}
