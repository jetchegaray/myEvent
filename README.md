MiEvent is a project that you could find all your necessities for you weeding, birthday party, friend party, martz mitzvah ..  whatever party you want . you would have to choose your calendar, send invitations , organize invitations, organize guesses status invitations, pick a place to do your party, customize your tables , places of your guesses at the tables, manage your budget, pick your providers, your gifts and much more !


features 

You can search a different providers of all your needs. this Beta version loads all the providers from Google places by Rest API https://youtu.be/5QKZjFuhEPs 




how to use it 


1 - go to https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiPlaces/services/ApiPlacesServicies.java
   switch the value API_KEY for you api key of google places.

2 - go to https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiGeo/services/GEOServicies.java
    change the username . created at https://www.geonames.org/ 

3 - have a mongo db locally running . mi-evento is the default DB but you can change it at 
   https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-dev.properties

4 - run this https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/integration-test/java/com/je/enterprise/mievento/domain/external/apiGeo/LoadGeoNames.java
    to create all the cities into the database. 

5 - run mvn clean install at the project root 

6 - go to mi-evento-service and run mvn spring-boot:run -Drun.jvmArguments="-Denvironment=dev"  for you local or 
    mvn spring-boot:run -Drun.jvmArguments="-Denvironment=prod" you might want to create a DB for prod and hitting from
    https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/resources/com/je/enterprise/mievento/mongodb/dataSource-prod.properties
    
7 - an background process will start to download all the providers from google places by type , the class that controls over that is https://github.com/jetchegaray/myEvent/blob/master/mi-evento-domain/src/main/java/com/je/enterprise/mievento/domain/external/apiPlaces/process/FullProvidersServiceData.java

    

How was implemented 


There is a domain module which have DAO objects and POJO model objects. Expose service classes to be used from the service module. which have the controllers and the main entrance to the application,  all the request and response objects which the service module exposes are API object from the API module. they are the objects to talk to the exterior of the application. 

there are a lot of integration an junit tests , mimic all the flows , so take a look  to have a better understanding of the code. ;) 

