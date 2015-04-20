package com.je.enterprise.mievento.domain.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.collect.Lists;

public class FileHelper {

	private String fileName;
	
	public FileHelper(String name) {
		this.fileName = name;
	}
	
	public List<String> initToRead(){
		
		List<String> lines = Lists.newArrayList();
		BufferedReader buffer = null;
    	try {
    		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
    		buffer =  new BufferedReader(new InputStreamReader(resourceAsStream,"UTF-8"));
			String line = buffer.readLine();
			
			do{
				lines.add(line);
			}while ((line = buffer.readLine()) != null);
	       
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();	 
		}
    	return lines;
	}
	
	
	
	public BufferedWriter initToWrite(){
		
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		BufferedWriter buffer = null;
    	try {
    		buffer =  new BufferedWriter(new FileWriter(this.fileName));
    	} catch (IOException e) {
			e.printStackTrace();	 
		}
		return buffer;
	}


}
