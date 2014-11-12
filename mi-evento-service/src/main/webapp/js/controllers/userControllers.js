var mailPattern = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]+$/;

mieventoControllers.controller("loginController", [ "$rootScope", "$scope",
		"$state", "userService", "applicationContext", 
		function($rootScope, $scope, $state, userService, applicationContext) {
			
			$scope.mailPattern = mailPattern;
			$scope.login = function() {
				if ($scope.loginForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						var error = applicationContext.getExceptionContext().getError();
						error.description = "El Mail ingresado debe ser de la forma example@mail.com";
						applicationContext.getExceptionContext().setDanger(error);
					}
					return false;
				}
				userService.login($scope.user, function(data) {
					$rootScope.login(data);
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
			
			$scope.forgottenPassword = function(user){
				
				if (user == null || user.email == null){
					var error = applicationContext.getExceptionContext().getError();
					error.description = "Debe ingresar un mail Valido.";
					applicationContext.getExceptionContext().setDanger(error);
					return false;
				}
				
				userService.forgottenPassword($scope.user,function(data){
					info.description = "Revise tu Mail !! tu nueva password ya fue enviada... ";
					applicationContext.getExceptionContext().setInfo(info);
					$state.go("eventState.login");
				},function(error){
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			};
			
} ]);



mieventoControllers.controller("signUpController", ["$scope", "$state",
		"userService", "applicationContext", function($scope, $state, userService, applicationContext) {
			
			$scope.mailPattern = mailPattern;
	
			$scope.signUp = function() {
				
				if ($scope.siginUpForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						var error = applicationContext.getExceptionContext().getError();
						error.description = "El Mail ingresado debe ser de la forma example@mail.com";
						applicationContext.getExceptionContext().setDanger(error);
					}
					return false;
				}
				//FIXME mall !!
				if (!angular.equals($scope.siginUpForm.passwordRepeat , $scope.siginUpForm.password)){
					
					$scope.errorPasswordClass = "has-error";
					var error = applicationContext.getExceptionContext().getError();
					error.description = "Las Claves deben coincidir.";
					applicationContext.getEexceptionContext().setDanger(error);
					return false;	
				}
				
				userService.signUp($scope.user, function(data) {
					$state.go("eventState.login");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
		} ]);



