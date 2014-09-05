BASE_PATH = "/";
LOGIN_PATH = "/user/login";
SIGNUP_PATH = "/user/signUp";

var mieventoApp = angular.module("mieventoApp",["ngRoute","ngCookies","mieventoControllers","mieventoServices"]);


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





mieventoApp.run([ "$rootScope", "$cookies","$location",function($rootScope, $cookies, $location){
	
	
	$rootScope.initialize = function(){
		if ($cookies.token) $rootScope.token = $cookies.token;
		if ($cookies.logged_user) $rootScope.logged_user = $cookies.logged_user;
	};
	
	$rootScope.login = function(token,email,remember){
		$rootScope.token = token;
		$rootScope.logged_user = email;
		alert($rootScope.logged_user);
		if (remember){
			$cookies.token = $rootScope.token;
			$cookies.logged_user = $rootScope.logged_user;
		}
		
		$location.path(BASE_PATH);
	};
	
	$rootScope.logout = function(){
		$rootScope.token = null;
		$rootScope.logged_user = null;
		
		delete $cookies.token;
		delete $cookies.logged_user;
		
		$location.path(BASE_PATH);
	}
	
	$rootScope.initialize();
}]);