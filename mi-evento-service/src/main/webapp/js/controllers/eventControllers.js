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


mieventoControllers.controller("guestsEventController", ["$scope", "$state", "$modal", "eventGuestService", "applicationContext",
		function($scope, $state, $modal, eventGuestService, applicationContext) {

			$scope.guests = applicationContext.getEventContext().getGuestsSelectedEvent();
			
			$scope.goAddGuest = function() {
				$state.go("eventState.guestCreate");
			};

			$scope.goToEditGuest = function(guest) {
				applicationContext.getEventContext().setEditGuest(guest);
				$state.go("eventState.guestEdit");
			};
			
			$scope.sendEmailToGuest = function(emailGuest){
				var selectedEvent = applicationContext.getEventContext().getSelectedEvent();
				var loggedUser = applicationContext.getLoggedUser();
				
				eventGuestService.sendInvitation(selectedEvent, emailGuest, loggedUser.email,
				  function(data){
					
					var info = applicationContext.getExceptionContext().getInfo();
					info.description = "La Invitacion Fue enviada Correctamente";
					applicationContext.getExceptionContext().setInfo(info);
					
				}, function(error){
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
			

} ]);


mieventoControllers.controller("newGuestEventController", ["$scope", "$state", "userService", "eventGuestService", "applicationContext", 
             function($scope, $state, userService, eventGuestService, applicationContext) {
			
			//get all status type TODO podria ir en otro controller si lo necesito de otro lugar.
			eventGuestService.getAllStatusTypes(function(data) {
				$scope.statusTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
	
			$scope.save = function() {
				applicationContext.getEventContext().addGuestSelectedEvent($scope.guest);

				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			};

} ]);


mieventoControllers.controller("editGuestEventController", ["$scope", "$state", "userService", "applicationContext", 
                                function($scope, $state, userService, applicationContext) {
			
			$scope.guest = applicationContext.getEventContext().getEditGuest();
			
			$scope.save = function() {
				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					$state.go("eventState.guests");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
}]);






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


mieventoControllers.controller("providersEventController", ["$scope", "$state", "applicationContext",
                function($scope, $state, applicationContext) {

		$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
		
		$scope.goToDetailProvider = function(provider){
			applicationContext.getProviderContext().setDetailProvider(provider);
			$state.go("providerDetailState");
		}
		
		$scope.goToEditProvider = function(provider){
			applicationContext.getEventContext().setEditProvider(provider);
			$state.go("eventState.providerEdit");
		}
	
 } ]);


mieventoControllers.controller("editProviderEventController", ["$scope", "$state", "userService", "applicationContext", 
 			function($scope, $state, userService, applicationContext) {
 			
 			$scope.person = applicationContext.getEventContext().getEditṔrovider();
 			
 			$scope.save = function() {
 				userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
 					$state.go("eventState.providers");
 				}, function(error) {
 					applicationContext.getExceptionContext().setDanger(error.data);
 				});
 			}

 }]);

