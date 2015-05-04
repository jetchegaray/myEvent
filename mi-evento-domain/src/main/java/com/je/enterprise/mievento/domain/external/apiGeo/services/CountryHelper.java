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
		geoIds.put(new CountryEntity(CountryCode.PA.name(), CountryCode.PA.getName()), "3703430");
		geoIds.put(new CountryEntity(CountryCode.CR.name(), CountryCode.CR.getName()), "3624060");
		geoIds.put(new CountryEntity(CountryCode.NI.name(), CountryCode.NI.getName()), "3617476");
		geoIds.put(new CountryEntity(CountryCode.HN.name(), CountryCode.HN.getName()), "3608932");
		geoIds.put(new CountryEntity(CountryCode.SV.name(), CountryCode.SV.getName()), "3585968");
		geoIds.put(new CountryEntity(CountryCode.GT.name(), CountryCode.GT.getName()), "3595528");
		geoIds.put(new CountryEntity(CountryCode.PR.name(), CountryCode.PR.getName()), "4566966");
		geoIds.put(new CountryEntity(CountryCode.DM.name(), CountryCode.DM.getName()), "3575830");
		
		
		return geoIds;
	}
}
