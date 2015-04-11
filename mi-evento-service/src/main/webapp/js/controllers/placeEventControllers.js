

mieventoControllers.controller("PlaceEventController", [ "$scope","$state", "applicationContext", 
                                function($scope, $state, applicationContext) {
		
		$scope.placeSelected = applicationContext.getEventContext().getPlaceSelectedEvent();
		console.log(angular.toJson($scope.placeSelected));
		if ($scope.placeSelected == null){
			$state.go("eventState.myPlace");
		}

		$scope.deletePlace = function(){
			applicationContext.getEventContext().deletePlace();
			$state.go("eventState.myPlace");
		}
		
		$scope.goToDetail = function(){
			$state.go("eventState.myPlace");
		}

}]);


mieventoControllers.controller("MyPlaceEventController", [ "$scope", "$state" ,"eventService", "userService", "applicationContext", 
                               function($scope, $state, eventService, userService, applicationContext) {
	
		$scope.place = applicationContext.getEventContext().getPlaceSelectedEvent();
		
		if ($scope.place != null){
			error = {code : "0015"};
			applicationContext.getExceptionContext().setWarning(error);
			return
		}
		
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
		
		$scope.search = {};
		$scope.save = function(){
			if ($scope.placeForm.$invalid){
				return;
			}
			applicationContext.getEventContext().setPlaceSelectedEvent($scope.place);
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$state.go("eventState.place");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
	
	
}]);