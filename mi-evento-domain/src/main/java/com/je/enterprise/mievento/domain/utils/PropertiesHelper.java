package com.je.enterprise.mievento.domain.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

	private String fileName;
	private Properties properties = new Properties();
	
	public PropertiesHelper(String name) {
		this.fileName = name;
	}
	
	public Properties load(){
		try {
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
			this.properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
}
