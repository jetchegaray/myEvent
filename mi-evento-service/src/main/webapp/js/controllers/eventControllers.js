TAG_SELECTED_EVENT_UPDATE = "selectedEventUpdated"


mieventoControllers.controller("EventListController", ["$rootScope", "$scope", "$state", "applicationContext",
		function($rootScope, $scope, $state, applicationContext) {

			$scope.events = applicationContext.getUserContext().getLoggedUserEvents();
			$scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
			$scope.loggedUser = applicationContext.getUserContext().getLoggedUser();
				
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
			};

} ]);


mieventoControllers.controller("DetailEventController", [ "$scope", "$state", "userService", "eventService", "applicationContext",
		function($scope, $state, userService, eventService, applicationContext) {

			eventService.getAllEventTypes(function(data) {
				$scope.eventTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
	
			$scope.save = function() {
				if ($scope.eventForm.$invalid){
					return;
				}
				
				applicationContext.getUserContext().addUserEvent($scope.event);
					
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					applicationContext.getEventContext().setSelectedEvent($scope.event);
					$state.go("eventState.events");
					$rootScope.$broadcast(TAG_SELECTED_EVENT_UPDATE);
							
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			};

} ]);



mieventoControllers.controller("EditEventController", [ "$scope", "$state", "userService", "applicationContext",
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
   		};

} ]);



mieventoControllers.controller("SelectedEventController", [ "$scope", "applicationContext", function($scope, applicationContext) {
	
	 $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
	 //if doesnt selected, observer the event to change.
	 $scope.$on(TAG_SELECTED_EVENT_UPDATE, function() {
		 $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
     });
	
		
} ]);


