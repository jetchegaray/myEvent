BASE_PATH = "/";
HOME_PATH = "/home"
LOGIN_PATH = "/user/login";
SIGNUP_PATH = "/user/signUp";
PROVIDER_PATH = "/providers";

mieventoApp.config([ "$routeProvider", function($routeProvider) {

	$routeProvider.when(HOME_PATH, {
		templateUrl : "../partials/home.html"
	}).when(LOGIN_PATH, {
		templateUrl : "../partials/login.html",
		controller : "loginController"
	}).when(SIGNUP_PATH, {
		templateUrl : "../partials/signUp.html",
		controller : "signUpController"
	}).when(PROVIDER_PATH, {
		templateUrl : "../partials/providers.html",
		controller : "providerListController"
	}).when(BASE_PATH, {
		redirectTo : HOME_PATH
	}).otherwise({
		redirectTo : BASE_PATH
	});

} ]);


