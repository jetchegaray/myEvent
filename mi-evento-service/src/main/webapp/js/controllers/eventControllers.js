mieventoControllers.controller("newEventController", [ "$rootScope", "$scope",
		"userService", "applicationContext",
		function($rootScope, $scope, userService, applicationContext) {

			$scope.create = function() {
				var event = $scope.event;
				$rootScope.logged_user.events.push(event);
				userService.update($rootScope.logged_user, function() {
					$rootScope.selectedEvent = event;
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			}

} ]);


mieventoControllers.controller("guestsEventController", [ "$rootScope",
		"$scope", "$state", "$modal", "userService", "applicationContext",
		function($rootScope, $scope, $state, $modal, userService, applicationContext) {

			$scope.guests = $rootScope.selectedEvent.guests;
		
			$scope.goAddPerson = function() {
				$state.go("eventState.guestCreate");
			};

			$scope.goToEditPerson = function(person) {
				applicationContext.getEventContext().setEditPerson(person);
				$state.go("eventState.guestEdit");
			};

} ]);


mieventoControllers.controller("newGuestEventController", [ "$rootScope",
		"$scope", "$state", "userService", "applicationContext", function($rootScope, $scope, $state, userService, applicationContext) {
			
			$scope.save = function() {
				$rootScope.selectedEvent.guests.push($scope.person);

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


mieventoControllers.controller("placeEventController", [ "$rootScope", "$scope","$state",
	function($rootScope, $scope, $state) {

		$scope.goToListProvider = function(){
			$state.go("providerListState",{"providerType" : "fotografos"});
		}
		
		$scope.goMoreInfo = function(){
			$scope.showMoreInfo = true;
		}
		
} ]);


mieventoControllers.controller("providerEventController", [ "$rootScope", "$scope","$state",
                function($rootScope, $scope,$state) {

				
 		
 } ]);

