

mieventoControllers.controller("ProvidersEventController", ["$rootScope","$scope", "$state", "userService", "applicationContext",
                function($rootScope, $scope, $state, userService, applicationContext) {

		$scope.providersToCompare = applicationContext.getEventContext().getProvidersToCompareEvent();
		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
		console.log(angular.toJson($scope.providers));
		
	    $scope.goToDetailProvider = function(provider){
			applicationContext.getProviderContext().setDetailProvider(provider);
			$state.go("providerDetailState");
		}
		
		$scope.save = function(data) {
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
				
				var success = {code : "2000"}
				applicationContext.getExceptionContext().setSuccess(success);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
 } ]);

