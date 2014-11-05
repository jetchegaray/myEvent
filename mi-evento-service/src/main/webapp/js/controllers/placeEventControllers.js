
mieventoControllers.controller("detailPlaceEventController", [ "$scope","$state", "applicationContext", 
                                       function($scope, $state, applicationContext) {

		$scope.place = applicationContext.getEventContext().getPlaceSelectedEvent();
	
		$scope.goToListProvider = function(){
			$state.go("providerListState",{"providerType" : "fotografos"});
		}
		
		$scope.goMoreInfo = function(){
			$scope.showMoreInfo = true;
		}
		
} ]);



mieventoControllers.controller("placeEventController", [ "$scope","$state", "applicationContext", 
                                function($scope, $state, applicationContext) {
		
		var placeSelected = applicationContext.getEventContext().getPlaceSelectedEvent();
	
		if (placeSelected == null){
			$state.go("eventState.placeChoose");
		}else{
			$scope.place = placeSelected;
		}
		
		$scope.deletePlace = function(){
			applicationContext.getEventContext().deselectedEvent();
			$state.go("eventState.placeChoose");
		}
		
		$scope.goToDetail = function(){
			applicationContext.getProviderContext().setDetailProvider($scope.place);
			$state.go("providerDetailState");
		}

}]);

