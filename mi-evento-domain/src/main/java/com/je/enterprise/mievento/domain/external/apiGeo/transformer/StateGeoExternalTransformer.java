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
	private static final String DEPT_DE = "Departamento de ";
	private static final String EST_DE = "Estado de ";
	private static final String EST = "Estado ";

	@Override
	protected GEOExternalWrapper transformDomainToApi(StateEntity domainObject) {
		return null;
	}

	@Override
	protected StateEntity transformApiToDomain(GEOExternalWrapper apiObject) {
		
		String newName = StringUtils.delete(apiObject.getName(), PROV_DEL);
		newName = StringUtils.delete(newName, PROV_DE);
		newName = StringUtils.delete(newName, DEPT_DE);
		newName = StringUtils.delete(newName, EST_DE);
		newName = StringUtils.delete(newName, EST);
		
		return new StateEntity(newName, apiObject.getLat(), apiObject.getLng(), null);
	}

}
