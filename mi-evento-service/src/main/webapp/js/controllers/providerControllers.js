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


mieventoControllers.controller("providerDetailController",["$scope","applicationContext",function($scope, applicationContext){
		
		$scope.provider = applicationContext.getProviderContext().getDetailProvider();
		
}]);



mieventoControllers.controller("providerListController",["$scope", "$state", "$stateParams", 
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
						
							applicationContext.getEventContext().setPlaceSelectedEvent(provider);
							
							userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
//								applicationContext.getEventContext().setSelectedEvent(selectedEvent);
								$state.go("eventState.place");
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
							
							
								
						}else if (angular.equals(state.name, "eventState.providers")){
							
							applicationContext.getEventContext().addProviderSelectedEvent(provider);
							
							userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
								$state.go("eventState.providers");
//								applicationContext.getEventContext().setSelectedEvent(selectedEvent);
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
						}
					}
				}
				
				$scope.goToDetail = function(provider){
					applicationContext.getProviderContext().setDetailProvider(provider);
					$state.go("providerDetailState");
				}
}]);


