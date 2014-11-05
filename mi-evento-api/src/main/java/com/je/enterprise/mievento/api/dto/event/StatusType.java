package com.je.enterprise.mievento.api.dto.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Lists;

public enum StatusType {

	CONFIRMED("Confirmado"), PENDING("Pendiente"), REJECTED("Rechazo"), 
	UNINVITED("No invitado"), WAIT_TILL_DAY("Confirma el dia");

	private String name;

	private StatusType(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@JsonCreator
	public static StatusType getByName(String name) {
		for (StatusType type : values()) {
			if (type.getName().equals(name)) {
				return type;
			}
		}
		return null;
	}
	
	public static List<String> stringValues(){
		List<String> stringValues = Lists.<String>newArrayList();
		for(StatusType type : values()){
			stringValues.add(type.getName());
		}
		return stringValues;
	}

}
