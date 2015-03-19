
mieventoControllers.controller("HomeController", ["$scope", "$state", "providerService", "eventService", "applicationContext",
		function($scope, $state, providerService, eventService, applicationContext) {
		
		providerService.getAllTypes(function(data){
			$scope.providerTypes = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		eventService.getAllCountries(function(data) {
			$scope.countries = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		eventService.getAllProvinces(function(data) {
			$scope.provinces = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		
		
		$scope.search = function(){
			var searchLocation = {
					countryCode : $scope.search.country,
					province : $scope.search.province
				}
			
			var searchLocationTypeRequest = {
					providerType : $scope.search.providerType,
					location : searchLocation
			}
			$state.go("providerListState", {"searchLocationTypeRequest" : searchLocationTypeRequest});
		}
} ]);

