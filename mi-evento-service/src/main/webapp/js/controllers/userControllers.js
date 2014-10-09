var mailPattern = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]+$/;

mieventoControllers.controller("loginController", [ "$rootScope", "$scope",
		"$state", "userService",
		function($rootScope, $scope, $state, userService) {
			
			$scope.mailPattern = mailPattern;

			$scope.login = function() {
				
				if ($scope.loginForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						$rootScope.addAlert("danger","El Mail ingresado debe ser de la forma example@mail.com");
					}
					return false;
				}	
				
				userService.login($scope.user, function(data) {
					$rootScope.login(data, $scope.remember);
				}, function(error) {
					$rootScope.addAlert("danger",error.data.description);
					console.log(JSON.stringify(error));
				});
			}
			
			$scope.forgottenPassword = function(user){
				
				if (user == null || user.email == null){
					$rootScope.addAlert("danger","Debe ingresar un mail Valido.",true);
					return false;
				}
				
				userService.forgottenPassword($scope.user,function(data){
					$rootScope.addAlert("success","Revise tu Mail !! tu nueva password ya fue enviada... ");
					$state.go("loginState");
				},function(error){
					console.log(error);
				})
			};
			
		} ]);



mieventoControllers.controller("signUpController", ["$rootScope", "$scope", "$state",
		"userService", function($rootScope,$scope, $state, userService) {
			
			$scope.mailPattern = mailPattern;
	
			$scope.signUp = function() {
				
				if ($scope.siginUpForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						$rootScope.addAlert("danger","El Mail ingresado debe ser de la forma example@mail.com");
					}
					return false;
				}
				//FIXME mall !!
				if (!angular.equals($scope.siginUpForm.passwordRepeat , $scope.siginUpForm.password)){
					$scope.errorPasswordClass = "has-error";
					$rootScope.addAlert("danger","Las Claves deben coincidir.");
					return false;	
				}
				
				userService.signUp($scope.user, function(data) {
					$state.go("loginState");
				}, function(error) {
					console.log(error);
				});
			}
		} ]);



