

mieventoControllers.controller("ProvidersEventController", ["$scope", "$state", "userService", "applicationContext",
                function($scope, $state, userService, applicationContext) {

		$scope.providersToCompare = applicationContext.getEventContext().getProvidersToCompareEvent();
		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
	    
	    
		$scope.goToDetailProvider = function(provider){
			applicationContext.getProviderContext().setDetailProvider(provider);
			$state.go("providerDetailState");
		}
		
		$scope.save = function(data) {
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
 } ]);

