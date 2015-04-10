package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.collect.Lists;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.dao.impl.ProviderDAO;
import com.je.enterprise.mievento.domain.entity.common.event.ProviderEntity;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.DetailPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.entities.SearchPlace;
import com.je.enterprise.mievento.domain.external.apiPlaces.services.ApiPlacesServicies;
import com.je.enterprise.mievento.domain.transformer.TransformerList;

@ContextConfiguration(locations = {"classpath:com/je/enterprise/mievento/test/test-domain-context.xml"})
public class FullProvidersServiceDataTest {




	
}
