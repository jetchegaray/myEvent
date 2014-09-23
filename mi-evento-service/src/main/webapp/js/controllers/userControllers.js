var mailPattern = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]+$/;

mieventoControllers.controller("loginController", [ "$rootScope", "$scope",
		"$routeParams", "userService",
		function($rootScope, $scope, $routeParams, userService) {
			
			$scope.mailPattern = mailPattern;

			$scope.login = function() {
				
				if ($scope.loginForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						$rootScope.addAlert("danger","El Mail ingresado debe ser de la forma example@mail.com",true);
					}
					return false;
				}	
				
				userService.login($scope.user, function(data) {
					$rootScope.login(data, $scope.user.email, $scope.remember);
				}, function(error) {
					alert("todo mal"+error);
				});
			}
			
			$scope.forgottenPassword = function(user){
				
				if (user == null || user.email == null){
					$rootScope.addAlert("danger","Debe ingresar un mail Valido.",true);
					return false;
				}
				
				userService.forgottenPassword($scope.user,function(data){
					$rootScope.addAlert("success","Revise tu Mail !! tu nueva password ya fue enviada... ",true);
					$location.path(LOGIN_PATH);
				},function(error){
					alert("no funka el mail");
				})
			};
			
		} ]);



mieventoControllers.controller("signUpController", ["$rootScope", "$scope", "$routeParams","$location",
		"userService", function($rootScope,$scope, $routeParams,$location, userService) {
			
			$scope.mailPattern = mailPattern;
	
			$scope.signUp = function() {
				
				if ($scope.siginUpForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						$rootScope.addAlert("danger","El Mail ingresado debe ser de la forma example@mail.com",true);
					}
					return false;
				}	
				
				userService.signUp($scope.user, function(data) {
					$location.path(LOGIN_PATH);
				}, function(data) {
					alert("todo mal");
				});
			}
		} ]);



