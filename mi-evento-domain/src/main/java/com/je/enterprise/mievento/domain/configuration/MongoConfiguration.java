package com.je.enterprise.mievento.domain.configuration;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.je.enterprise.mievento.domain.utils.BigDecimalConverter;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;


@Configuration
public class MongoConfiguration {

	public static final String DB_NAME = "mi-evento";
	
	
	 @Bean
    public DB getDb() throws UnknownHostException, MongoException {
		 
		 MongoClientURI uri  = new MongoClientURI("mongodb://javimetal:Dickis666@ds051740.mongolab.com:51740/heroku_app31605532"); 
	     MongoClient client = new MongoClient(uri);
	     DB db = client.getDB(uri.getDatabase());

        return db;
    }

	@Bean
	public Datastore MongoConnectionManager() {
		MongoClient client = null;
//		MongoClientURI uri  = new MongoClientURI("mongodb://javimetal:Dickis666@ds051740.mongolab.com:51740/heroku_app31605532"); 
			
		try {
//		     client = new MongoClient(uri);
		   client = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		Datastore ds = morphia.createDatastore(client, DB_NAME);
//		Datastore ds = morphia.createDatastore(client, uri.getDatabase());
		ds.setDefaultWriteConcern(WriteConcern.FSYNCED);
		return ds;
	}
	
	

	

}
