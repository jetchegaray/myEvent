var mieventoControllers = angular.module("mieventoControllers", []);

mieventoControllers.controller("loginController", [ "$rootScope", "$scope",
		"$routeParams", "userService",
		function($rootScope, $scope, $routeParams, userService) {
			$scope.login = function() {
				userService.login($scope.user, function(data) {
					$rootScope.login(data, $scope.user.email, $scope.remember);
				}, function(data) {
					alert("todo mal");
				});
			}
		} ]);

mieventoControllers.controller("signUpController", [ "$scope", "$routeParams","$location",
		"userService", function($scope, $routeParams,$location, userService) {
			$scope.signUp = function() {
				userService.signUp($scope.user, function(data) {
					$location.path(LOGIN_PATH);
				}, function(data) {
					alert("todo mal");
				});
			}
		} ]);


mieventoControllers.controller("providerController",["$scope","$routeParams","$location",
         "providerService",function($scope,$routeParams,$location,providerService){
			providerService.getAllTypes(function(data){
				$scope.types = data;
			});
			
			$scope.getProviderByType = function(type){
				providerService.getByType({ pathParams: type },function(providers){
					$scope.providers = providers
					$location.path(PROVIDER_PATH);
				})
			};
}]);


mieventoControllers.controller("carouselCtrl",["$scope",function($scope) {
	  $scope.interval = 5000;
	  var slides = $scope.slides = [];
	  for (var i=1; i<=5; i++) {
		    slides.push({image : "../img/carusel_"+i+".jpg",
		    text : "Cualquier Clase de Eventos Sociales"});
	  }
}]);