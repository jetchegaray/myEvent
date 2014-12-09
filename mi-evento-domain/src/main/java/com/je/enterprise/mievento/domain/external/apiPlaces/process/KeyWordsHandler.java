package com.je.enterprise.mievento.domain.external.apiPlaces.process;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.je.enterprise.mievento.domain.utils.LoadFile;
import com.je.enterprise.mievento.domain.utils.PropertiesHelper;

@Component
public class KeyWordsHandler implements LoadFile{

	Properties properties;
	
	@PostConstruct
	public void load(){
		PropertiesHelper helper = new PropertiesHelper("com/je/enterprise/mievento/domain/apiPlaces/keywords.properties");
		this.properties = helper.load();
	}
	
	
	public List<String> getKeywords(){
		
		List<Object> keys = Lists.newArrayList(properties.keySet());
		 
		return Lists.transform(keys, new Function<Object, String>(){

			@Override
			public String apply(Object input) {
				return String.valueOf(input);
			}
			
		});
			
	}
	
	
}
