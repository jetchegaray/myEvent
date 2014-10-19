mieventoControllers.controller("exceptionController", ["$scope", "applicationContext", function($scope, applicationContext){
	
	$scope.$on(TAG_ERROR_UPDATE, function() {
		var exceptionContext = applicationContext.getExceptionContext();
		var description = exceptionContext.getErrorDescription();
		var type = exceptionContext.getErrorType();
		$scope.alert = 
		   {
		       "type": type,
		       "msg": description,
		       "show":true
		   };
	 });
	
	$scope.closeAlert = function() {
		$scope.alert = null;
	};
	
}]);
		