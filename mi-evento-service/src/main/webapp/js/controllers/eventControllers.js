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
				})
			}

} ]);


mieventoControllers.controller("guestsEventController", ["$scope", "$state", "$modal", "userService", "applicationContext",
		function($scope, $state, $modal, userService, applicationContext) {

			$scope.guests = applicationContext.getEventContext().getGuestsSelectedEvent();
			console.log(applicationContext.getEventContext().getSelectedEvent());
			console.log($scope.guests);
			$scope.goAddPerson = function() {
				$state.go("eventState.guestCreate");
			};

			$scope.goToEditPerson = function(person) {
				applicationContext.getEventContext().setEditPerson(person);
				$state.go("eventState.guestEdit");
			};

} ]);


mieventoControllers.controller("newGuestEventController", ["$scope", "$state", "userService", "applicationContext", 
             function( $scope, $state, userService, applicationContext) {
			
			$scope.save = function() {
				applicationContext.getEventContext().addGuestSelectedEvent($scope.person).guests.push($scope.person);

				userService.update($rootScope.logged_user, function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			}

} ]);


mieventoControllers.controller("editGuestEventController", [ "$rootScope",
		"$scope", "$state", "userService", "applicationContext", function($rootScope, $scope, $state, userService, applicationContext) {
			
			$scope.person = eventContext.getEditPerson();
			
			$scope.save = function() {
				userService.update($rootScope.logged_user, function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			}

} ]);


mieventoControllers.controller("choosePlaceEventController", [ "$scope","$state", "applicationContext", function($scope, $state, applicationContext) {

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
			$state.go("eventState.choosePlace");
		}else{
			$scope.place = placeSelected;
		}
		
		$scope.deletePlace = function(){
			applicationContext.getEventContext().deselectedEvent();
			$state.go("eventState.choosePlace");
		}
		
		$scope.goToDetail = function(){
			applicationContext.getProviderContext().setDetailProvider($scope.place);
			$state.go("providerDetailState");
		}

}]);


mieventoControllers.controller("providerEventController", [ "$rootScope", "$scope","$state",
                function($rootScope, $scope,$state) {

				
 		
 } ]);

