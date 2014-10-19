

mieventoContext.service("eventContext",function(){
	var editPerson = null;
	var deletePerson = null;
	
	this.setEditPerson = function(person){
		editPerson = person;
	}
	
	this.getEditPerson = function(){
		return deletePerson;
	}
	
	this.setDeletePerson = function(person){
		editPerson = person;
	}
	
	this.getDeletePerson = function(){
		return deletePerson;
	}
	
});