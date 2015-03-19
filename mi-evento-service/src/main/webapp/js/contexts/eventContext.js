

mieventoContext.service("eventContext",function(){
	var editGuest = null;
	var editProvider = null;
	var editEvent = null;
	var selectedEvent = null;
	
	//***********SELECTED EVENT **********
	
	this.getSelectedEvent = function(){
		return selectedEvent;
	}
	
	this.setSelectedEvent = function(event){
		selectedEvent = event;
	}
	
	this.deselectedEvent = function(){
		selectedEvent = null;
	}
	
	this.setEditEvent = function(event){
		editEvent = event;
	}
	
	this.getEditEvent = function(){
		return editEvent;
	}
	
	
	//***********GUESTS EVENT **********
	
	this.getGuestsSelectedEvent = function(){
		if (selectedEvent == null){
			selectedEvent.guests = [];
		}
		if (selectedEvent.guests == null){
			selectedEvent.guests = [];
		}
		return selectedEvent.guests;
	}
	
	this.addGuestSelectedEvent = function(guest){
		selectedEvent.guests.push(guest);
	}
	
	this.setEditGuest = function(guest){
		editGuest = guest;
	}
	
	this.getEditGuest = function(){
		return editGuest;
	}
	
	
	//*********PLACE EVENT ************
	
	
	
	this.getPlaceSelectedEvent = function(){
		if (selectedEvent == null){
			return null;
		}
		
		return selectedEvent.place;
	}
	
	this.setPlaceSelectedEvent = function(place){
		selectedEvent.place = place;
	}
	
	
	//**********PROVIDERS EVENT ***********
	
	this.getProvidersSelectedEvent = function(){
		return selectedEvent.providers;
	}
	
	this.setProvidersSelectedEvent = function(providers){
		selectedEvent.providers = providers;
	}
	
	this.addProviderSelectedEvent = function(newProvider){
		var error = this.isExistsInProvider(newProvider);
		if (error != null){
			 return error;
		}
		if (selectedEvent.providers == null){
			selectedEvent.providers = [];
		}
		selectedEvent.providers.push(newProvider);
	}
	
	
	this.isExistsInProvider = function(newProvider){
		
		if (selectedEvent.providers != null){
			var foundIt = _.find(selectedEvent.providers,function(provider){ return provider.businessId == newProvider.businessId});
			if (! angular.isUndefined(foundIt)){
				 return error = {code : 0007, description : "El proveedor ya se ha agregado !"};
			}
		}
		return null;
	}
	
	
	this.removeProviderSelectedEvent = function(provider){
		if (selectedEvent.providers == null){
			selectedEvent.providers = [];
		}
		var index = list.indexOf(provider)
		selectedEvent.providers.splice(index, 1);
	}
	
	this.setEditProvider = function(provider){
		editProvider = provider;
	}
	
	this.getEditProvider = function(){
		return editProvider;
	}
	
	this.getTotalBudgetSelectedEvent = function(){
		
		if (selectedEvent == null || selectedEvent.providers == null){
			return 0;
		}
		return _.reduce(selectedEvent.providers, function(memo, provider){ return parseInt(memo) + parseInt(provider.estimatedPrice); }, 0);
	}
	
	//**********PROVIDERS TO COMPARE IN EVENT ***********
	
	this.getProvidersToCompareEvent = function(){
		if (selectedEvent == null || selectedEvent.providersToCompare == null){
			return [];
		}
		return selectedEvent.providersToCompare;
	}
	
	this.setProvidersToCompareEvent = function(providers){
		selectedEvent.providersToCompare = providers;
	}
	
	this.addProviderToCompareEvent  = function(providerToCompare){
		if (selectedEvent.providersToCompare != null){
			var foundIt = _.find(selectedEvent.providersToCompare,function(provider){ return provider.businessId == providerToCompare.businessId});
			if (! angular.isUndefined(foundIt)){
				 return error = {code : 0006, description : "El proveedor ya se ha agregado para comparar !"};
			}
		}else{
			selectedEvent.providersToCompare = [];
		}
		selectedEvent.providersToCompare.push(providerToCompare);
		return null;
	}
	
	
	//**************TASKS EVENT *********************
	
	this.addTaskToEvent = function(task){
		if (selectedEvent.tasks == null){
			selectedEvent.tasks = [];
		}
		selectedEvent.tasks.push(task);	
	}
	
	
	this.deleteTaskFromEvent = function(task){
		var index = selectedEvent.tasks.indexOf(task);
		selectedEvent.tasks.splice(index, 1);
	}
	
});