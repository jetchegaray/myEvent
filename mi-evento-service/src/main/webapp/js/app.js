//ngRoute if it doesnt use, promise and resolve object in response service.
var mieventoApp = angular.module("mieventoApp", [ "ngCookies", "ngRoute", "ui.bootstrap", "ngAnimate", "ui.router", "ui.select", 
                                                  "ui.calendar", "xeditable", "ngDragDrop", "pascalprecht.translate",
                                                  "mieventoControllers", "mieventoServices", "mieventoContext"]);

var mieventoControllers = angular.module("mieventoControllers", []);
var mieventoContext = angular.module("mieventoContext",[]);
var mieventoServices = angular.module("mieventoServices",["ngResource"]);


mieventoApp.config(function ($translateProvider) {
	$translateProvider.useUrlLoader('/mievento/messageBundle');
//    $translateProvider.useStorage('UrlLanguageStorage');
	$translateProvider.preferredLanguage('es');
	$translateProvider.fallbackLanguage('es');
});

mieventoApp.run([ "$rootScope", "$cookies", "$state", "editableOptions", "userService", "applicationContext",
		function($rootScope, $cookies, $state, editableOptions, userService, applicationContext) {
	
//	x-editable
	editableOptions.theme = 'bs3';
	
	if ($cookies.token){
		$rootScope.token = $cookies.token;
	}
	//FIXME horroorrr !!
	if ($rootScope.loggedUser == null && $cookies.logged_email){
		var user = {"email" : $cookies.logged_email,"password" : $cookies.logged_pass}
	
		userService.login(user, function(data) {
			$rootScope.login(data);
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
	}

	$rootScope.login = function(user) {

		$rootScope.token = user.idSession;
		$rootScope.loggedUser = user;
		$cookies.token = $rootScope.token;
		$cookies.logged_email = $rootScope.loggedUser.email;
		$cookies.logged_pass = $rootScope.loggedUser.password;
		applicationContext.getUserContext().setLoggedUser(user);
		
		$state.go("eventState.events");
	};

	$rootScope.logout = function() {
		applicationContext.getUserContext().setUnLoggedUser();
		applicationContext.getEventContext().deselectedEvent();
		$rootScope.token = null;
		$rootScope.loggedUser = null;
		

		delete $cookies.token;
		delete $cookies.logged_email;
		$state.go("homeState");
	}

	$rootScope.selectEvent = function(event){
		applicationContext.getEventContext().setSelectedEvent(event);
		$state.go("eventState.events");
	}

	
	 $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
		 applicationContext.setPreviousState(from);
		 //if go to any event page you first selectedEvent
		 if (to.name.search("eventState") != -1){
			 var loggedUser = applicationContext.getUserContext().getLoggedUser();
			 if (loggedUser == null){
				 $state.go('loginState');
				 error = {code : "0001"};
				 applicationContext.getExceptionContext().setInfo(error);
			 }else if (!angular.equals(to.name,"eventState.eventCreate") && !angular.equals(to.name,"eventState.eventEdit") 
					 && !angular.equals(to.name,"eventState.events")){
				 var eventSelected = applicationContext.getEventContext().getSelectedEvent();
				 if (eventSelected == null){
					 error = {code : "0002"};
					 $state.go('eventState.events');
					 applicationContext.getExceptionContext().setInfo(error);
				 }
			 }
		 }
     });
	

} ]);





