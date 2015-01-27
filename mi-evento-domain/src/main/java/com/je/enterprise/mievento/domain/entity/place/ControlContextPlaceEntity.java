package com.je.enterprise.mievento.domain.entity.place;

import java.util.List;

public class ControlContextPlaceEntity {

	private List<ControlContextTableEntity> controlContextTableEntities;
	private ConfigurationPlaceEntity configurarionPlaceEntity;
	
	public ControlContextPlaceEntity() {
	}

	public ControlContextPlaceEntity(
			List<ControlContextTableEntity> controlContextTableEntities,
			ConfigurationPlaceEntity configurarionPlaceEntity) {
		this.controlContextTableEntities = controlContextTableEntities;
		this.configurarionPlaceEntity = configurarionPlaceEntity;
	}

	public List<ControlContextTableEntity> getControlContextTableEntities() {
		return controlContextTableEntities;
	}

	public void setControlContextTableEntities(
			List<ControlContextTableEntity> controlContextTableEntities) {
		this.controlContextTableEntities = controlContextTableEntities;
	}

	public ConfigurationPlaceEntity getConfigurarionPlaceEntity() {
		return configurarionPlaceEntity;
	}

	public void setConfigurarionPlaceEntity(
			ConfigurationPlaceEntity configurarionPlaceEntity) {
		this.configurarionPlaceEntity = configurarionPlaceEntity;
	}

}
