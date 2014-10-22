

mieventoContext.service("eventContext",function(){
	var editPerson = null;
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
	
	this.setEditPerson = function(person){
		editPerson = person;
	}
	
	this.getEditPerson = function(){
		return editPerson;
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
		this.selectedEvent.providers.push(provider);
	}
	
	this.setEditProvider = function(provider){
		editProvider = provider;
	}
	
	this.getEditProvider = function(){
		return editProvider;
	}
	
	
});