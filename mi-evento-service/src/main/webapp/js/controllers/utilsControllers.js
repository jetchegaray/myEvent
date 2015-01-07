/* Los watchers en ui-boostrap estan definidos sobre este nombre carouselCtrl => NO CAMBIAR*/
mieventoControllers.controller("CarouselCtrl", [ "$scope", function($scope) {
	$scope.interval = 5000;
	var slides = $scope.slides = [];
	for (var i = 1; i <= 5; i++) {
		slides.push({
			image : "../img/carusel_" + i + ".jpg",
			text : "Cualquier Clase de Eventos Sociales"
		});
	}
} ]);


angular.module('ui.bootstrap.setNgAnimate', ['ngAnimate'])
.directive('setNgAnimate', ['$animate', function ($animate) {
    return {
        link: function ($scope, $element, $attrs) { 
          
            $scope.$watch( function() { 
                    return $scope.$eval($attrs.setNgAnimate, $scope); 
                }, function(valnew, valold){
                    $animate.enabled(!!valnew, $element);
            });  
            
            
        }
    };
}]);


mieventoControllers.controller("providerCarouselCtrl", [ "$scope", function($scope) {
	
	// providers -> photos
	$scope.interval = 5000;
	var slides = $scope.slides = [];
	for (var i = 1; i <= 5; i++) {
		slides.push({
			image : "../img/carusel_" + i + ".jpg",
			text : "Cualquier Clase de Eventos Sociales"
		});
	}
} ]);


/* Los watchers en ui-boostrap estan definidos sobre este nombre accordionCtrl => NO CAMBIAR*/
mieventoControllers.controller("accordionCtrl", ["$scope", function($scope){
	$scope.oneAtATime = true;
	$scope.isFirstOpen = true;
}]);


mieventoControllers.controller("DeleteConfirmationModalController", ["$scope","$modal","userService","applicationContext",
		function($scope, $modal, userService, applicationContext) {

			$scope.openDeleteListConfirmation = function(list,element) {

				var modalInstance = $modal.open({
					templateUrl : 'deleteConfirmation.html',
					controller : "DeleteConfirmationInstanceController"
				});

				modalInstance.result.then(function() {
					
					var index = list.indexOf(element)
					list.splice(index, 1);
					userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
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
		
		$scope.minDate = new Date();
		$scope.maxDate = "22-06-2020";
		$scope.format = "dd-MM-yyyy";
		$scope.showWeeks = false;
					
}]);
