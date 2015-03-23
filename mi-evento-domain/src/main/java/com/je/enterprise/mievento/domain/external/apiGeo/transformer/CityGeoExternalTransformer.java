package com.je.enterprise.mievento.domain.external.apiGeo.transformer;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOExternalWrapper;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class CityGeoExternalTransformer extends
		Transformer<CityEntity, GEOExternalWrapper> {
	
	private static final String PART_DE = "Partido de ";
	private static final String DEPT_DE = "Departamento de ";
	
	@Override
	protected GEOExternalWrapper transformDomainToApi(CityEntity domainObject) {
		return null;
	}

	@Override
	protected CityEntity transformApiToDomain(GEOExternalWrapper apiObject) {
		String newName = StringUtils.delete(apiObject.getName(), PART_DE);
		newName = StringUtils.delete(newName, DEPT_DE);
		return new CityEntity(newName, apiObject.getLat(), apiObject.getLng());
	}

}
