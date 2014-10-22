mieventoControllers.controller("newEventController", [ "$rootScope", "$scope",
		"userService", "applicationContext",
		function($rootScope, $scope, userService, applicationContext) {

			$scope.create = function() {
				var event = $scope.event;
				$rootScope.logged_user.events.push(event);
				userService.update($rootScope.logged_user, function() {
					applicationContext.getEventContext().setSelectedEvent(event);
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}

} ]);


mieventoControllers.controller("guestsEventController", ["$scope", "$state", "$modal", "userService", "applicationContext",
		function($scope, $state, $modal, userService, applicationContext) {

			$scope.guests = applicationContext.getEventContext().getGuestsSelectedEvent();
		
			$scope.goAddPerson = function() {
				$state.go("eventState.guestCreate");
			};

			$scope.goToEditPerson = function(person) {
				applicationContext.getEventContext().setEditPerson(person);
				$state.go("eventState.guestEdit");
			};

} ]);


mieventoControllers.controller("newGuestEventController", ["$rootScope", "$scope", "$state", "userService", "applicationContext", 
             function($rootScope, $scope, $state, userService, applicationContext) {
			
			$scope.save = function() {
				applicationContext.getEventContext().addGuestSelectedEvent($scope.person);

				userService.update($rootScope.logged_user, function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			};

} ]);


mieventoControllers.controller("editGuestEventController", [ "$rootScope",
		"$scope", "$state", "userService", "applicationContext", function($rootScope, $scope, $state, userService, applicationContext) {
			
			$scope.person = applicationContext.getEventContext().getEditPerson();
			
			$scope.save = function() {
				userService.update($rootScope.logged_user, function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}

}]);


mieventoControllers.controller("detailPlaceEventController", [ "$scope","$state", "applicationContext", function($scope, $state, applicationContext) {

		$scope.place = applicationContext.getEventContext().getPlaceSelectedEvent();
	
		$scope.goToListProvider = function(){
			$state.go("providerListState",{"providerType" : "fotografos"});
		}
		
		$scope.goMoreInfo = function(){
			$scope.showMoreInfo = true;
		}
		
} ]);



mieventoControllers.controller("placeEventController", [ "$scope","$state", "applicationContext", function($scope, $state, applicationContext) {
		
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


mieventoControllers.controller("providersEventController", ["$scope", "$state", "applicationContext",
                function($scope, $statem, applicationContext) {

		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
		
		$scope.goToDetailProvider = function(provider){
			applicationContext.getProviderContext().setDetailProvider(provider);
			$state.go("providerDetailState");
		}
		
		$scope.goToEditProvider = function(provider){
			applicationContext.getProviderContext().setEditProvider(provider);
			$state.go("eventState.providerEdit");
		}
	
 } ]);


mieventoControllers.controller("editProviderEventController", [ "$rootScope",
 			"$scope", "$state", "userService", "applicationContext", 
 			function($rootScope, $scope, $state, userService, applicationContext) {
 			
 			$scope.person = applicationContext.getEventContext().getEditṔrovider();
 			
 			$scope.save = function() {
 				userService.update($rootScope.logged_user, function() {
 					$state.go("eventState.providers");
 				}, function(error) {
 					applicationContext.getExceptionContext().setDanger(error.data);
 				});
 			}

 }]);

