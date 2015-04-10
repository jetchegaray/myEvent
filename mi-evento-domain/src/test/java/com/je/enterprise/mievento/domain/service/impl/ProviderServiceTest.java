package com.je.enterprise.mievento.domain.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.service.filters.CriteriaFilterProvider;
import com.je.enterprise.mievento.domain.service.helper.CRUDHelper;

public class ProviderServiceTest {

	@Mock
	private CRUDHelper<ProviderEntity, ObjectId> crudHelper;
	@Mock
	private ProviderDAO providerDao;

	ProviderService providerService;
	
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		Mockito.when(providerDao.findBy(Mockito.any(CriteriaFilterProvider.class))).thenReturn(createProviders());
		Mockito.when(crudHelper.getDao()).thenReturn(providerDao);
	}
	
	@Test
	public void getMostCheaper_ok(){
		
	}

	
	private List<ProviderEntity> createProviders() {
		
		return null;
	}
	
}
