TAG_ERROR_UPDATE = "errorUpdated";

mieventoContext.service("exceptionContext",function($rootScope){

	var error = {
			"type" : null,
			"code" : null,
			"httpStatus" : null,
			"description" : null,
			"moreInfoURL" : null
	};
	
	this.setDanger = function(data){
		error.type = "danger";
		this.setErrorWithoutType(data);
	}

	this.setInfo = function(data){
		error.type = "info";
		this.setErrorWithoutType(data);
	}
	
	this.setWarning = function(data){
		error.type = "warning";
		this.setErrorWithoutType(data);
	}
	
	this.setSucess = function(data){
		error.type = "sucess";
		this.setErrorWithoutType(data);
	}	

	this.setErrorWithoutType = function(data){
		error.code = data.code;
		error.httpStatus = data.httpStatus;
		error.description = data.description;
		error.moreInfoURL = data.moreInfoURL;
		$rootScope.$broadcast(TAG_ERROR_UPDATE);
	}
	
	
	//********** GETTERS ************
	this.getError = function(){
		return error;
	}
	
	this.getErrorDescription = function(){
		return error.description;
	}
	
	this.getErrorType = function(){
		return error.type;
	}

	
	
});