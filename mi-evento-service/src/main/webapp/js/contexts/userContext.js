
mieventoContext.service("userContext", function(){
	
	var loggedUser = null;
	
	this.setLoggedUser = function(user){
		loggedUser = user;
	}
	
	this.getLoggedUser = function(){
		return loggedUser;
	}
	
	this.getLoggedUserEvents = function(){
		if (loggedUser == null){
			return null;
		}
		return loggedUser.events;
	}
	
	this.addUserEvent = function(event){
		if (event.type == "Boda o Casamiento" || event.type.search("Cumplea") == 0 || event.type === "Fiesta" || event.type === "Bar Mitzvah"){
			event.clazz="EventWithPlaceAndPresent"
		}else{
			event.clazz="Event"
		}
		loggedUser.events.push(event);
	}
	
	this.setUnLoggedUser = function(){
		loggedUser = null;
	}
	
	
});