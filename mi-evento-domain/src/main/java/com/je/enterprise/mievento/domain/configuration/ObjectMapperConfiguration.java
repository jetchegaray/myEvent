package com.je.enterprise.mievento.domain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.je.enterprise.mievento.api.dto.event.eventWithplace.EventWithPlaceAndPresent;

@Configuration
public class ObjectMapperConfiguration {
	
	@Bean
	public ObjectMapper objectMapperBean(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.enableDefaultTyping();
//		mapper.registerSubtypes(EventWithPlaceAndPresent.class);
		return mapper;
	}
}
