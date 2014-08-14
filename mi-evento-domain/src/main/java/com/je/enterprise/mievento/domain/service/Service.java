package com.je.enterprise.mievento.domain.service;

import java.io.Serializable;

import com.je.enterprise.mievento.domain.dao.BaseEntity;
import com.je.enterprise.mievento.domain.dao.GenericDAO;


public interface Service<T extends BaseEntity, K extends Serializable> {
	
	public String create(T entity);
	
	public void update(T entity);
	
	public void delete(K key);
	
}
