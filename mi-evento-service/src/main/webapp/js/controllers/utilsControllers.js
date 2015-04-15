
mieventoControllers.controller("providerCarouselCtrl", [ "$scope", function($scope) {
	$scope.init = function(provider)
	{
		// providers -> photos
		$scope.interval = 5000;
		$scope.slides = _.map(provider.photos, function(photo){ return { image : photo}; });
	}
	
}]);


/* Los watchers en ui-boostrap estan definidos sobre este nombre accordionCtrl => NO CAMBIAR*/
mieventoControllers.controller("accordionCtrl", ["$scope", function($scope){
	$scope.oneAtATime = true;
	$scope.isFirstOpen = true;
}]);


mieventoControllers.controller("DeleteConfirmationModalController", ["$rootScope", "$scope","$modal","userService","applicationContext",
		function($rootScope, $scope, $modal, userService, applicationContext) {

			$scope.openDeleteListConfirmation = function(list,element) {

				var modalInstance = $modal.open({
					templateUrl : 'deleteConfirmation.html',
					controller : "DeleteConfirmationInstanceController"
				});

				modalInstance.result.then(function() {
					
					var index = list.indexOf(element)
					list.splice(index, 1);
					userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
						// si elimina proveedores. actualizo el budget.
						$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
					}, function(error) {
						applicationContext.getExceptionContext().setDanger(error.data);
					})
				});
			};
			
			
			$scope.openDeleteElementConfirmation = function(element) {

				var modalInstance = $modal.open({
					templateUrl : 'deleteConfirmation.html',
					controller : "deleteConfirmationInstanceController"
				});

				modalInstance.result.then(function() {
					
					applicationContext.getEventContext().setSelectedEvent(null);
					
					userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					}, function(error) {
						applicationContext.getExceptionContext().setDanger(error.data);
					})
				});
			};

			
			$scope.openDeleteElementPlaceConfirmation = function(element) {

				var modalInstance = $modal.open({
					templateUrl : 'deleteConfirmation.html',
					controller : "deleteConfirmationInstanceController"
				});

				modalInstance.result.then(function() {
					
					applicationContext.getEventContext().deletePlace();
					
					userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
					}, function(error) {
						applicationContext.getExceptionContext().setDanger(error.data);
					})
				});
			};
}]);


mieventoControllers.controller("DeleteConfirmationInstanceController", [
		"$scope", "$modalInstance", function($scope, $modalInstance) {

			$scope.ok = function() {
				$modalInstance.close();
			}

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			}

} ]);



mieventoControllers.controller("DeleteConfirmationModalNotPersistenceController", ["$scope","$modal","userService","applicationContext",
 		function($scope, $modal, userService, applicationContext) {

 			$scope.openDeleteListConfirmation = function(list,element) {
 				
 				var modalInstance = $modal.open({
 					templateUrl : 'deleteConfirmation.html',
 					controller : "DeleteConfirmationInstanceController"
 				});

 				modalInstance.result.then(function() {
 					console.log("DeleteConfirmationModalNotPersistenceController");
 					var index = list.indexOf(element)
 					list.splice(index, 1);
 				});
 			};
 }]);



mieventoControllers.controller("datepickerController",["$scope",function($scope) {

	
		$scope.open = function($event) {
			$event.preventDefault();
			$event.stopPropagation();

			$scope.opened = true;
		};
		
		$scope.dateOptions = {
			'year-format': "'yy'",
			'starting-day': 1
		};
		
		$scope.minDate = Date.parse(new Date());
		$scope.maxDate = "22-06-2020";
		$scope.format = "dd-MM-yyyy";
		$scope.showWeeks = false;
			
		$scope.onDateChange = function(date) {
			console.log(date);
            if (date) {
              $scope.date =  moment(date);
              console.log("despues "+$scope.date);
            }
          };
					
}]);

