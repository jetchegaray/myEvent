package com.je.enterprise.mievento.domain.dao;

import java.io.Serializable;

public interface GenericDAO<T extends BaseEntity, K extends Serializable>
{
    public void insert(T entity);
    public T queryByKey(Class<T> typeClass, K id);
}