package com.je.enterprise.mievento.domain;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
 

public class MongoClientUtilsTest {

	private static Version DEFAULT_MONGO_VERSION = Version.V2_4_9;
	private static String DEFAULT_MONGO_DB_NAME = "mi-evento-test";
	private static String DEFAULT_MONGO_HOST = "127.0.0.1";
	private static int DEFAULT_MONGO_PORT = 277119;
	
	protected MongoClient mongo;
	protected MongodProcess mongoDaemon;
	
	public Mongo startMongoDB() throws Exception{
		MongodStarter runtime = MongodStarter.getDefaultInstance();
		IMongodConfig build = new MongodConfigBuilder().version(DEFAULT_MONGO_VERSION).net(new Net(DEFAULT_MONGO_PORT, Network.localhostIsIPv6())).build();
		MongodExecutable mongodExe = runtime.prepare(build);
	
		mongoDaemon = mongodExe.start();
		mongo = new MongoClient(DEFAULT_MONGO_HOST,DEFAULT_MONGO_PORT);
		
		return mongo;
	}
	
	public synchronized void getCleanMongoDB(){
		this.mongo.getDB(DEFAULT_MONGO_DB_NAME).dropDatabase();
	}
	
	public void stopMongoDB(){
		if (mongoDaemon != null){
			mongoDaemon.stop();
		}
	}
	
	
	public Datastore getDataStore(){
		return new Morphia().createDatastore(mongo, DEFAULT_MONGO_DB_NAME);
	}
	
	
	
}
