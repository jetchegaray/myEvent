var mailPattern = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]+$/;

mieventoControllers.controller("LoginController", [ "$rootScope", "$scope",
		"$state", "userService", "applicationContext", 
		function($rootScope, $scope, $state, userService, applicationContext) {
			
			$scope.mailPattern = mailPattern;
			$scope.login = function() {
				if ($scope.loginForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						var error = { code : "0009" };
						applicationContext.getExceptionContext().setWarning(error);
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
					var error = { code : "0010" };
					applicationContext.getExceptionContext().setWarning(error);
					return false;
				}
				
				userService.forgottenPassword($scope.user,function(data){
					var info = { code : "0011"};
					applicationContext.getExceptionContext().setInfo(info);
					$state.go("loginState");
				},function(error){
					applicationContext.getExceptionContext().setDanger(error.data);
				})
			};
			
} ]);



mieventoControllers.controller("SignUpController", ["$scope", "$state",
		"userService", "applicationContext", function($scope, $state, userService, applicationContext) {
			
			$scope.mailPattern = mailPattern;
	
			$scope.signUp = function() {
				
				if ($scope.siginUpForm.$invalid){
					if ($scope.user.email != ""){
						$scope.errorMailClass = "has-error";
						error = {code : "0009"};
						applicationContext.getExceptionContext().setWarning(error);
					}
					return false;
				}
				
				if (!angular.equals($scope.passwordRepeat , $scope.user.password)){
					
					$scope.errorPasswordClass = "has-error";
					var error = { code : "0012"};
					applicationContext.getExceptionContext().setWarning(error);
					return false;	
				}
				userService.signUp($scope.user, function(data) {
					$state.go("loginState");
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
		} ]);



