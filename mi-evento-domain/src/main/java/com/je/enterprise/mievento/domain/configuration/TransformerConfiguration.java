package com.je.enterprise.mievento.domain.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.Person;
import com.je.enterprise.mievento.api.dto.event.wedding.Present;
import com.je.enterprise.mievento.api.dto.provider.Provider;
import com.je.enterprise.mievento.domain.entity.common.event.EventEntity;
import com.je.enterprise.mievento.domain.entity.common.event.PersonEntity;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.entity.wedding.PresentEntity;
import com.je.enterprise.mievento.domain.transformer.TransformerList;
import com.je.enterprise.mievento.domain.transformer.impl.CommercialLocationTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.EventTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.PersonTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.PresentTransformer;
import com.je.enterprise.mievento.domain.transformer.impl.ProviderTransformer;

@Configuration
public class TransformerConfiguration {

	@Autowired
	private PersonTransformer personTransformer;
	@Autowired
	private ProviderTransformer providerTransformer;
	@Autowired
	private PresentTransformer presentTransformer;
	@Autowired
	private CommercialLocationTransformer CommercialLocationTransformer;

	
	@Bean(name = "personTransformerList")
	public TransformerList<PersonEntity, Person> personTransformerList(){
		return new TransformerList<PersonEntity, Person>(this.personTransformer);
	}
	
	@Bean(name = "providerTransformerList")
	public TransformerList<ProviderEntity, Provider> providerTransformerList(){
		return new TransformerList<ProviderEntity, Provider>(this.providerTransformer);
	}
	
	@Bean(name = "presentTransformerList")
	public TransformerList<PresentEntity, Present> presentTransformerList(){
		return new TransformerList<PresentEntity, Present>(this.presentTransformer);
	}

	@Bean(name = "eventTransformer")
	public EventTransformer eventTransformer(){
		return new EventTransformer(this.CommercialLocationTransformer,this.personTransformerList());
	}
	
	@Bean(name = "eventTransformerList")
	public TransformerList<EventEntity, Event> eventTransformerList(){
		return new TransformerList<EventEntity, Event>(this.eventTransformer());
	}

	
}
