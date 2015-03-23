

mieventoContext.service("applicationContext", ["userContext", "eventContext", "exceptionContext", "providerContext", "countryContext",
                                               function(userContext, eventContext, exceptionContext, providerContext, countryContext){
	var previousState = null;
	var eventContext = eventContext;
	var exceptionContext = exceptionContext;
	var providerContext = providerContext;
	var userContext = userContext;
	var countryContext = countryContext;


	this.setPreviousState = function(state){
		previousState = state;
	}
	
	this.getPreviousState = function(){
		return previousState;
	}
	
	
	//********** GETTER CONTEXT'S ************
	
	this.getEventContext = function(){
		return eventContext;
	}
	
	this.getExceptionContext = function(){
		return exceptionContext;
	}
	
	this.getProviderContext = function(){
		return providerContext;
	}
	
	this.getUserContext = function(){
		return userContext;
	}
	
	this.getCountryContext = function(){
		return countryContext;
	}
	
	
}]);