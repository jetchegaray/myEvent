package com.je.enterprise.mievento.domain.dao;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.mapping.MappedClass;
import org.mongodb.morphia.mapping.Mapper;

public class GenericDAO<T extends BaseEntity, K extends Serializable> extends BasicDAO<T, K> 
{
	
	public GenericDAO(Class<T> entityClass,Datastore ds){
		super(entityClass,ds);
		ds.ensureIndexes(entityClass, true);
	}
	
	public String getEntityIdField(){
		MappedClass mclass = ((DatastoreImpl)this.getDatastore()).getMapper().getMappedClass(this.getEntityClass());
		return mclass.getMappedIdField().getJavaFieldName();
	}

	public Key<Object> getRefKey(Object o){
		return this.getDatastore().getKey(o);
	}
	
	public T findById(K key){
		return this.findOne(Mapper.ID_KEY,key);
	}
	
	public List<T> findAll(){
		return this.find().asList();
	}
	
	public boolean exists(T entity){
		return this.exists(Mapper.ID_KEY,this.getEntityId(entity));
	}
	
	private Object getEntityId(T entity){
		MappedClass mclass = ((DatastoreImpl)this.getDatastore()).getMapper().getMappedClass(this.getEntityClass());
		
		try {
			return mclass.getIdField().get(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}