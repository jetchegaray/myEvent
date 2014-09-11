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


mieventoServices.factory("providerService",["$resource",function($resource){
        return $resource("/mievento/provider/:requestMapping/:pathParams",{
        	requestMapping: "@requestMapping",
        	pathParams: "@pathParams"
        },
        {
        	getAll : {method : "GET",params: {requestMapping: "all"},isArray:true},
        	getAllTypes : {method : "GET",params: {requestMapping: "types"},isArray:true},
        	getByType : {method : "GET",params: {requestMapping: "byType"},isArray:true} 	
        })
      }                                     
]);