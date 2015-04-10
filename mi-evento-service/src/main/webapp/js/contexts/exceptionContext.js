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
		error.type = 'danger';
		this.setErrorWithoutType(data);
	}

	this.setInfo = function(data){
		error.type = 'info';
		this.setErrorWithoutType(data);
	}
	
	this.setWarning = function(data){
		error.type = 'warning';
		this.setErrorWithoutType(data);
	}
	
	this.setSuccess = function(data){
		error.type = 'success';
		this.setErrorWithoutType(data);
	}	

	this.setErrorWithoutType = function(data){
		
		if (angular.isUndefined(data) || data.code == null){
			error.code = 5000;
			error.httpStatus = 500;
			error.description = "Error en el servidor. Estamos solucionando el problema !";
			error.moreInfoURL = null;
			error.type = 'danger';
		}else{
			error.code = data.code;
			error.httpStatus = data.httpStatus;
			error.description = data.description;
			error.moreInfoURL = data.moreInfoURL;
		}
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
	
	this.getErrorCode = function(){
		return error.code;
	}

	
	
});