

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
			return null;
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
	
	this.addProviderSelectedEvent = function(provider){
		if (selectedEvent.providers == null){
			selectedEvent.providers = [];
		}
		selectedEvent.providers.push(provider);
	}
	
	this.setEditProvider = function(provider){
		editProvider = provider;
	}
	
	this.getEditProvider = function(){
		return editProvider;
	}
	
});