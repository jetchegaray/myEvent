
//21 Tables = 30px T y 10px G
//8 tables = 80px T y 40px G 
mieventoControllers.controller("PlaceAdministratorController", ["$scope", "$state", "userService", "applicationContext",
                function($scope, $state, userService, applicationContext) {
			
		$scope.amountTables = 0;
		$scope.guests = angular.copy(applicationContext.getEventContext().getGuestsSelectedEvent());
		
		calculateSizePxTables = function(){
			if ($scope.amountTables <= 8){
				$scope.selectCss = {
						droppableTable : "droppable-tableCien",
						placeAdminTableTaable : "taableCien"
				};
				$scope.amountGuests = [{number : 1,style: "one-lg"},{number : 2,style: "two-lg"},{number : 3,style: "three-lg"},{number : 4,style: "four-lg"},
				                       {number : 5,style: "five-lg"},{number : 6,style: "six-lg"},{number : 7,style: "seven-lg"},{number : 8,style: "eight-lg"}];
			}else if ($scope.amountTables <= 10){
				$scope.selectCss = {
						droppableTable : "droppable-tableOchentayCinco",
						placeAdminTableTaable : "taableOchentayCinco"
				};
				$scope.amountGuests = [{number : 1,style: "one-lg"},{number : 2,style: "two-lg"},{number : 3,style: "three-lg"},{number : 4,style: "four-lg"},
				                       {number : 5,style: "five-lg"},{number : 6,style: "six-lg"},{number : 7,style: "seven-lg"},{number : 8,style: "eight-lg"}];
			}else{
				$scope.selectCss = {
						droppableTable : "droppable-tableCincuenta",
						placeAdminTableTaable : "taableCincuenta"
				};
				$scope.amountGuests = [{number : 1,style: "one-sm"},{number : 2,style: "two-sm"},{number : 3,style: "three-sm"},{number : 4,style: "four-sm"},
				                       {number : 5,style: "five-sm"},{number : 6,style: "six-sm"},{number : 7,style: "seven-sm"},{number : 8,style: "eight-sm"}];
			}
		}
		
		$scope.setAmountTables = function(){
			
			if ($scope.formModel.amountTables < 0){
				 error = {code : 0009,description : "El numero de mesas debe ser mayor a 0 !"};
				 applicationContext.getExceptionContext().setInfo(error);
				 return;
			}else if ($scope.formModel.amountTables > 21){
				 error = {code : 0009,description : "El numero de mesas debe ser menor a 21 !"};
				 applicationContext.getExceptionContext().setInfo(error);
				 return;
			}
			$scope.amountTables = $scope.formModel.amountTables;

			calculateSizePxTables();
			

			$scope.tables = [];
			
			for (i = 0; i < $scope.amountTables ;i++){
				$scope.tables.push({ id : i,located : false});
			}
		}
	
		$scope.addTable = function(){
			var guestsInTable = $scope.amountGuests.slice(0, $scope.formModel.guestAmountPerTable.number);
			
			for (i = 0; i < $scope.amountTables ;i++){
				if ($scope.tables[i].located == false){
					
					$scope.tables[i] = {
							id : i,
							guestAmount : guestsInTable,
							located : true,
							guests : []
					};
				
					for (k = 0; k < guestsInTable ;k++){
						 $scope.tables[i].guests.push({});
					}
					break;
				}
				if (i+1 == $scope.amountTables){
					 error = {code : 0008,description : "No hay mas lugar para agregar mesas !"};
					 applicationContext.getExceptionContext().setInfo(error);
				}
			}
			console.log(angular.toJson($scope.tables));
		}
		
		 $scope.startDragGuest = function(event, ui, guest) {
			    $scope.draggedGuest = guest;
			    console.log(angular.toJson(guest));
		 };

		 $scope.dropGuestSitting = function(event, ui,table) {
			 console.log(angular.toJson(table));
		 };
		
		 $scope.startDragTable = function(event, ui, table) {
			 $scope.draggedTable = table;
		 };
		 
		 $scope.dropDeleteTable = function(event, ui, table) {
			 console.log($scope.draggedTable);
			 
			 $scope.guests.concat($scope.draggedTable.guests.slice());
			 
			 //reset table	
			 $scope.tables = _.filter($scope.tables, function(table){ return table.id != $scope.draggedTable.id});
			 $scope.tables = _.filter($scope.tables, function(table){ return !angular.isUndefined(table.id);});
			 
			 $scope.tables.push({ id : $scope.draggedTable.id,located : false});
			 console.log($scope.tables.length);
			 console.log(angular.toJson($scope.tables));
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


