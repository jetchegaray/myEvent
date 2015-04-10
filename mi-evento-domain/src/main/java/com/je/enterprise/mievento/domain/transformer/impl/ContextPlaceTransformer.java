package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.place.ControlContextPlace;
import com.je.enterprise.mievento.api.dto.place.ControlContextTable;
import com.je.enterprise.mievento.domain.entity.place.ControlContextPlaceEntity;
import com.je.enterprise.mievento.domain.entity.place.ControlContextTableEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

//avoid redundancy because transformer list need trnasformer y this transformer needs trnaformer list
public class ContextPlaceTransformer extends Transformer<ControlContextPlaceEntity,ControlContextPlace>{

	private TransformerList<ControlContextTableEntity,ControlContextTable> contextTableTransformerList;
	
	public ContextPlaceTransformer(
			TransformerList<ControlContextTableEntity, ControlContextTable> contextTableTransformerList) {
		super();
		this.contextTableTransformerList = contextTableTransformerList;
	}

	@Override
	protected ControlContextPlace transformDomainToApi(ControlContextPlaceEntity domainObject) {
		List<ControlContextTable> contextTables = contextTableTransformerList.transformDomainToApi(domainObject.getControlContextTableEntities());
		return new ControlContextPlace(contextTables);
	}

	@Override
	protected ControlContextPlaceEntity transformApiToDomain(ControlContextPlace apiObject) {
		List<ControlContextTableEntity> contextTables = contextTableTransformerList.transformApiToDomain(apiObject.getControlContextTables());
		return new ControlContextPlaceEntity(contextTables);
	}

	
}
