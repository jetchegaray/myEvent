

mieventoContext.service("eventContext",function(){
	var editGuest = null;
	var editProvider = null;
	
	var selectedEvent = null;
	
	//***********SELECTED EVENT **********
	
	this.getSelectedEvent = function(){
		return selectedEvent;
	}
	
	this.setSelectedEvent = function(selectedEvent){
		this.selectedEvent = selectedEvent;
	}
	
	this.deselectedEvent = function(){
		this.selectedEvent = null;
	}
	//***********GUESTS EVENT **********
	
	this.getGuestsSelectedEvent = function(){
		if (this.selectedEvent == null){
			return null;
		}
		return this.selectedEvent.guests;
	}
	
	this.addGuestSelectedEvent = function(guest){
		this.selectedEvent.guests.push(guest);
	}
	
	this.setEditGuest = function(guest){
		editGuest = guest;
	}
	
	this.getEditGuest = function(){
		return editGuest;
	}
	
	
	//*********PLACE EVENT ************
	
	this.getPlaceSelectedEvent = function(){
		if (this.selectedEvent == null){
			return null;
		}
		return this.selectedEvent.place;
	}
	
	this.setPlaceSelectedEvent = function(place){
		this.selectedEvent.place = place;
	}
	
	//**********PROVIDERS EVENT ***********
	
	this.getProvidersSelectedEvent = function(){
		return this.selectedEvent.providers;
	}
	
	this.addProviderSelectedEvent = function(provider){
		if (this.selectedEvent.providers == null){
			this.selectedEvent.providers = [];
		}
		this.selectedEvent.providers.push(provider);
	}
	
	this.setEditProvider = function(provider){
		editProvider = provider;
	}
	
	this.getEditProvider = function(){
		return editProvider;
	}
	
	
});