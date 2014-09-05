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