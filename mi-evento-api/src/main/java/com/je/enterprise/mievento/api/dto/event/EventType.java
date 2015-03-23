package com.je.enterprise.mievento.api.dto.event;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Sets;

public enum EventType {

	WEDDING("Boda o Casamiento"),
	BIRTHDAY("Cumpleaños"),
	PARTY("Fiesta"),
	BAR_MITZVAH("Bar Mitzvah"),
	COMMON_EVENT("Otro Evento");
	
	private String name;
	
	private EventType(String name) {
		this.name = name;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static EventType getByName(String name){
		for(EventType type : values()){
			if(type.getName().equals(name)){
				return type;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static Set<EventType> withPosiblesPlaces(){
		return Sets.newHashSet(EventType.WEDDING,EventType.PARTY,EventType.BIRTHDAY,EventType.BAR_MITZVAH);
	}
	
}
