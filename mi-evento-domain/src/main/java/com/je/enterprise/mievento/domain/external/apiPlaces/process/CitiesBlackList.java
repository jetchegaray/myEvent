package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.je.enterprise.mievento.api.dto.location.CountryCode;
import com.je.enterprise.mievento.domain.entity.geo.CityEntity;
import com.je.enterprise.mievento.domain.utils.LoadFile;
import com.je.enterprise.mievento.domain.utils.PropertiesHelper;

@Component
public class CitiesBlackList implements LoadFile{

	private Properties properties;
	private PropertiesHelper helper;
	
	@PostConstruct
	public void load(){
		this.helper = new PropertiesHelper("com/je/enterprise/mievento/domain/apiPlaces/nonresults.properties");
		this.properties = helper.load();
	}
	
	
	public Set<CityEntity> getAllCitiesNoResult(CountryCode countryCode){
		
		List<String> values = Arrays.asList(properties.getProperty(countryCode.name()).split(","));
		
		return Sets.newLinkedHashSet(Lists.transform(values, new Function<String, CityEntity>(){

			@Override
			public CityEntity apply(String input) {
				return new CityEntity(input, StringUtils.EMPTY, StringUtils.EMPTY);
			}
			
		}));
	}
	
	public void setCityNoResult(CountryCode countryCode,CityEntity city){
			properties.setProperty(countryCode.name(), city.getName());
			this.helper.store();
	}
	
	
}
