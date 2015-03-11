

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
	
	
	
	this.getEventLocationSelectedEvent = function(){
		if (selectedEvent == null){
			return null;
		}
		
		return selectedEvent.eventLocation;
	}
	
	this.setEventLocationSelectedEvent = function(place){
		selectedEvent.eventLocation = toProviderPlaceToCommercialLocation(place);
	}
	
	toProviderPlaceToCommercialLocation = function(place){
		var eventLocation = {};
		eventLocation.placeName = place.businessName;
		eventLocation.location = place.location;
//		eventLocation.city
//		eventLocation.countryCode
//		eventLocation.streetAddress.street
//		eventLocation.streetAddress.number
//		eventLocation.streetAddress.additionalInfo 
//		eventLocation.streetAddress.neighborhood
		return eventLocation;
	}
	
	
	//**********PROVIDERS EVENT ***********
	
	this.getProvidersSelectedEvent = function(){
		return selectedEvent.providers;
	}
	
	this.setProvidersSelectedEvent = function(providers){
		selectedEvent.providers = providers;
	}
	
	this.addProviderSelectedEvent = function(provider){
		if (selectedEvent.providers == null){
			selectedEvent.providers = [];
		}
		selectedEvent.providers.push(provider);
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
	
	this.getTotalBuggetSelectedEvent = function(){
		
		if (selectedEvent == null || selectedEvent.providers == null){
			return 0;
		}
		return _.reduce(selectedEvent.providers, function(memo, provider){ return memo + provider.estimatedPrice; }, 0);
	}
	
	//**********PROVIDERS TO COMPARE IN EVENT ***********
	
	this.getProvidersToCompareEvent = function(){
		if (selectedEvent == null || selectedEvent.providersToCompare){
			return arrays();
		}
		return selectedEvent.providersToCompare;
	}
	
	this.setProvidersToCompareEvent = function(providers){
		selectedEvent.providersToCompare = providers;
	}
	
	this.addProviderToCompareEvent  = function(providerToCompare){
		if (selectedEvent.providers != null){
			var foundIt = _.find(selectedEvent.providers,function(providerOriginal){ providerOriginal === providerToCompare});
			if (foundIt != null){
				console.log("The provider alredy exists in original list of providers.");
			}
		}
		
		if (selectedEvent.providersToCompare == null){
			selectedEvent.providersToCompare = [];
		}
		selectedEvent.providersToCompare.push(providerToCompare);
	}
	
	
	//**************TASKS EVENT *********************
	
	this.addTaskToEvent = function(task){
		if (selectedEvent.tasks == null){
			selectedEvent.tasks = [];
		}
		selectedEvent.tasks.push(task);	
	}
	
	
	this.deleteTaskFromEvent = function(task){
		var index = selectedEvent.tasks.indexOf(task)
		selectedEvent.tasks.splice(index, 1);
	}
	
});