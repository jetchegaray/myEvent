
//mieventoControllers.controller("DetailPlaceEventController", [ "$scope","$state", "userService", "applicationContext", 
//                                       function($scope, $state, userService, applicationContext) {
//
//		$scope.place = applicationContext.getEventContext().getEventLocationSelectedEvent();
//		
//		$scope.goMoreInfo = function(){
//			$scope.showMoreInfo = true;
//		}	
//		
//		//Just save if is a  place add by user, if user choose place, it's saved in the providerControllers
//		$scope.save = function(){
//			if ($scope.placeForm.$invalid){
//				return;
//			}
//			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
//				$state.go("eventState.place");
//			}, function(error) {
//				applicationContext.getExceptionContext().setDanger(error.data);
//			});
//		}
//		
//} ]);



mieventoControllers.controller("PlaceEventController", [ "$scope","$state", "applicationContext", 
                                function($scope, $state, applicationContext) {
		
		$scope.placeSelected = applicationContext.getEventContext().getEventLocationSelectedEvent();
		console.log(angular.toJson($scope.placeSelected));
		
		if ($scope.placeSelected == null){
			$state.go("eventState.myPlace");
		}

		$scope.deletePlace = function(){
			applicationContext.getEventContext().deselectedEvent();
			$state.go("eventState.myPlace");
		}
		
		$scope.goToDetail = function(){
			$state.go("eventState.myPlace");
		}

}]);


mieventoControllers.controller("MyPlaceEventController", [ "$scope", "$state" ,"eventService", "userService", "applicationContext", 
                               function($scope, $state, eventService, userService, applicationContext) {
	
		$scope.place = applicationContext.getEventContext().getEventLocationSelectedEvent();
	
		eventService.getAllCountries(function(data) {
			$scope.countries = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		eventService.getAllProvinces(function(data) {
			$scope.provinces = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		
		$scope.save = function(){
			if ($scope.placeForm.$invalid){
				return;
			}
			applicationContext.getEventContext().setEventLocationSelectedEvent($scope.place);
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$state.go("eventState.place");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
	
	
}]);