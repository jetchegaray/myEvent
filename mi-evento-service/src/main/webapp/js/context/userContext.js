
mieventoContext.service("userContext", function(){
	
	var loggedUser = null;
	
	
	this.setLoggedUser = function(user){
		loggedUser = user;
	}
	
	this.getLoggedUser = function(){
		return loggedUser;
	}
	
	this.getLoggedUserEvents = function(){
		if (this.loggedUser == null){
			return null;
		}
		return this.loggedUser.events;
	}
	
	this.addUserSelectedEvent = function(event){
		this.loggedUser.events.push(event);
	}
	
	this.setUnLoggedUser = function(){
		this.loggedUser = null;
	}
	
});