BASE_PATH = "/";
LOGIN_PATH = "/user/login";
SIGNUP_PATH = "/user/signUp";

var mieventoApp = angular.module("mieventoApp",["ngRoute","mieventoControllers","mieventoServices"]);



mieventoApp.config(["$routeProvider",function($routeProvider){
	
	$routeProvider.when(LOGIN_PATH,{
						templateUrl: "../partials/login.html",
						controller:"loginController"  }
					).when(SIGNUP_PATH,{
						templateUrl: "../partials/signUp.html",
						controller:"signUpController"  }
					).when(BASE_PATH,{
						redirectTo : BASE_PATH
					}).otherwise({
						redirectTo : BASE_PATH
					});
	
}]);