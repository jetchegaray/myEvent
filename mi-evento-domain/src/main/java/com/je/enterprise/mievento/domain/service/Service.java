package com.je.enterprise.mievento.domain.service;

import java.io.Serializable;
import java.util.List;

import com.je.enterprise.mievento.domain.dao.BaseEntity;

public interface Service<T extends BaseEntity, K extends Serializable> {

	String create(T entity);

	void update(T entity);

	void delete(K key);

	List<T> getAll();

}
