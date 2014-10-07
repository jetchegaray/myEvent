var mieventoApp = angular.module("mieventoApp", [ "ngRoute", "ngCookies",
		"ui.bootstrap", "ngAnimate", "mgcrea.ngStrap", "ui.router", "mieventoControllers", "mieventoServices"]);

var mieventoControllers = angular.module("mieventoControllers", []);

mieventoApp.run([ "$rootScope", "$cookies", "$location","userService",
		function($rootScope, $cookies, $location,userService) {

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
						$rootScope.addAlert("danger","Error en el servidor.");
						console.log(error);
					});
				}
			};

			$rootScope.login = function(user) {

				$rootScope.token = user.idSession;
				$rootScope.logged_user = user;
				$cookies.token = $rootScope.token;
				$cookies.logged_email = $rootScope.logged_user.email;
				$cookies.logged_pass = $rootScope.logged_user.password;

				$location.path(EVENTS_PATH);
			};

			$rootScope.logout = function() {
				$rootScope.token = null;
				$rootScope.logged_user = null;

				delete $cookies.token;
				delete $cookies.logged_email;

				$location.path(BASE_PATH);
			}
			
			
			$rootScope.addAlert = function(alertType, alertMsg) {
			 var alert = 
	        {
	            "type": alertType,
	            "msg": alertMsg,
	            "show":true,
	        };
			 $rootScope.alert = alert;
		}
	
		$rootScope.closeAlert = function() {
			$rootScope.alert = null;
		};
		
		
		$rootScope.selectEvent = function(event){
			$rootScope.selectedEvent = event;
		}
	
		$rootScope.initialize();
} ]);



mieventoControllers.controller("carouselCtrl",["$scope",function($scope) {
	  $scope.interval = 5000;
	  var slides = $scope.slides = [];
	  for (var i=1; i<=5; i++) {
		    slides.push({image : "../img/carusel_"+i+".jpg",
		    text : "Cualquier Clase de Eventos Sociales"});
	  }
}]);

mieventoControllers.controller("datepickerController",["$scope",function($scope) {
	
		  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.open = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope.opened = true;
	  };
	  
	  $scope.dateOptions = { 
	    formatYear: 'yy',
	    showWeeks : "false"
	  };
	  $scope.minDate = new Date();
	  $scope.maxDate = "22-06-2020";
	  $scope.format="dd-MM-yyyy";	  
}]);