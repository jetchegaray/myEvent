## Table of Contents

1. [Technology Stack](#Technology-Stack)
1. [Description](#Description)
2. [Demo](#Demo)
3. [Environment variables](#Environment-variables)
4. [Instalation & Run](#Instalation-&-Run)
5. [Brief explanation of Arquitecture](#Brief-explanation-of-Arquitecture)
      - [API module](#API-module)
      - [Domain module](#Damain-module)
      - [Service module](#Service-module)
7. [Testing](#Testing)


## Technology Stack
 - Java 8
 - Maven 3.2
 - mongodb 3.2
 - morphia 1.2.2
 - spring 3.2.3.RELEASE 
 - Spring secutiry 3.2.3.RELEASE
 - thymeleaf 2.1.3
 - bower 1.6
 - angular 1.2
 - angular-dragdrop 1.0.1
 - bootstrap 3.1.0
 - flapdoodle 1.45
 - slf4j 1.7.5
 - guava 14.0.1

## Description

MyEvent is a project that you could find all your necessities for you weeding, birthday party, friend party, martz mitzvah ..  whatever party you want . you will have to choose a day in your calendar for you next event and 
   - pick your providers
   - send invitations 
   - organize invitations and following status
   - organize guesses
   - pick a place to do your party
   - customize your tables 
   - places of your guesses at the tables
   - manage your budget
   - your gifts and much more !


You can search a different providers of all your needs. this Beta version loads all the providers from Google places by Rest API 

   - Search for an specific country, province, town or city, different providers, like catering, place, video makers, DJs, bakers, etc. 
   - Add your guess and send invitations
   - Set up a place where you want to receive your gifts
   - Control bugdet with alerts and recommendations
   - ( Beta ) Customize the phisical place to set up your tables and where your guess will be seated. Drag a drop your guests into their tables. Next feature could be give the possibility to add the map of the place with its corresponding mensuration  
   - Manage Task for your party
   - Calendar for your party
   - Leave reviews to all your providers, linked to google places
   - Locales supported en_US, es_ES, pt_BR

## Demo

you can see a demo here [ https://youtu.be/5QKZjFuhEPs. ](https://www.youtube.com/watch?v=5QKZjFuhEPs)

## Environment variables

   local or dev this [env file](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-dev.properties)
   ```
      user=
      pass=
      host=localhost
      port=27017
      db=mi-evento

      urlhost=localhost:8080/mievento/
   ```
   
    prod this [env file](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-prod.properties
)
   ```
      user=javimetal
      pass=XXXXXX
      host=XXXXX
      port=51740
      db=XXXX

      urlhost=mi-evento.herokuapp.com/mievento/
   ```
   -  go to [API google Places](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiPlaces/services/ApiPlacesServicies.java)
   switch the value API_KEY for you api key of google places.
   
   - go to [GeoNames external API](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiGeo/services/GEOServicies.java)
    change the username . created at https://www.geonames.org/ 

  
## Instalation & Run

   - run this https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiGeo/LoadGeoNames.java
    to create all the cities into the database. 

   - run mvn clean install at the project root 

   - go to mi-evento-service and run 
      
      local or dev
      
      ```
      mvn spring-boot:run -Drun.jvmArguments="-Denvironment=dev"
     ``` 
     
     Prod 
    
    ```
      mvn spring-boot:run -Drun.jvmArguments="-Denvironment=prod" 
    ```
    
   - You shuld run a background process will start to download all the providers from google places by type , the class that controls over that is [this one](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/FullProvidersServiceData.java)

    
## Brief explanation of Arquitecture

This project uses MVC pattern using spring-boot & Java, is was splitted up in three modules, domain, api and service, using maven like I did, with a central xml config which keeps all the versions an dependencies in one place and then heritage form the rest of the modules just the dependencies that I need, you can upload this modules to your local company repository independently of each other. 

### API Module  
Expose my API, this Objects or contracts for the API, it is being used by the controllers when I need to send objects back to the client as a response of a request. If some External app should consume my controller, they will need import this module. to know about the DTO api objects or contract. 

 ```
 .
├── .env (Make sure to create this file locally and fill the env vars)
├── pom.xml (pom inherits version from general pom at the root of the app)
├── src
│   ├── country (country objects)
│   │   ├── City 
│   │   ├── Country 
│   │   └── State 
│   ├── Error 
│   │   └── EventError (Class to be mapped from EventErrorTransformer to be exposed to the outside)
│   ├── Event (All the entities related to Event, mapped by the right transformer in the domain)
│   │   ├── EventWithPlaces
│   │   │    ├── EventWithPlaceANdPressent (it is a full event with all the entities inside to use it for the present screen)
│   │   |    └── Presents 
│   │   ├── Event
│   │   ├── EventType 
│   │   ├── Guest 
│   │   ├── GuestStatusType
│   │   ├── InvitationStatus 
│   │   ├── Person
│   │   └── Task
│   ├── Location 
│   │   ├── CommercialLocation (extends from location adding the name of the place)
│   │   ├── CountryCode
│   │   ├── Location (proper location, composed with streetAdress and countryCode and lat and lng)
│   │   └── StreetAddress
│   ├──  




│   │   │    ├── fullCountry (Manages the comunication with the Fullcountry Firebase collection called FullCountry, country, states, cities)
│   │   │    ├── geonames (Manages the call the GeoNames API using Axios)
│   │   │    ├── province (Manages the call to the province and cities per country)
│   │   │    ├── zip (Manages the same functionality than country and fullCountry but returning an snapshot of the data)
│   │   │    └── country (Manages the comunication with the country Firebase collection called Country, second layer)
│   │   │
│   │   ├── entities (Contains all the entities to manage the DAO and DTO objects) 
│   │   ├── Intefaces (Manage the behaviour of objects when it is called geonames external API)
│   │   └── DTO (FullCountryDTO to map to the database country-states-city)
│   │ 
│   ├── interceptors (interceptos to manage logging and empty responses)   
│   └── logger (module and logger service)
└── test (Contains the end-to-end (e2e) tests)
```


### Domain Module
Define the POJOS for model objects, and DAO objects to access the database, it contains the communication to ORM morphia too. Transfomer generic funcionality to transform a DTO object to an DAO object 

### Service Module
Define the controllers represented by the front controller pattern of springs, it manages the security of the API, and it uses the API objects like an response of the controllers and the domain module to call the next layer in the application. 
Web Application is within this module. The Deployable module with angularJS, bower, and spring MVC with thymeleaf

## Testing

   Junit & Integration Tests::
   
      Black box and white box :::
      -  [TransformersImplTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/transformer/impl/TransformersImplTest.java)
      -  [ProviderServiceTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/service/impl/ProviderServiceTest.java)
      -  [CitiesWithNoResultTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/CitiesWithNoResultTest.java)
      -  [SearchKeywordsHelperTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/test/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/SearchKeywordsHelperTest.java)
      -  [LoadGeoNames](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiGeo/LoadGeoNames.java)
      -  [FullProvidersServiceDataTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/FullProvidersServiceDataTest.java)
      -  [ApiPlacesIntegrationTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiPlaces/services/ApiPlacesIntegrationTest.java)
      -  [MailServiceIntegrationTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/service/MailServiceIntegrationTest.java)
      -  [AllEntitiesServiceIntegrationTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/service/AllEntitiesServiceIntegrationTest.java)
      -  [UserServiceIntegrationTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/service/UserServiceIntegrationTest.java)
      -  [LoadContextTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/LoadContextTest.java)
      -  [MongoClientUtilsTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/MongoClientUtilsTest.java) 
      - [UserControllerTest](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/test/java/com/je/enterprise/mievento/service/controller/UserControllerTest.java)
      - [config file for integration tests](# https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/test/test-domain-context.xml) 
         

**Important files ::**

   - Configurations of Beans for dependency Injection -->  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/configuration 
   - Customize Exceptions -->  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/exception
   - customize annotations for customize exceptions  --->  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/java/com/je/enterprise/mievento/service/error/AnnotatedExceptionResolver.java 
   - Transformer generic class which all other transfomer classes will use https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/transformer
   - Spring factory bean  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/domain/domain-context.xml 
   - properties for dev and prod DB  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-dev.properties, mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-prod.properties
   - Customize serialization  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/java/com/je/enterprise/mievento/service/utils/SerializableResourceBundleMessageSource.java
   - customize locale and languages  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/resources/messages
   - Config for web deploy  https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/webapp/WEB-INF
  
