

App.run(["$rootScope","$cookies","$location",function($rootScope,$cookies,$location){
	
	
	$rootScope.initialize = function(){
		if ($cookies.token) $rootScope.token = $cookies.token;
		if ($cookie.logged_user) $rootScope.logged_user = $cookies.logged_user;
	};
	
	$rootScope.login = function(token,email,remember){
		$rootScope.token = token;
		$rootScope.logged_user = email;
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
	
}]);