mieventoControllers.controller("newEventController", [ "$rootScope", "$scope",
		"$routeParams", "userService",
		function($rootScope, $scope, $routeParams, userService) {
	
			$scope.create = function(){
				var event = $scope.event;
				$rootScope.logged_user.events.push(event);
				userService.update($rootScope.logged_user,function(){
					console.log("todo bien");
					$rootScope.selectedEvent = event;
				},function(){
					console.log("todo mal");
				})
			}
	
}]);

mieventoControllers.controller("guestsEventController",[ "$rootScope", "$scope",
          "$state", "userService",function($rootScope, $scope, $state, userService) {
	
		$scope.goAddPerson = function(){
			$state.go("eventState.newGuest");
		}
}]);


mieventoControllers.controller("newGuestEventController",[ "$rootScope", "$scope",
          "$routeParams", "userService",function($rootScope, $scope, $routeParams, userService) {
                                    
			$scope.create = function(){
				var person = $scope.person;
				$rootScope.logged_user.selectedEvent.guests.push(person);
				userService.update($rootScope.logged_user,function(){
					console.log("todo bien");
				},function(){
					console.log("todo mal");
				})
			}
	   
}]);
