package com.je.enterprise.mievento.domain.external.apiGeo.services;

import java.util.Map;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CountryEntity;

public class CountryHelper {

	public CountryHelper() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Map<CountryEntity, String> getCountryGeoId(){
		Map<CountryEntity, String> geoIds = Maps.newLinkedHashMap();
		geoIds.put(new CountryEntity(CountryCode.AR.name(), CountryCode.AR.getName()), "3865483");
		geoIds.put(new CountryEntity(CountryCode.PY.name(), CountryCode.PY.getName()), "3437598");
		geoIds.put(new CountryEntity(CountryCode.UY.name(), CountryCode.UY.getName()), "3439705");
		geoIds.put(new CountryEntity(CountryCode.BR.name(), CountryCode.BR.getName()), "3469034");
		geoIds.put(new CountryEntity(CountryCode.EC.name(), CountryCode.EC.getName()), "3658394");
		geoIds.put(new CountryEntity(CountryCode.VE.name(), CountryCode.VE.getName()), "3625428");
		geoIds.put(new CountryEntity(CountryCode.CO.name(), CountryCode.CO.getName()), "3686110");
		geoIds.put(new CountryEntity(CountryCode.BO.name(), CountryCode.BO.getName()), "3923057");
		geoIds.put(new CountryEntity(CountryCode.PE.name(), CountryCode.PE.getName()), "3932488");
		geoIds.put(new CountryEntity(CountryCode.CL.name(), CountryCode.CL.getName()), "3895114");
		geoIds.put(new CountryEntity(CountryCode.MX.name(), CountryCode.MX.getName()), "3996063");
		geoIds.put(new CountryEntity(CountryCode.CU.name(), CountryCode.CU.getName()), "3562981");
		geoIds.put(new CountryEntity(CountryCode.US.name(), CountryCode.US.getName()), "6252001");
		geoIds.put(new CountryEntity(CountryCode.ES.name(), CountryCode.ES.getName()), "2510769");
		
		return geoIds;
	}
}
