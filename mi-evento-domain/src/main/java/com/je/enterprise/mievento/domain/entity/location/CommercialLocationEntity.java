package com.je.enterprise.mievento.domain.entity.location;

import org.apache.commons.lang3.StringUtils;

import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.api.dto.location.ProvinceCode;


public class CommercialLocationEntity extends LocationEntity{

	private String placeName;

	public CommercialLocationEntity() {
	}

	public CommercialLocationEntity(String placeName,CountryCode countryCode, ProvinceCode province,String city,
			StreetAddressEntity streetAddress) {
		super(countryCode, province,city, streetAddress,StringUtils.EMPTY,StringUtils.EMPTY);
		this.placeName = placeName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	@Override
	public String toString() {
		final String separator = " "; 
		StringBuilder builder = new StringBuilder();
		builder.append(this.streetAddress.getStreet()).append(separator);
		builder.append(this.streetAddress.getNumber()).append(separator);
		builder.append(this.streetAddress.getNeighborhood()).append(separator);
		builder.append(this.streetAddress.getAdditionalInfo()).append(separator);
		builder.append(this.province).append(separator);
		builder.append(this.city).append(".");
		
		return builder.toString();
	}
	
}
