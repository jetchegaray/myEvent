package com.je.enterprise.mievento.domain.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesHelper {

	private String fileName;
	private Properties properties = new Properties();
	private FileOutputStream output;
	
	public PropertiesHelper(String name) {
		this.fileName = name;
	}
	
	public Properties load(){
		try {
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
			URL resource = this.getClass().getClassLoader().getResource(fileName);
			this.properties.load(resourceAsStream);
		 	output = new FileOutputStream(new File(resource.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public void store(){
		try {
			properties.store(output,null);
			output.flush();
	//	output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
