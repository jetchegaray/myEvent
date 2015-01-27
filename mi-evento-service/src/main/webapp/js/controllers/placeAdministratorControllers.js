

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
		
		$scope.startCallback = function(event, ui, title) {
		    console.log('You started draggin: ' + title.firstName);
		    $scope.draggedTitle = title.firstName;
		};
		
		$scope.addTable = function(){
			console.log($scope.formModel.guestAmountPerTable);
			var guestsInTable = $scope.amountGuests.slice(0, $scope.formModel.guestAmountPerTable.number);
			console.log(guestsInTable);
			$scope.tables[currentTableIndex++] = {
				guestAmount : guestsInTable,
				located : true
			};
			console.log(angular.toJson($scope.tables));
		}
		
		
		getGuestByStrategySitting = function(amountText){
			if (amountText === "two"){
				
			}else if(amountText === "three"){
				
			}else if(amountText === "four"){
				
			}else if(amountText === "five"){
				
			}else if(amountText === "six"){
				
			}else if(amountText === "seven"){
				
			}else{
				
			}
		}
 } ]);


