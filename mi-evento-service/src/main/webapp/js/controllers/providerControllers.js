mieventoControllers.controller("providerTypeController",["$scope", "providerService", function($scope, providerService){
			
			providerService.getAllTypes(function(data){
				$scope.types = data;
			});
		
}]);


mieventoControllers.controller("providerPlaceTypesController",["$scope", "providerService",function($scope, providerService){
			
			providerService.getPlaceTypes(function(data){
				$scope.placeTypes = data;
			});
		
}]);

mieventoControllers.controller("providerListController",["$rootScope", "$scope", "$state", "$stateParams", 
         "providerService", "applicationContext", 
         function($rootScope, $scope, $state, $stateParams, providerService, applicationContext){
	
				providerService.getByType({ pathParams: $stateParams.providerType },function(data){
					$scope.providers = data;
				},function(error){
					applicationContext.getExceptionContext().setDanger(error.data);
				})
				
				
				$scope.addProvider = function(provider){
					if ($rootScope.logged_user == null){
						$state.go("loginState");
					}else{
						if ($rootScope.selectedEvent == null){
							$state.go("eventState");
						}
						state = applicationContext.getPreviousState();
						console.log(state);
						if (angular.equals(state.name, "eventState.place")){
							$rootScope.selectedEvent.place = provider;
							console.log(angular.toJson($rootScope.selectedEvent));
						}else if (angular.equals(state.name, "eventState.providers")){
							$rootScope.selectedEvent.providers.push(provider); 
							$state.go("eventState.place");
						}
					}
				}
				
				$scope.goToDetail = function(provider){
					applicationContext.getProviderContext().setDetailProvider(provider);
					$state.go("providerDetailState");
				}
}]);


mieventoControllers.controller("providerDetailController",["$scope","applicationContext",
                               function($scope, applicationContext){
		
		$scope.provider = applicationContext.getProviderContext().getDetailProvider();
		
}]);
