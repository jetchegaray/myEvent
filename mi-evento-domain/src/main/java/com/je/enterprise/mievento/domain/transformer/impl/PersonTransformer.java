package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.domain.entity.location.LocationEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PersonEntity;
import com.je.enterprise.mievento.domain.transformer.Transformer;

@Component
public class PersonTransformer extends Transformer<PersonEntity, Person>{

	
	private LocationTransformer locationTransformer;
	
	@Autowired
	public PersonTransformer(LocationTransformer locationTransformer) {
		this.locationTransformer = locationTransformer;
	}

	@Override
	public Person transformDomainToApi(PersonEntity domainObject) {
		Location  location = locationTransformer.transformAndValidateDomainToApi(domainObject.getLocation()); 
		return new Person(domainObject.getFirstName(),domainObject.getLastName(), domainObject.getEmail(), location);
	}

	@Override
	public PersonEntity transformApiToDomain(Person apiObject) {
		LocationEntity  locationEntity = locationTransformer.transformAndValidateApiToDomain(apiObject.getLocation()); 
		return new PersonEntity(apiObject.getFirstName(),apiObject.getLastName(), apiObject.getEmail(), locationEntity);
	}

}
