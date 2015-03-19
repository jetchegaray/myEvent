

mieventoControllers.controller("PlaceAdministratorController", ["$scope", "$state", "userService", "applicationContext",
                function($scope, $state, userService, applicationContext) {
			
		$scope.guests = applicationContext.getEventContext().getGuestsSelectedEvent();
		
		var amountTables = 8;
		$scope.amountGuests = [{number : 1,text: "one"},{number : 2,text: "two"},{number : 3,text: "three"},{number : 4,text: "four"},
		                       {number : 5,text: "five"},{number : 6,text: "six"},{number : 7,text: "seven"},{number : 8,text: "eight"}];
		$scope.tables = [];
		var currentTableIndex = 0;
		$scope.formModel ={}
		
		for (i = 0; i < amountTables ;i++){
			$scope.tables.push({ located : false});
		}
		
		$scope.addTable = function(){
			var guestsInTable = $scope.amountGuests.slice(0, $scope.formModel.guestAmountPerTable.number);
			$scope.tables[currentTableIndex++] = {
				guestAmount : guestsInTable,
				located : true,
				guests : []
			};
			for (i = 0; i < guestsInTable ;i++){
				 $scope.tables[currentTableIndex].guests.push({});
			}
			console.log(angular.toJson($scope.tables))
		}
		
		 $scope.startCallback = function(event, ui, guest) {
			    console.log('You started draggin: ' + guest.firstName);
			    $scope.draggedGuest = guest;
			    console.log(angular.toJson(guest));
		 };

		 $scope.dropCallback = function(event, ui) {
			 console.log('You started dropping: '+currentTableIndex);
			 $scope.tables[currentTableIndex].guests.push($scope.draggedGuest);
			 console.log(angular.toJson($scope.tables));
		 };

 } ]);


