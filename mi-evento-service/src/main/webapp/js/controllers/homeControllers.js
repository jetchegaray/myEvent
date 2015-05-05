
mieventoControllers.controller("HomeController", ["$scope", "$state", "providerService", "countryService","applicationContext",
		function($scope, $state, providerService, countryService,applicationContext) {
		
		providerService.getAllTypes(function(data){
			$scope.providerTypes = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
	
		$scope.search = function(){
			
			if ($scope.homeSearchForm.$invalid){
				return false;
			}
			
			var provinceName = ($scope.search.state != null) ? $scope.search.state.name : "";
			var searchLocation = {
					countryCode : $scope.search.country.code,
					province : provinceName
				}
			
			var searchLocationTypeRequest = {
					providerType : $scope.search.providerType,
					location : searchLocation
			}
			applicationContext.setSearchLocationTypeRequest(searchLocationTypeRequest);
			$state.go("providerListState");
		}
} ]);

