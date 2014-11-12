
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
	
	//private
	getEventByName = function(name){
		
		var foundEvent = null;
		angular.forEach(loggedUser.events, function(event){
			if (angular.equals(event.name,name)){
				foundEvent = event;
			}
		});
		return foundEvent;
	}
	
	this.addTaskToEvent = function(eventName, task){
		
		var event = getEventByName(eventName);
		
		if (event.tasks == null){
			event.tasks = [];
		}
		event.tasks.push(task);	
		console.log(angular.toJson(event));
	}
	
	
	this.deleteTask = function(event, task){
		
	}
	
	
});