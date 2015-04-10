package com.je.enterprise.mievento.domain.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

	private String fileName;
	
	public PropertiesHelper(String name) {
		this.fileName = name;
	}
	
	public Properties load(){
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
