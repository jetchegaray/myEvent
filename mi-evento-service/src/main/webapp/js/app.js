var mieventoApp = angular.module("mieventoApp", [ "ngRoute", "ngCookies",
		"ui.bootstrap", "ngAnimate", "ui.router", "checklist-model", "mieventoControllers", "mieventoServices", "mieventoContext"]);

var mieventoControllers = angular.module("mieventoControllers", []);
var mieventoContext = angular.module("mieventoContext",[]);
var mieventoServices = angular.module("mieventoServices",["ngResource"]);


mieventoApp.run([ "$rootScope", "$cookies", "$state", "userService", "applicationContext",
		function($rootScope, $cookies, $state, userService, applicationContext) {

		$rootScope.initialize = function() {
			if ($cookies.token){
				$rootScope.token = $cookies.token;
			}
			//FIXME horroorrr !!
			if ($rootScope.logged_user == null && $cookies.logged_email){
				var user = {"email" : $cookies.logged_email,"password" : $cookies.logged_pass}
				userService.login(user, function(data) {
					$rootScope.login(data);
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
			}
		};

		$rootScope.login = function(user) {

			$rootScope.token = user.idSession;
			$rootScope.logged_user = user;
			$cookies.token = $rootScope.token;
			$cookies.logged_email = $rootScope.logged_user.email;
			$cookies.logged_pass = $rootScope.logged_user.password;

			$state.go("eventState");
		};

		$rootScope.logout = function() {
			$rootScope.token = null;
			$rootScope.logged_user = null;

			delete $cookies.token;
			delete $cookies.logged_email;

		}

		$rootScope.selectEvent = function(event){
			applicationContext.getEventContext().setSelectedEvent(event);
			$state.go("eventState");
		}
	
		$rootScope.initialize();
		
		 $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
			 applicationContext.setPreviousState(from);
	     });
} ]);





