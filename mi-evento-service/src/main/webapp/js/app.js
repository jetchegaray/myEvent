BASE_PATH = "/";
HOME_PATH = "/home"
LOGIN_PATH = "/user/login";
SIGNUP_PATH = "/user/signUp";
PROVIDER_PATH = "/providers";

var mieventoApp = angular.module("mieventoApp",["ngRoute","ngCookies","ui.bootstrap","mieventoControllers","mieventoServices"]);


mieventoApp.config(["$routeProvider",function($routeProvider){
	
	$routeProvider.when(HOME_PATH,{
						templateUrl: "../partials/home.html"
					}).when(LOGIN_PATH,{
						templateUrl: "../partials/login.html",
						controller:"loginController"  }
					).when(SIGNUP_PATH,{
						templateUrl: "../partials/signUp.html",
						controller:"signUpController"  }
					).when(PROVIDER_PATH,{
						templateUrl: "../partials/providers.html",
						controller: "providerController"}
					).when(BASE_PATH,{
						redirectTo : HOME_PATH
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