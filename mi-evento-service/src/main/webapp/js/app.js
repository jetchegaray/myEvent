var mieventoApp = angular.module("mieventoApp", [ "ngRoute", "ngCookies",
		"ui.bootstrap", "mieventoControllers", "mieventoServices" ]);

var mieventoControllers = angular.module("mieventoControllers", []);

mieventoApp.run([ "$rootScope", "$cookies", "$location",
		function($rootScope, $cookies, $location) {

			$rootScope.initialize = function() {
				if ($cookies.token)
					$rootScope.token = $cookies.token;
				if ($cookies.logged_user)
					$rootScope.logged_user = $cookies.logged_user;
			};

			$rootScope.login = function(token, email, remember) {

				$rootScope.token = token;
				$rootScope.logged_user = email;
				alert($rootScope.logged_user);
				if (remember) {
					$cookies.token = $rootScope.token;
					$cookies.logged_user = $rootScope.logged_user;
				}

				$location.path(BASE_PATH);
			};

			$rootScope.logout = function() {
				$rootScope.token = null;
				$rootScope.logged_user = null;

				delete $cookies.token;
				delete $cookies.logged_user;

				$location.path(BASE_PATH);
			}
			
			
			$rootScope.addAlert = function(alertType, alertMsg, alertShow) {
			 var alert = 
	        {
	            "type": alertType,
	            "msg": alertMsg,
	            "show":alertShow,
	        };
			 $rootScope.alert = alert;
		}
	
		$rootScope.closeAlert = function() {
			$rootScope.alert = null;
		};
		
		$rootScope.autohide =function(){
	        $timeout(function() {
	        	$rootScope.alert = null;
	        }, 1000);
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