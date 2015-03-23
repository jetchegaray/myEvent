package com.je.enterprise.mievento.domain.external.apiGeo.transformer;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.je.enterprise.mievento.domain.entity.geo.StateEntity;
import com.je.enterprise.mievento.domain.external.apiGeo.services.GEOExternalWrapper;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class StateGeoExternalTransformer extends
		Transformer<StateEntity, GEOExternalWrapper> {
	
	private static final String PROV_DE = "Provincia de ";
	private static final String PROV_DEL = "Provincia del ";
	

	@Override
	protected GEOExternalWrapper transformDomainToApi(StateEntity domainObject) {
		return null;
	}

	@Override
	protected StateEntity transformApiToDomain(GEOExternalWrapper apiObject) {
		String newName = StringUtils.delete(apiObject.getName(), PROV_DE);
		newName = StringUtils.delete(newName, PROV_DEL);
		return new StateEntity(newName, apiObject.getLat(), apiObject.getLng(), null);
	}

}
