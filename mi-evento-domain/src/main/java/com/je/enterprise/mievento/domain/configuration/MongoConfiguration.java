package com.je.enterprise.mievento.domain.configuration;

import java.net.UnknownHostException;


import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;


@Configuration
public class MongoConfiguration {

	public static final String DB_NAME = "mi-evento";

	@Bean
	public Datastore MongoConnectionManager() {
		MongoClient client = null;
		try {
			client = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Datastore ds = new Morphia().createDatastore(client, DB_NAME);
		ds.setDefaultWriteConcern(WriteConcern.FSYNCED);
		return ds;
		//mapPackage("at.ac.tuwien.ec.mongouk2011.entities")
	}

}
