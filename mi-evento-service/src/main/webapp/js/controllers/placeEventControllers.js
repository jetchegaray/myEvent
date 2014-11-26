
mieventoControllers.controller("detailPlaceEventController", [ "$scope","$state", "userService", "applicationContext", 
                                       function($scope, $state, userService, applicationContext) {

		$scope.place = applicationContext.getEventContext().getPlaceSelectedEvent();
		
		$scope.goMoreInfo = function(){
			$scope.showMoreInfo = true;
		}	
		
		//Just save if is a  place add by user, if user choose place, it's saved in the providerControllers
		$scope.save = function(){
			if ($scope.placeForm.$invalid){
				return;
			}
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				$state.go("eventState.place");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			})
		}
		
} ]);



mieventoControllers.controller("placeEventController", [ "$scope","$state", "applicationContext", 
                                function($scope, $state, applicationContext) {
		
		$scope.placeSelected = applicationContext.getEventContext().getPlaceSelectedEvent();

		$scope.deletePlace = function(){
			applicationContext.getEventContext().deselectedEvent();
			$state.go("eventState.placeChoose");
		}
		
		$scope.goToDetail = function(){
			applicationContext.getProviderContext().setDetailProvider($scope.placeSelected);
			$state.go("providerDetailState");
		}

		$scope.goToListProvider = function(){
			$state.go("providerListState",{"providerType" : "fotografos"});
		}
		
		$scope.goToAddLocation = function(){
			$state.go("eventState.placeChoose");
		}
}]);

