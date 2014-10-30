mieventoControllers.controller("providerTypeController",["$scope", "providerService", 
                                                         function($scope, providerService){
			
			providerService.getAllTypes(function(data){
				$scope.types = data;
			});
			
		
}]);


mieventoControllers.controller("providerPlaceTypesController",["$scope", "providerService",
                                                               function($scope, providerService){
			
			providerService.getPlaceTypes(function(data){
				$scope.placeTypes = data;
			});
			
			
		
}]);

mieventoControllers.controller("providerListController",["$scope", "$state", "$stateParams", 
         "providerService", "applicationContext", 
         function( $scope, $state, $stateParams, providerService, applicationContext){
	
				providerService.getByType({ pathParams: $stateParams.providerType },function(data){
					$scope.providers = data;
				},function(error){
					applicationContext.getExceptionContext().setDanger(error.data);
				})
				
				
				$scope.addProvider = function(provider){
					var logged_user = applicationContext.getUserContext().getLoggedUser();
					
					if (logged_user == null){
						$state.go("loginState");
					}else{
						
						var selectedEvent = applicationContext.getEventContext().getSelectedEvent();
						
						if (selectedEvent == null){
							$state.go("eventState");
						}
						state = applicationContext.getPreviousState();
						
						if (angular.equals(state.name, "eventState.choosePlace")){
						
							applicationContext.getEventContext().setPlaceSelectedEvent(provider);
							$state.go("eventState.place");
							//TODO save
						}else if (angular.equals(state.name, "eventState.providers")){
							
							applicationContext.getEventContext().addProviderSelectedEvent(provider);
							$state.go("eventState.providers");
							//TODO save
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
