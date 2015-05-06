

mieventoControllers.controller("PlaceEventController", [ "$scope","$state", "userService", "applicationContext", 
                                function($scope, $state, userService, applicationContext) {
		
		$scope.placeSelected = applicationContext.getEventContext().getPlaceSelectedEvent();
		console.log(angular.toJson($scope.placeSelected));
		if ($scope.placeSelected == null  || angular.isUndefined($scope.placeSelected) || $scope.placeSelected == null){
			$state.go("eventState.myPlace");
		}
		
		if (!$scope.placeSelected.owner){
			info = {code : "0015"};
			applicationContext.getExceptionContext().setWarning(info);
			$state.go(applicationContext.getPreviousState());
		}else{
			$state.go("eventState.place");
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

		if ($scope.place != null && !$scope.place.owner){
			error = {code : "0015"};
			applicationContext.getExceptionContext().setWarning(error);
			return;
		}
		//IMPROVEMENT take from the children and set combos.
		$scope.countrySelected = "";
		$scope.stateSelected = "";
		$scope.citySelected = "";
		if ($scope.place!= null && $scope.place.location.countryCode != null){
			$scope.countrySelected = $scope.place.location.countryCode;
		}
		if ($scope.place!= null && $scope.place.location.province != null){
			$scope.stateSelected = $scope.place.location.province;
		}
		if ($scope.place!= null && $scope.place.location.city != null){
			$scope.citySelected = $scope.place.location.city;
		}
		
		$scope.search = {}; 
		

		$scope.save = function(){
			if ($scope.placeForm.$invalid){
				return;
			}
			$scope.place.location.countryCode = $scope.search.country.name;
			$scope.place.location.province = $scope.search.state.name;
			$scope.place.location.city = $scope.search.city.name;
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