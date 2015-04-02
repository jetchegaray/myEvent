mieventoControllers.controller("ExceptionController", ["$scope", "$timeout", "applicationContext", function($scope, $timeout, applicationContext){
	
	$scope.$on(TAG_ERROR_UPDATE, function() {
		var exceptionContext = applicationContext.getExceptionContext();
		var description = exceptionContext.getErrorDescription();
		var type = exceptionContext.getErrorType();
		var code = exceptionContext.getErrorCode();
		$scope.alert = 
		   {
			   "code" : code,
		       "type": type,
		       "msg": description,
		       "show":true
		   };
		$timeout(function(){
			$scope.alert = null;
		}, 3000); // maybe '}, 3000, false);' to avoid calling apply

	 });
	
	$scope.closeAlert = function() {
		$scope.alert = null;
	};
	
	
	
}]);
		