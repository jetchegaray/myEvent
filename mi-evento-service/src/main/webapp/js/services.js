var mieventoServices = angular.module("mieventoServices",["ngResource"]);

mieventoServices.factory("userService",["$resource",function($resource){
		return $resource("/mievento/user/",{},
				{
					signUp : {method: "POST"},
					login : {method : "PUT"},
					logout: {method : "DELETE"}
				})
		}
]);