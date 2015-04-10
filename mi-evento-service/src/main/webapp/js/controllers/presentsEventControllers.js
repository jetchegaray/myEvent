

mieventoControllers.controller("PresentsEventController", ["$scope", "$state", "applicationContext", "userService",
                function($scope, $state, applicationContext, userService) {
		
		$scope.presents = applicationContext.getEventContext().getPresents();
		if ($scope.presents.length == 0){
			$state.go("eventState.presentsCreate");
		}
		
		$scope.goToAddCreditPlace = function(){
			$state.go("eventState.presentsCreate");
		}
		
		$scope.save = function(data) {
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
		$scope.addDescription = function(description,present) {
			if (present.descriptions == null){
				present.descriptions = [];
			}
			present.descriptions.push(description);
			
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
 } ]);



mieventoControllers.controller("DetailPresentEventController", ["$scope", "$state", "applicationContext", "userService", "countryService", 
                function($scope, $state, applicationContext, userService, countryService) {
	
		$scope.search = {};
		$scope.save = function() {
			
			if ($scope.presentForm.$invalid){
				return;
			}
			
			var location = {
					placeName : $scope.present.locationCredit.placeName,
					countryCode :  $scope.search.country.name,
					province :  $scope.search.state.name,
					city :  $scope.search.city.name,
					streetAddress : $scope.present.locationCredit.streetAddress
			};
			
			$scope.present.locationCredit = location;
			console.log(angular.toJson($scope.present));
			applicationContext.getEventContext().addPresent($scope.present);
		
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$state.go("eventState.presents");
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
 } ]);
