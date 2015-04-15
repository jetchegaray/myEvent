

mieventoControllers.controller("PlaceEventController", [ "$scope","$state", "applicationContext", 
                                function($scope, $state, applicationContext) {
		
		$scope.placeSelected = applicationContext.getEventContext().getPlaceSelectedEvent();
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
		console.log(angular.toJson($scope.place));
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
		
		$scope.search = {}; //no borrar no me reconoce el scope child.
		$scope.save = function(){
			if ($scope.placeForm.$invalid){
				return;
			}
			$scope.place.location.countryCode = $scope.search.country.name;
			$scope.place.location.province = $scope.search.state.name;
			$scope.place.location.city = $scope.search.city.name;
			applicationContext.getEventContext().setPlaceSelectedEvent($scope.place);
		
			var user = applicationContext.getUserContext().getLoggedUser();
			console.log(angular.toJson(user));
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$state.go("eventState.place");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
	
	
}]);