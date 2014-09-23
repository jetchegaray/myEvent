var mieventoControllers = angular.module("mieventoControllers", []);
var mailPattern = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]+$/;

mieventoControllers.controller("loginController", [ "$rootScope", "$scope",
		"$routeParams", "userService",
		function($rootScope, $scope, $routeParams, userService) {
			
			$scope.mailPattern = mailPattern;

			$scope.login = function() {
				
				if (!validateMail($rootScope,$scope)){
					return false;
				}
				
				userService.login($scope.user, function(data) {
					$rootScope.login(data, $scope.user.email, $scope.remember);
				}, function(error) {
					alert("todo mal"+error);
				});
			}
			
			$scope.forgottenPassword = function(){
				userService.forgottenPassword($scope.user,function(data){
					$rootScope.addAlert("success"," Revise tu Mail !! tu nueva password ya fue enviada... ",true);
					$location.path(LOGIN_PATH);
				},function(error){
					alert("no funka el mail");
				})
			};
			
		} ]);



mieventoControllers.controller("signUpController", [ "$scope", "$routeParams","$location",
		"userService", function($scope, $routeParams,$location, userService) {
			
			$scope.mailPattern = mailPattern;
	
			$scope.signUp = function() {
				
				if (!validateMail($rootScope,$scope)){
					return false;
				}
				
				userService.signUp($scope.user, function(data) {
					$location.path(LOGIN_PATH);
				}, function(data) {
					alert("todo mal");
				});
			}
		} ]);


validateMail = function($rootScope,$scope){
	
	if ($scope.loginForm.$invalid){
		if ($scope.user.email != ""){
			$scope.errorMailClass = "has-error";
			$rootScope.addAlert("danger","El Mail ingresado debe ser de la forma example@mail.com",true);
		}
		return false;
	}
	return true;
}


mieventoControllers.controller("providerTypeController",["$rootScope","$scope","$routeParams","$location",
         "providerService",function($rootScope,$scope,$routeParams,$location,providerService){
			providerService.getAllTypes(function(data){
				$scope.types = data;
			});
			
			$scope.toProviderByType = function(type){
				$rootScope.providerType = type;
				$location.path(PROVIDER_PATH);
			};
}]);

mieventoControllers.controller("providerListController",["$rootScope","$scope","$routeParams","$location",
         "providerService",function($rootScope,$scope,$routeParams,$location,providerService){
				providerService.getByType({ pathParams: $rootScope.providerType },function(data){
					$scope.providers = data;
					console.log(data);
				},function(error){
					alert("No hay providers");
				})
}]);

mieventoControllers.controller("carouselCtrl",["$scope",function($scope) {
	  $scope.interval = 5000;
	  var slides = $scope.slides = [];
	  for (var i=1; i<=5; i++) {
		    slides.push({image : "../img/carusel_"+i+".jpg",
		    text : "Cualquier Clase de Eventos Sociales"});
	  }
}]);