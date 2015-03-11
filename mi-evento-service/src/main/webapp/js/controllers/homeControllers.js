
mieventoControllers.controller("HomeController", ["$scope", "providerService", "eventService", "applicationContext",
		function($scope, providerService, eventService, applicationContext) {

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
} ]);

