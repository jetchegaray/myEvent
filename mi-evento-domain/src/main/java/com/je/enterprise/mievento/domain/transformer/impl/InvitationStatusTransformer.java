package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.InvitationStatus;
import com.je.enterprise.mievento.domain.entity.common.event.InvitationStatusEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class InvitationStatusTransformer extends Transformer<InvitationStatusEntity, InvitationStatus>{

	@Override
	protected InvitationStatus transformDomainToApi(InvitationStatusEntity domainObject) {
		return new InvitationStatus(domainObject.getStatus(), domainObject.getUpdateStatusDate());
	}

	@Override
	protected InvitationStatusEntity transformApiToDomain(InvitationStatus apiObject) {
		return new InvitationStatusEntity(apiObject.getStatus(), apiObject.getUpdateStatusDate());
	}

}
