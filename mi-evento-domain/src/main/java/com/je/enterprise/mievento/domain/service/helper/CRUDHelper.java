package com.je.enterprise.mievento.domain.service.helper;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.je.enterprise.mievento.domain.dao.BaseEntity;
import com.je.enterprise.mievento.domain.dao.GenericDAO;

@Component
public class CRUDHelper<T extends BaseEntity, K extends Serializable> {
	
	protected GenericDAO<T, K> dao;
	
	@Autowired
	public CRUDHelper(GenericDAO<T, K> dao){
		this.dao = dao;
	}
	
	public String create(T entity){
		return this.dao.save(entity).getId().toString();
	}
	
	public void update(T entity){
		if (this.dao.exists(entity)){
			this.dao.save(entity);
		}
	}
	
	public void delete(K key){
		this.dao.deleteById(key);
	}
	
}
