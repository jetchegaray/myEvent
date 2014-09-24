package com.je.enterprise.mievento.domain.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.location.Location;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.transformer.DomainToApiTransformer;

@Component
public class PersonDomainToApiTransformer extends DomainToApiTransformer<PersonEntity, Person>{

	
	private LocationDomainToApiTransformer locationDomainToApiTransformer;
	
	@Autowired
	public PersonDomainToApiTransformer(LocationDomainToApiTransformer locationDomainToApiTransformer) {
		this.locationDomainToApiTransformer = locationDomainToApiTransformer;
	}

	@Override
	public Person transform(PersonEntity domainObject) {
		Location  location = locationDomainToApiTransformer.transform(domainObject.getLocation()); 
		return new Person(domainObject.getFirstName(),domainObject.getLastName(), domainObject.getEmail(), location);
	}

}
