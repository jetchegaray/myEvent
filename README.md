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
8. [Configuration files](#Configuration-files)


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
   - organize 
   es
   - pick a place to do your party
   - customize your tables 
   - places of your guestes at the tables
   - manage your budget
   - your gifts and much more !


You can search a different providers of all your needs. this Beta version loads all the providers from Google places by Rest API 

   - Search for an specific country, province, town or city, different providers, like catering, place, video makers, DJs, bakers, etc. 
   - Add your guest and send invitations
   - Set up a place where you want to receive your gifts
   - Control bugdet with alerts and recommendations
   - ( Beta ) Customize the phisical place to set up your tables and where your guest will be seated. Drag a drop your guests into their tables. Next feature could be give the possibility to add the map of the place with its corresponding mensuration  
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

This project uses MVC pattern using spring-boot & Java, the design is a hybrid service oriented domain model and object oriented domain model, it was splitted up in three modules, domain, api and service, using maven like I did, with a central xml config which keeps all the versions an dependencies in one place and then heritage form the rest of the modules just the dependencies that I need, you can upload this modules to your local company repository independently of each other.


### API Module  
Expose my API, this Objects or contracts for the API, it is being used by the controllers when I need to send objects back to the client as a response of a request. If some External app should consume my controller, they will need import this module. to know about the DTO api objects or contract. 

 ```
 
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
│   ├── Location (represents  the location of a provider)
│   │   ├── CommercialLocation (extends from location adding the name of the place)
│   │   ├── CountryCode
│   │   ├── Location (proper location, composed with streetAdress and countryCode and lat and lng)
│   │   └── StreetAddress
│   ├── Place (represents the place which will be the choosen to plan the event)
│   │   ├── CpnfigurationPlace (can support a map)
│   │   ├── ControlContextPlace 
│   │   ├── ControlContextTable (represents how to place the tables at the place)
│   │   └── Place 
│   ├── Provider (represents any kind of provider)
│   │   ├── Provider (can support a map)
│   │   ├── ProviderType (Enum, you will need to add here a type if you need something there is not)
│   │   └── Review 
│   ├── User (Represents the auth user object)
│   ├── Wrapper  
│   │   └── invitationDTO  (wrap the event, userEmail and guestEmail used by the invitationcontroller to send an invitation) 
└── └──
```


### Domain Module
Define the POJOS for model objects, and DAO objects to access the database, it contains the communication to ORM morphia too. Transfomer generic funcionality to transform a DTO object to an DAO object 

```

├── pom.xml (pom inherits version from general pom at the root of the app)
├── src
│   ├── IntegrationTest (country objects)
│   │   ├── MongoClientUtilsTest.java (Perform a tests to the DB using flapdoodle in memory) 
│   │   ├── External
│   │   │    ├── API GEO (LoadGeoNames to load all the countries and its states and its cities from API of Geonames into the DB)
│   │   │    └── API Places (Test the API and the entities coming from the external endpoint)
│   │   ├── Service 
│   │   │   ├── AllEntitiesServiceIntegrationTestjava (test save entities structures into the DB)
│   │   │   ├── MailServiceIntegrationTest.java (test the emailservice better a play than load the entire server to send an email for test)
│   │   │   └── UserServiceIntegration.java (Testing the UserService e2e with mongo in memory)
│   ├── Main (Contains all the services, repositories, transformers from DTO-DAO, etc)
│   │   ├── Configuration (all the @Configurations for beans creators)
│   │   │    ├── ConnectionConfiguration.java (exposes the RestTemplate to e used by the httpservice)
│   │   |    ├── CRUDHelperConfiguration.java (conf for CRUDHelper) 
│   │   |    ├── MongoConfiguration.java 
│   │   |    ├── ObjectMapperConfiguration.java (Object mapper for Json Serialization )
│   │   │    └── TransformerConfiguration.java (creates all the transformers and its dependecies)
│   │   ├── Context (manage the locale for the website)
│   │   ├── DAO (All the objects for represents the morphia objects of each collection on the DB)
│   │   │    ├── GenericDAO.java (custom representation of the BasicDAO of morphia)
│   │   │    ├── BaseEntity.java (the father of all DAOs)
│   │   │    └── impl 
│   │   │    │    ├── BlackListCityDAO.java (filtering cities)
│   │   │    │    ├── CountryDAO.java
│   │   │    │    ├── InvitationDAO.java
│   │   │    │    └── ProviderDAO.java
│   │   ├── Entitiy (contains all the objects for represents entities for the domain layer, these will perform the bussiness rules in the services or inside them )
│   │   │    ├── commonn/event (all the common entities to represents an event and its characteristics)
│   │   │    ├── geo.java (all the entities to manage external API calls)
│   │   │    ├── Invitation.java 
│   │   │    ├── location.java
│   │   │    ├── place.java
│   │   │    └── wedding.java (this type of event needs some particular neccesities to be represented, like the configuration of the place the and table distribution)
│   │   ├── Exception (contains all the custom exception to be returned by the services, it will return an exception, a customized code and http verb code)
│   │   │    ├── Customize (all the exceptions customized)
│   │   │    ├── HttpEventException.java (extends from RuntimeException)
│   │   │    ├── HttpEventExceptionCode.java (customize codes to return) 
│   │   │    └── HttpEventExceptionHttpStatus.java (codes for the http verbs)
│   │   ├── External (Services to manage the external calls to Geonames api and googleplaces api)
│   │   │    ├── ApiGeo 
│   │   │    │    ├── services (contains all the services which use an instance of RestTemplate) 
│   │   │    │    └── transformers (contains transform the response from geonames api to entities objects) 
│   │   │    ├── apiPlaces
│   │   │    │    ├── entities 
│   │   │    │    ├── process 
│   │   │    │    │    └── FullProvidersServiceData.java (import class to manage the quota, resilient4j, and chuunks of the response and call the ApiPlacesServicies)
│   │   │    │    ├── services 
│   │   │    │    │    └── ApiPlacesServicies.java (uses the Resttemplate to hit the api)
│   │   │    │    └── transformers  (transformers to switch the response from google places api to entities objects) 
│   │   ├── Service (Services to manage the bussiness logic which will be called by the controllers, and will the call the repositories)
│   │   │    ├── filters (all the filters for queries to the DB)
│   │   │    │    └── CriteriaFilterProvider.java (father class of all criterias)
│   │   │    ├── Helper (helper to serialize/deserialize DAOs and define the contract that I need from moprhia avoiding unneccesary methods)
│   │   │    │    └── CRUDHelper.java (generic implementation for talk to the genericDAO and the repositories) 
│   │   │    ├── impl (contains all implemented services to call the respoitories using generics and using the CRUDHelper)
│   │   ├── Transformer (all the classes to transform the DAO to entities and viceversa)
│   │   │    ├── Transformer.class (abtract generic class to define the behaviour of the transformations)
│   │   │    ├── TransformerList.class (abtract generic class to define the behaviour of the transformation lists)
│   │   │    └── impl (contain all the implemented child classes of the transformers using the classes above)
│   ├── Resources (Contains all the environment configuration)
│   │   ├── domain
│   │   │    ├── domain.context.xml  (scanner configuration to load all the classes in the configurations folder )
│   │   │    └── email.context.xml (scanner configuration for email functionality)
│   │   ├── mongodb (env variables for differents environments)
│   │   │    ├── scripts (scripts of creation for testing DB with fake documents)
│   │   │    ├── dataSource-dev.properties
│   │   │    └──  dataSource-prod.properties
│   │   ├── mongolab (scripts of creation for testing DB with fake documents)
│   │   └──  test (the configurations file for the integration tests)
│   ├── Tests (Junit tests)
│   │   ├── external (contains all the tests for external servcies)
│   │   ├── service (contains all the tests for services)
│   │   └── Transformer (contains all the tests for transformers)
└──
```




### Service Module
Define the controllers represented by the front controller pattern of springs, it manages the security of the API, and it uses the API objects like an response of the controllers and the domain module to call the next layer in the application. 
Web Application is within this module. The Deployable module with angularJS, bower, and spring MVC with thymeleaf

```

├── pom.xml (pom inherits version from general pom at the root of the app)
├── src
│   ├── IntegrationTest (country objects)
│   │   ├── MongoClientUtilsTest.java (Perform a tests to the DB using flapdoodle in memory) 
│   │   ├── External
│   │   │    ├── API GEO (LoadGeoNames to load all the countries and its states and its cities from API of Geonames into the DB)
│   │   │    └── API Places (Test the API and the entities coming from the external endpoint)
│   │   ├── Service 
│   │   │   ├── AllEntitiesServiceIntegrationTestjava (test save entities structures into the DB)
│   │   │   ├── MailServiceIntegrationTest.java (test the emailservice better a play than load the entire server to send an email for test)
│   │   │   └── UserServiceIntegration.java (Testing the UserService e2e with mongo in memory)
│   ├── Main (Contains all the services, repositories, transformers from DTO-DAO, etc)
│   │   ├── Configuration (all the @Configurations for beans creators)
│   │   │    ├── ConnectionConfiguration.java (exposes the RestTemplate to e used by the httpservice)
│   │   |    ├── CRUDHelperConfiguration.java (conf for CRUDHelper) 
│   │   |    ├── MongoConfiguration.java 
│   │   |    ├── ObjectMapperConfiguration.java (Object mapper for Json Serialization )
│   │   │    └── TransformerConfiguration.java (creates all the transformers and its dependecies)
│   │   ├── Context (manage the locale for the website)
│   │   ├── DAO (All the objects for represents the morphia objects of each collection on the DB)
│   │   │    ├── GenericDAO.java (custom representation of the BasicDAO of morphia)
│   │   │    ├── BaseEntity.java (the father of all DAOs)
│   │   │    └── impl 
│   │   │    │    ├── BlackListCityDAO.java (filtering cities)
│   │   │    │    ├── CountryDAO.java
│   │   │    │    ├── InvitationDAO.java
│   │   │    │    └── ProviderDAO.java
│   │   ├── Entitiy (contains all the objects for represents entities for the domain layer, these will perform the bussiness rules in the services or inside them )
│   │   │    ├── commonn/event (all the common entities to represents an event and its characteristics)
│   │   │    ├── geo.java (all the entities to manage external API calls)
│   │   │    ├── Invitation.java 
│   │   │    ├── location.java
│   │   │    ├── place.java
│   │   │    └── wedding.java (this type of event needs some particular neccesities to be represented, like the configuration of the place the and table distribution)
│   │   ├── Exception (contains all the custom exception to be returned by the services, it will return an exception, a customized code and http verb code)
│   │   │    ├── Customize (all the exceptions customized)
│   │   │    ├── HttpEventException.java (extends from RuntimeException)
│   │   │    ├── HttpEventExceptionCode.java (customize codes to return) 
│   │   │    └── HttpEventExceptionHttpStatus.java (codes for the http verbs)
│   │   ├── External (Services to manage the external calls to Geonames api and googleplaces api)
│   │   │    ├── ApiGeo 
│   │   │    │    ├── services (contains all the services which use an instance of RestTemplate) 
│   │   │    │    └── transformers (contains transform the response from geonames api to entities objects) 
│   │   │    ├── apiPlaces
│   │   │    │    ├── entities 
│   │   │    │    ├── process 
│   │   │    │    │    └── FullProvidersServiceData.java (import class to manage the quota, resilient4j, and chuunks of the response and call the ApiPlacesServicies)
│   │   │    │    ├── services 
│   │   │    │    │    └── ApiPlacesServicies.java (uses the Resttemplate to hit the api)
│   │   │    │    └── transformers  (transformers to switch the response from google places api to entities objects) 
│   │   ├── Service (Services to manage the bussiness logic which will be called by the controllers, and will the call the repositories)
│   │   │    ├── filters (all the filters for queries to the DB)
│   │   │    │    └── CriteriaFilterProvider.java (father class of all criterias)
│   │   │    ├── Helper (helper to serialize/deserialize DAOs and define the contract that I need from moprhia avoiding unneccesary methods)
│   │   │    │    └── CRUDHelper.java (generic implementation for talk to the genericDAO and the repositories) 
│   │   │    ├── impl (contains all implemented services to call the respoitories using generics and using the CRUDHelper)
│   │   ├── Transformer (all the classes to transform the DAO to entities and viceversa)
│   │   │    ├── Transformer.class (abtract generic class to define the behaviour of the transformations)
│   │   │    ├── TransformerList.class (abtract generic class to define the behaviour of the transformation lists)
│   │   │    └── impl (contain all the implemented child classes of the transformers using the classes above)
│   ├── Resources (Contains all the environment configuration)
│   │   ├── domain
│   │   │    ├── domain.context.xml  (scanner configuration to load all the classes in the configurations folder )
│   │   │    └── email.context.xml (scanner configuration for email functionality)
│   │   ├── mongodb (env variables for differents environments)
│   │   │    ├── scripts (scripts of creation for testing DB with fake documents)
│   │   │    ├── dataSource-dev.properties
│   │   │    └──  dataSource-prod.properties
│   │   ├── mongolab (scripts of creation for testing DB with fake documents)
│   │   └──  test (the configurations file for the integration tests)
│   ├── Tests (Junit tests)
│   │   ├── external (contains all the tests for external servcies)
│   │   ├── service (contains all the tests for services)
│   │   └── Transformer (contains all the tests for transformers)
└──
```


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
         

## Configuration files

   - [Configurations of Beans for dependency Injection](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/configuration) 
   - [Customize Exceptions](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/exception)
   - [customize annotations for customize exceptions](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/java/com/je/enterprise/mievento/service/error/AnnotatedExceptionResolver.java)
   - [Transformer generic class which all other transfomer classes will use](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/transformer)
   - [Spring factory bean](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/domain/domain-context.xml)
   - [properties for dev and prod DB](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-dev.properties, mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-prod.properties)
   - [Customize serialization](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/java/com/je/enterprise/mievento/service/utils/SerializableResourceBundleMessageSource.java)
   - [customize locale and languages](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/resources/messages)
   - [Config for web deploy](#https://github.com/jetchegaray/myEvent/blob/master/mi-evento-service/src/main/webapp/WEB-INF)
  
