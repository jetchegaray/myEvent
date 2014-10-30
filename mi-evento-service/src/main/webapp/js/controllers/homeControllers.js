mieventoControllers.controller("homeController",["$scope", "$state", "applicationContext", 
                                                         function($scope, $state, applicationContext){
			
			$scope.loggedUser = applicationContext.getUserContext().getLoggedUser();
			$scope.events = applicationContext.getUserContext().getLoggedUserEvents();
		
			$scope.selectEvent = function(event){
				applicationContext.getEventContext().setSelectedEvent(event);
				$state.go("eventState");
			}
}]);

