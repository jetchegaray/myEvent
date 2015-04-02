package com.je.enterprise.mievento.domain.transformer.impl;

import java.util.List;

import com.je.enterprise.mievento.api.dto.event.Guest;
import com.je.enterprise.mievento.api.dto.place.ControlContextTable;
import com.je.enterprise.mievento.domain.entity.common.event.GuestEntity;
import com.je.enterprise.mievento.domain.entity.place.ControlContextTableEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

public class ContextTableTransformer extends Transformer<ControlContextTableEntity,ControlContextTable>{

	private TransformerList<GuestEntity, Guest> guestTransformerList;
	
	public ContextTableTransformer(
			TransformerList<GuestEntity, Guest> guestTransformerList) {
		this.guestTransformerList = guestTransformerList;
	}

	@Override
	protected ControlContextTable transformDomainToApi(
			ControlContextTableEntity domainObject) {
		List<Guest> guests = guestTransformerList.transformDomainToApi(domainObject.getGuests());
		return new ControlContextTable(guests);
	}

	@Override
	protected ControlContextTableEntity transformApiToDomain(
			ControlContextTable apiObject) {
		List<GuestEntity> guests = guestTransformerList.transformApiToDomain(apiObject.getGuests());
		return new ControlContextTableEntity(guests);
	}

	
}
