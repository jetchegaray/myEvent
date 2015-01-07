mieventoControllers.controller("ProviderTypeController",["$scope", "providerService", function($scope, providerService){
			
			providerService.getAllTypes(function(data){
				$scope.types = data;
			});
					
}]);


mieventoControllers.controller("ProviderPlaceTypesController",["$scope", "providerService",function($scope, providerService){
			
			providerService.getPlaceTypes(function(data){
				$scope.placeTypes = data;
			});
		
}]);


mieventoControllers.controller("ProviderDetailController",["$scope","applicationContext",function($scope, applicationContext){
		
		$scope.provider = applicationContext.getProviderContext().getDetailProvider();
		
}]);



mieventoControllers.controller("ProviderListController",["$scope", "$state", "$stateParams", 
         "providerService", "userService", "applicationContext", 
         function( $scope, $state, $stateParams, providerService, userService, applicationContext){
	
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
							$state.go("eventState.events");
						}
						state = applicationContext.getPreviousState();
						
						if (angular.equals(state.name, "eventState.place")){
						
							applicationContext.getEventContext().setEventLocationSelectedEvent(provider);
							
							userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
								//forward state
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
							
								
						}else if (angular.equals(state.name, "eventState.providers")){
							
							applicationContext.getEventContext().addProviderSelectedEvent(provider);
							
							userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
								//forward state
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
						}else if (angular.equals(state.name, "eventState.budget")){
							
							applicationContext.getEventContext().addProviderToCompareEvent(provider);
						}
				
						$state.go(state.name);
					}
				}
				
				$scope.goToDetail = function(provider){
					applicationContext.getProviderContext().setDetailProvider(provider);
					$state.go("providerDetailState");
				}
}]);


