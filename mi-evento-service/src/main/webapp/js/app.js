var mieventoApp = angular.module("mieventoApp", [ "ngRoute", "ngCookies",
		"ui.bootstrap", "ngAnimate", "ui.router", "ui.select", "mieventoControllers", "mieventoServices", "mieventoContext"]);

var mieventoControllers = angular.module("mieventoControllers", []);
var mieventoContext = angular.module("mieventoContext",[]);
var mieventoServices = angular.module("mieventoServices",["ngResource"]);


mieventoApp.run([ "$rootScope", "$cookies", "$state", "userService", "applicationContext",
		function($rootScope, $cookies, $state, userService, applicationContext) {

		$rootScope.initialize = function() {
			if ($cookies.token){
				$rootScope.token = $cookies.token;
			}
			var logged_user = applicationContext.getUserContext().getLoggedUser();
			//FIXME horroorrr !!
			if (logged_user == null && $cookies.logged_email){
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
			//FIXME eliminar token del rootScope.
			$cookies.token = $rootScope.token;
			$cookies.logged_email = user.email;
			$cookies.logged_pass = user.password;
			applicationContext.getUserContext().setLoggedUser(user);
			
			$state.go("eventState");
		};

		$rootScope.logout = function() {
			$rootScope.token = null;
			applicationContext.getUserContext().setUnLoggedUser();

			delete $cookies.token;
			delete $cookies.logged_email;

		}

	
	
		 $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
			 applicationContext.setPreviousState(from);
	     });
		 
		 $rootScope.initialize();
} ]);





