package com.je.enterprise.mievento.domain.utils;

import java.util.Properties;

public class ProviderTypeHelper{

	private Properties properties;
	private PropertiesHelper helper;
	
	public ProviderTypeHelper(String language) {
		this.helper = new PropertiesHelper("/messages/messages_"+language+".properties");
		this.properties = helper.load();
	}

	
}
