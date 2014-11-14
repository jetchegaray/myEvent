
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
		loggedUser.events.push(event);
	}
	
	this.setUnLoggedUser = function(){
		loggedUser = null;
	}
	
	
});