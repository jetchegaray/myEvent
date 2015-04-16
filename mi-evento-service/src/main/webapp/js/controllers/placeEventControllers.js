

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
	
		if ($scope.place != null){
			error = {code : "0015"};
			applicationContext.getExceptionContext().setWarning(error);
			return
		}
		//IMPROVEMENT take from the children and set combos.
		$scope.getCountrySelected = ""
		$scope.getStateSelected = ""
		$scope.getCitySelected = ""
		if ($scope.place!= null && $scope.place.location.countryCode != null){
			$scope.getCountrySelected = $scope.place.location.countryCode;
		}
		if ($scope.place!= null && $scope.place.location.province != null){
			$scope.getStateSelected = $scope.place.location.province;
		}
		if ($scope.place!= null && $scope.place.location.city != null){
			$scope.getCitySelected = $scope.place.location.city;
		}
		
		
		$scope.search = {
				country : $scope.getCountrySelected,
				state : $scope.getStateSelected,
				city : $scope.getCitySelected
				
		}; //no borrar no me reconoce el scope child.
		
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