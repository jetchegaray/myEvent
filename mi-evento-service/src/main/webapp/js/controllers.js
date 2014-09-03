var mieventoControllers = angular.module("mieventoControllers", []);

mieventoControllers.controller("loginController", [ "$rootScope", "$scope",
		"$routeParams", "userService",
		function($rootScope, $scope, $routeParams, userService) {
			$scope.login = function() {
				userService.login($scope.user, function(data) {
					alert(data);
					$rootScope.login(data, $scope.user.email, $scope.remember);
				}, function(data) {
					alert("todo mal");
				});
			}
		} ]);

mieventoControllers.controller("signUpController", [ "$scope", "$routeParams",
		"userService", function($scope, $routeParams, userService) {
			$scope.signUp = function() {
				userService.signUp($scope.user, function(data) {
					alert("resgitrado");
				}, function(data) {
					alert("todo mal");
				});
			}
		} ]);