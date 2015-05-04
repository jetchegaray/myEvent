package com.je.enterprise.mievento.domain.entity.common.event;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.event.Event;
import com.je.enterprise.mievento.api.dto.event.EventType;
import com.je.enterprise.mievento.domain.transformer.impl.events.VisitorTransformer;

public class EventEntity {

	private String name;
	private Date initialDate;
	private Date finalDate;
	@Embedded
	private List<GuestEntity> guests = Lists.<GuestEntity>newArrayList();
	@Embedded
	private List<TaskEntity> tasks = Lists.<TaskEntity>newArrayList();
	@Embedded
	@Reference
	private List<ProviderEntity> providers;
	private EventType type;
	
	public EventEntity() {
	}
	
	public EventEntity(String name, Date initialDate,Date finalDate,
			List<GuestEntity> guests, List<TaskEntity> tasks, List<ProviderEntity> providers, EventType type) {
		this.name = name;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.guests = guests;
		this.tasks = tasks;
		this.providers = providers;
		this.type = type;
	}

	public List<GuestEntity> getGuests() {
		return guests;
	}

	public void setGuests(List<GuestEntity> guests) {
		this.guests = guests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}
	
	public List<ProviderEntity> getProviders() {
		return providers;
	}

	public void setProviders(List<ProviderEntity> providers) {
		this.providers = providers;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
	public Event accept(VisitorTransformer visitorTransformer){
		return visitorTransformer.visitTransformer(this);
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

}
