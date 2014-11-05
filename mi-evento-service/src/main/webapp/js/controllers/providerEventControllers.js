

mieventoControllers.controller("providersEventController", ["$scope", "$state", "applicationContext",
                function($scope, $state, applicationContext) {

		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
		
		$scope.goToDetailProvider = function(provider){
			applicationContext.getProviderContext().setDetailProvider(provider);
			$state.go("providerDetailState");
		}
		
		$scope.goToEditProvider = function(provider){
			applicationContext.getEventContext().setEditProvider(provider);
			$state.go("eventState.providerEdit");
		}
	
 } ]);


mieventoControllers.controller("editProviderEventController", ["$scope", "$state", "userService", "applicationContext", 
 			function($scope, $state, userService, applicationContext) {
 			
 			$scope.person = applicationContext.getEventContext().getEditṔrovider();
 			
 			$scope.save = function() {
 				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
 					$state.go("eventState.providers");
 				}, function(error) {
 					applicationContext.getExceptionContext().setDanger(error.data);
 				});
 			}

 }]);

