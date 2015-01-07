

mieventoControllers.controller("ProvidersEventController", ["$scope", "$state", "userService", "applicationContext",
                function($scope, $state, userService, applicationContext) {

		$scope.providersToCompare = applicationContext.getEventContext().getProvidersToCompareEvent();
	  
		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
	    
	    
		$scope.goToDetailProvider = function(provider){
			applicationContext.getProviderContext().setDetailProvider(provider);
			$state.go("providerDetailState");
		}
		
		$scope.save = function(data) {
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				$state.go("eventState.providers");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
 } ]);

