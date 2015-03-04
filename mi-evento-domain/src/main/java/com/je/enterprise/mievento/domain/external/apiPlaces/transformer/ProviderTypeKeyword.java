package com.je.enterprise.mievento.domain.external.apiPlaces.transformer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.je.enterprise.mievento.api.dto.provider.ProviderType;
import com.je.enterprise.mievento.domain.utils.LoadFile;
import com.je.enterprise.mievento.domain.utils.PropertiesHelper;

@Component
public class ProviderTypeKeyword implements LoadFile{

	Properties properties;
	
	@PostConstruct
	public void load(){
		PropertiesHelper helper = new PropertiesHelper("com/je/enterprise/mievento/domain/apiPlaces/providerType.properties");
		this.properties = helper.load();
	}
	
	
	public Map<ProviderType, List<String>> getProviderTypeKeyword(){
		
		Map<ProviderType, List<String>> providerKeyword = Maps.<ProviderType, List<String>>newHashMap();
		for (ProviderType providerType : ProviderType.values()) {
		
			String value = String.valueOf(properties.get(providerType.getName()));
			
			String[] citiesAbrv = value.split(",");
			
			providerKeyword.put(providerType, Arrays.asList(citiesAbrv));
		}
		return providerKeyword;
	}
	
	
}
