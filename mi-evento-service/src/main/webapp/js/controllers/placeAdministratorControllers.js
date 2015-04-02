
//21 Tables = 30px T y 10px G
//8 tables = 80px T y 40px G 
mieventoControllers.controller("PlaceAdministratorController", ["$scope", "$state", "userService", "applicationContext",
                function($scope, $state, userService, applicationContext) {
			
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
		
		//INIT
		$scope.guests = angular.copy(applicationContext.getEventContext().getGuestsSelectedEvent());
		$scope.tables = applicationContext.getEventContext().getTablesPlaceSelectedEvent();
		
		
		if ($scope.tables == null){
			$scope.amountTables = 0;
		}else{
			$scope.amountTables = _.size($scope.tables);
			calculateSizePxTables();
			_.each($scope.tables,function(table, index){table.id = index; table.located = true; table.guestAmount = $scope.amountGuests.slice(0, _.size(table.guests));});
			var guestsSitting = _.chain($scope.tables).pluck("guests").flatten().filter(function(guest){return guest.firstName != null && guest.lastName != null}).value();
			$scope.guests = _.difference(_.pluck($scope.guests,"email"),_.pluck(guestsSitting,"email"));
		}
		
		
		//functions
		$scope.setAmountTables = function(){
			
			if ($scope.formModel.amountTables < 0){
				 error = {code : "0006"};
				 applicationContext.getExceptionContext().setInfo(error);
				 return;
			}else if ($scope.formModel.amountTables > 21){
				 error = {code : "0007"};
				 applicationContext.getExceptionContext().setInfo(error);
				 return;
			}
			$scope.amountTables = $scope.formModel.amountTables;

			calculateSizePxTables();
			
			for (i = 0; i < $scope.amountTables ;i++){
				if ($scope.tables[i] == null){
					$scope.tables.push({ id : i,located : false});
				}
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
							guests : guests
					};
				
					for (k = 0; k < $scope.formModel.guestAmountPerTable.number ;k++){
						 $scope.tables[i].guests[k] = {};
					}
					break; //solo creo una tabla.
				}
				if (i+1 == $scope.amountTables){
					 error = {code : "0008"};
					 applicationContext.getExceptionContext().setInfo(error);
				}
			}
			console.log(angular.toJson($scope.tables));
		}
		
		
		 $scope.startDragGuest = function(event, ui, guest) {
			    $scope.draggedGuest = guest;
		 };

		 $scope.dropGuestSitting = function(event, ui,table) {
		 };
		
		 $scope.startDragTable = function(event, ui, table) {
			 $scope.draggedTable = table;
		 };
		 
		 $scope.dropTable = function(event, ui,table) {
		 };
		 
		 $scope.dropDeleteTable = function(event, ui, table) {
			 $scope.guests = _.union($scope.guests,_.filter($scope.draggedTable.guests,function(guest){return guest.firstName != null && guest.lastName != null}));
			 
			 //reset table	
			 $scope.tables = _.filter($scope.tables, function(table){ return table.id != $scope.draggedTable.id});
			 $scope.tables = _.filter($scope.tables, function(table){ return !angular.isUndefined(table.id);});
			 console.log($scope.draggedTable.id);
			 $scope.tables.push({ id : $scope.draggedTable.id,located : false});
		 };
		 
		 
		 $scope.save = function(){
			 applicationContext.getEventContext().setTablesPlaceSelectedEvent($scope.tables);
			  
			 userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				 var success = {code : "2000"};
				applicationContext.getExceptionContext().setSuccess(success);
			 }, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			 });
		 }

 } ]);


