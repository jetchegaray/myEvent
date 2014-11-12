TAG_SELECTED_EVENT_UPDATE = "selectedEventUpdated"


mieventoControllers.controller("eventListController", ["$rootScope", "$scope", "$state", "applicationContext",
		function($rootScope, $scope, $state, applicationContext) {

			$scope.events = applicationContext.getUserContext().getLoggedUserEvents();
			$scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
				
			$scope.goAddEvent = function() {
				$state.go("eventState.eventCreate");
			};
		
			$scope.goToEditEvent = function(event) {
				applicationContext.getEventContext().setEditEvent(event);
				$state.go("eventState.eventEdit");
			};
			
			$scope.setSelectEvent = function(event){
				$scope.selectedEvent = event;
				applicationContext.getEventContext().setSelectedEvent(event);
				$rootScope.$broadcast(TAG_SELECTED_EVENT_UPDATE);
			}

} ]);


mieventoControllers.controller("newEventController", [ "$scope", "$state", "userService", "applicationContext",
		function($scope, $state, userService, applicationContext) {

			$scope.save = function() {
				
				if ($scope.eventForm.$invalid){
					return;
				}
				
				applicationContext.getUserContext().addUserEvent($scope.event);
					
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					applicationContext.getEventContext().setSelectedEvent(event);
					$state.go("eventState.events");
					
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}

} ]);



mieventoControllers.controller("editEventController", [ "$scope", "$state", "userService", "applicationContext",
	function($scope, $state, userService, applicationContext) {
		
		$scope.event = applicationContext.getEventContext().getEditEvent(event);
		
		$scope.save = function() {
			
			if ($scope.eventForm.$invalid){
				return;
			}
			
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				applicationContext.getEventContext().setSelectedEvent(event);
				$state.go("eventState.events");
   					
   				}, function(error) {
   					applicationContext.getExceptionContext().setDanger(error.data);
   				});
   		}

} ]);



mieventoControllers.controller("selectedEventController", [ "$scope", "applicationContext", function($scope, applicationContext) {
	
	 $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
	 //if doesnt selected, observer the event to change.
	 $scope.$on(TAG_SELECTED_EVENT_UPDATE, function() {
		 $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
     });
	
		
} ]);


