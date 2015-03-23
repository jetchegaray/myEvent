
mieventoControllers.controller("HomeController", ["$scope", "$state", "providerService", "countryService","applicationContext",
		function($scope, $state, providerService, countryService,applicationContext) {
		
		providerService.getAllTypes(function(data){
			$scope.providerTypes = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		$scope.countries = applicationContext.getCountryContext().getAllCountries();
		if ($scope.countries == null){
			countryService.getAll(function(data) {
				$scope.countries = data;
				applicationContext.getCountryContext().setAllCountries(data);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});	
		}
		
		
		$scope.loadStates = function(){
			$scope.states = $scope.search.country.states;
		}
		
		$scope.search = function(){
			var searchLocation = {
					countryCode : $scope.search.country.code,
					province : $scope.search.province.name
				}
			
			var searchLocationTypeRequest = {
					providerType : $scope.search.providerType,
					location : searchLocation
			}
			$state.go("providerListState", {"searchLocationTypeRequest" : searchLocationTypeRequest});
		}
} ]);

