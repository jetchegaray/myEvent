

mieventoControllers.controller("BudgetEventController", ["$scope", "$state", "providerService", "applicationContext",
                function($scope, $state, providerService, applicationContext) {

		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
		$scope.providersToCompare = applicationContext().getEventContext().getProvidersToCompareEvent();
		
		
		
		
		$scope.goToEditProvider = function(provider){
			applicationContext.getEventContext().setEditProvider(provider);
			$state.go("eventState.providerEdit");
		}
		
		$scope.goToSearchAnotherProvider = function(provider){
			$state.go("providerListState",{"providerType" : provider.providerType});
		}
		
		
		$scope.goToMoreCheaper = function(provider){
			
			params = [];
			params.push(provider.providerType);
	
			providerService.getMoreCheaperByCategory(params, function(data){
				applicationContext().getSelectedEvent().setProvidersToCompareEvent(data);
				$state.go("providerListState",{"providerType" : provider.providerType});
			}, function(error){
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
 } ]);


