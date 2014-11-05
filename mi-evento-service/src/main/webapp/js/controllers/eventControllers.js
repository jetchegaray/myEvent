mieventoControllers.controller("newEventController", [ "$scope",
		"userService", "applicationContext",
		function($scope, userService, applicationContext) {

			$scope.create = function() {
				var event = $scope.event;
				applicationContext.getUserContext().addUserSelectedEvent(event);
				
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					applicationContext.getEventContext().setSelectedEvent(event);
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}

} ]);

