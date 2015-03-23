

mieventoControllers.controller("PlaceAdministratorController", ["$scope", "$state", "userService", "applicationContext",
                function($scope, $state, userService, applicationContext) {
			
		$scope.guests = angular.copy(applicationContext.getEventContext().getGuestsSelectedEvent());
		
		var amountTables = 8;
		$scope.amountGuests = [{number : 1,text: "one"},{number : 2,text: "two"},{number : 3,text: "three"},{number : 4,text: "four"},
		                       {number : 5,text: "five"},{number : 6,text: "six"},{number : 7,text: "seven"},{number : 8,text: "eight"}];
		$scope.tables = [];
		
		$scope.formModel ={}
		
		for (i = 0; i < amountTables ;i++){
			$scope.tables.push({ located : false});
		}
		
		$scope.addTable = function(){
			var guestsInTable = $scope.amountGuests.slice(0, $scope.formModel.guestAmountPerTable.number);
			
			for (i = 0; i < amountTables ;i++){
				if ($scope.tables[i].located == false){
					
					$scope.tables[i] = {
							guestAmount : guestsInTable,
							located : true,
							guests : []
					};
				
					for (k = 0; k < guestsInTable ;k++){
						 $scope.tables[i].guests.push({});
					}
					break;
				}
				if (i+1 == amountTables){
					 error = {code : 0008,description : "No hay mas lugar para agregar mesas !"};
					 applicationContext.getExceptionContext().setInfo(error);
				}
			}
			
		
			console.log(angular.toJson($scope.tables))
		}
		
		 $scope.startCallback = function(event, ui, guest) {
			    console.log('You started draggin: ' + guest.firstName);
			    $scope.draggedGuest = guest;
			    console.log(angular.toJson(guest));
		 };

		 $scope.dropCallback = function(event, ui,table) {
			 console.log('You started dropping: '+currentTableIndex);
			 table.guests.push($scope.draggedGuest);
			 console.log(angular.toJson($scope.tables));
		 };
		
		 $scope.startDragTable = function(event, ui, guest, table) {
			    console.log('You started draggin: ' + guest.firstName);
			    
			    
			    
			    $scope.draggedGuest = table.guests.slice(1, $scope.draggedGuest);;
			    console.log(angular.toJson(guest));
		 };
		 
		 
		 
		 $scope.save = function(){
			 var place = applicationContext.getEventContext().getPlaceSelectedEvent();
			 place.controlContextPlace = $scope.tables;
			 applicationContext.getEventContext().setPlaceSelectedEvent(place);
			 
			 userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				 //nothing to do.
			 }, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			 });
		 }

 } ]);


