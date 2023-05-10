**Intro**

MiParty is a project that you could find all your necessities for you weeding, birthday party, friend party, martz mitzvah ..  whatever party you want . you would have to choose your calendar, send invitations , organize invitations, organize guesses status invitations, pick a place to do your party, customize your tables , places of your guesses at the tables, manage your budget, pick your providers, your gifts and much more !


**features **

You can search a different providers of all your needs. this Beta version loads all the providers from Google places by Rest API 
you can see a demo here https://youtu.be/5QKZjFuhEPs. 

   - Search for an specific country, province, town or city, different providers, like catering, place, video makers, DJs, bakers, etc. 
   - Add your guess and send invitations
   - Set up a place where you want to receive your gifts
   - Control bugdet with alerts and recommendations
   - ( Beta ) Customize the phisical place to set up your tables and where your guess will be seated. Drag a drop your guests into their tables. Next feature could be give the possibility to add the map of the place.   
   - Manage Task for your party
   - Calendar for your party
   - Leave reviews to all your providers, linked to google places
   - Locales supported en_US, es_ES, pt_BR


**how to run it** 

   -  go to https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiPlaces/services/ApiPlacesServicies.java
   switch the value API_KEY for you api key of google places.
   
   - go to https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiGeo/services/GEOServicies.java
    change the username . created at https://www.geonames.org/ 

   - have a mongo db locally running . mi-evento is the default DB but you can change it at 
   https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-dev.properties

   - run this https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiGeo/LoadGeoNames.java
    to create all the cities into the database. 

   - run mvn clean install at the project root 

   - go to mi-evento-service and run mvn spring-boot:run -Drun.jvmArguments="-Denvironment=dev"  for you local or 
    mvn spring-boot:run -Drun.jvmArguments="-Denvironment=prod" you might want to create a DB for prod and hitting from
    https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-prod.properties
    
   - an background process will start to download all the providers from google places by type , the class that controls over that is https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/FullProvidersServiceData.java

    
**
How was it implemented **

**Arquitecture **

This project uses MVC pattern using spring-boot & Java, is was splitted up in three modules, domain, api and service, using maven like I did, with a central xml config which keeps all the versions an dependencies in one place and then heritage form the rest of the modules just the dependencies that I need, you can upload this modules to your local company repository independently of each other. 

*API Module*  
Expose my API, this Objects or contracts for the API, it is being used by the controllers when I need to send objects back to the client as a response of a request. If some External app should consume my controller, they will need import this module. to know about the API objects or contract. 

*API Domain*
Define the POJOS for model objects, and DAO objects to access the database, it contains the communication to ORM morphia too. Transfomer generic funcionality to transform a DTO object to an API object 

*API Service*
Define the controllers represented by the front controller pattern of springs, it manages the security of the API, and it uses the API objects like an response of the controllers and the domain module to call the next layer in the application. 
Web Application is within this module. The Deployable module with angularJS, bower, and spring MVC with thymeleaf

**Testing **

   Junit & Integration Tests::
      Black box and white box :::
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/transformer/impl/TransformersImplTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/service/impl/ProviderServiceTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/CitiesWithNoResultTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/SearchKeywordsHelperTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiGeo/LoadGeoNames.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/FullProvidersServiceDataTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiPlaces/services/ApiPlacesIntegrationTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/service/MailServiceIntegrationTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/service/AllEntitiesServiceIntegrationTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/service/UserServiceIntegrationTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/LoadContextTest.java
      -  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/MongoClientUtilsTest.java 
      - https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/test/java/com/je/enterprise/mievento/service/controller/UserControllerTest.java
      - config file for integration tests  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/test/test-domain-context.xml 
         
         
       
**Technology Stack ::** 

   - Java 
   - Spring boot 
   - MongoDB
   - Morphia DB
   - Spring secutiry
   - Spring MVC to render front & thymeleaf
   - AngularJS, Bootstrap & bower
   - Mockito y hamcrest for testing
   - flapdoodle for embebed mongo database in memory
   - slf4j for logging 
   - jodaTime & BigDecimal to handle prices and dates
   - javax.mail 
   - GeoNames for lat and long to allow mongo execute its radial search. https://www.geonames.org/export/ 
   - GooglePlaces for populate the database https://developers.google.com/maps/documentation/places/web-service/overview  

**Important files :: **
   - Configurations of Beans for dependency Injection -->  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/configuration 
   - Customize Exceptions -->  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/exception
   - customize annotations for customize exceptions  --->  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/java/com/je/enterprise/mievento/service/error/AnnotatedExceptionResolver.java 
   - Transformer generic class which all other transfomer classes will use https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/transformer
   - Spring factory bean  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/domain/domain-context.xml 
   - properties for dev and prod DB  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-dev.properties, mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-prod.properties
   - Customize serialization  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/java/com/je/enterprise/mievento/service/utils/SerializableResourceBundleMessageSource.java
   - customize locale and languages  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/resources/messages
   - Config for web deploy  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/webapp/WEB-INF
  
