mieventoControllers.controller("GuestsEventController", ["$scope", "$state", "$modal", "eventGuestService", "applicationContext",
		function($scope, $state, $modal, eventGuestService, applicationContext) {

			$scope.guests = applicationContext.getEventContext().getGuestsSelectedEvent();
			if ($scope.guests == null || $scope.guests.length == 0){
				$state.go("eventState.guestCreate");
			}
			
			$scope.goAddGuest = function() {
				$state.go("eventState.guestCreate");
			};

			$scope.goToEditGuest = function(guest) {
				applicationContext.getEventContext().setEditGuest(guest);
				$state.go("eventState.guestEdit");
			};
			
			$scope.sendEmailToGuest = function(guest){
				var selectedEvent = applicationContext.getEventContext().getSelectedEvent();
				var loggedUser = applicationContext.getUserContext().getLoggedUser();
				
				params = {
					event : selectedEvent,
					userEmail : loggedUser.email,
					guestEmail : guest.email
				}
				
				eventGuestService.sendInvitation(params,function(data){
					
					var info = applicationContext.getExceptionContext().getError();
					info.description = "La Invitacion fue enviada Correctamente";
					applicationContext.getExceptionContext().setInfo(info);
					
				}, function(error){
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
			

} ]);


mieventoControllers.controller("InvitationsGuestEventController", ["$scope", "eventGuestService", "applicationContext", function($scope, eventGuestService, applicationContext) {
			
			eventGuestService.getAllStatusTypes(function(data) {
				$scope.statusTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
	
} ]);
                                              			



mieventoControllers.controller("DetailGuestEventController", ["$scope", "$state", "userService", "eventGuestService", "applicationContext", 
             function($scope, $state, userService, eventGuestService, applicationContext) {
			
			//FIXME unificar en un solo lugar
			eventGuestService.getAllStatusTypes(function(data) {
				$scope.statusTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
			
			$scope.save = function() {
				applicationContext.getEventContext().addGuestSelectedEvent($scope.guest);
				
				if ($scope.guestForm.$invalid){
					return;
				}
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			};

} ]);


mieventoControllers.controller("EditGuestEventController", ["$scope", "$state", "userService", "eventGuestService", "applicationContext", 
                                function($scope, $state, userService, eventGuestService, applicationContext) {
			//FIXME unificar en un solo lugar
			eventGuestService.getAllStatusTypes(function(data) {
				$scope.statusTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
	
			$scope.guest = applicationContext.getEventContext().getEditGuest();
			
			$scope.save = function() {
			
				if ($scope.guestForm.$invalid){
					return;
				}
				
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
}]);