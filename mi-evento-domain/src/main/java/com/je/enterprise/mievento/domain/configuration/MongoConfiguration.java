package com.je.enterprise.mievento.domain.configuration;

import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.domain.utils.BigDecimalConverter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;


@Configuration
public class MongoConfiguration {

	@Value("${user}")
	private String user;
	@Value("${pass}")
	private String pass;
	@Value("${host}")
	private String host;
	@Value("${port}")
	private String port;
	@Value("${db}")
	private String db;
	
	@Bean
	public Datastore MongoConnectionManager() {
		MongoClient client = null;
		MongoClientURI uri  = new MongoClientURI(this.getURI()); 
		
//		MongoClientURI uri  = new MongoClientURI("mongodb://javimetal:Dickis666@ds051740.mongolab.com:51740/heroku_app31605532"); 
			
		try {
		   client = new MongoClient(uri);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		Datastore ds = morphia.createDatastore(client, uri.getDatabase());
		ds.setDefaultWriteConcern(WriteConcern.FSYNCED);
		return ds;
	}
	
	
	private String getURI(){
		StringBuilder builder = new StringBuilder("mongodb://");
		if (!StringUtils.isBlank(this.user)){
			builder.append(this.user);
			builder.append(":");
			builder.append(this.pass);
			builder.append("@");
		}
		builder.append(this.host);
		builder.append(":");
		builder.append(this.port);
		builder.append("/");
		builder.append(this.db);
		
		return builder.toString();
	}

	

}
