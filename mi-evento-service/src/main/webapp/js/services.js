var mieventoServices = angular.module("mieventoServices",["ngResource"]);

mieventoServices.factory("userService",["$resource",function($resource){
		return $resource("/mievento/user/:requestMapping",{},
				{
					signUp : {method: "POST", headers: {'Content-Type': 'application/json'}},
					login : {method : "PUT", headers: {'Content-Type': 'application/json'}},
					logout: {method : "DELETE"},
					forgottenPassword: {method : "PUT",params: {requestMapping : "email"}},
					update : {method : "POST", params : {requestMapping : "update"}, headers: {'Content-Type': 'application/json'}}
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
        });
       }                                     
]);


mieventoApp.provider('providerContextService', function() {
  
    this.providerType = null;
 
    this.$get = function() {
        return this.providerType;
    };
 
    this.setProviderType = function(type) {
        this.providerType = type;
    };
});


mieventoServices.factory('handlerErrorService',["$rootScope", function($rootScope) {
    var alertService = {};

    $rootScope.alert = null;

    alertService.addError = function(msg) {
      $rootScope.alert = ({"type": "danger", "msg": msg});
    };
    
    alertService.addWarning = function(msg) {
        $rootScope.alert = ({"type": "warning", "msg": msg});
     };
     
     alertService.addSuccess = function(msg) {
         $rootScope.alert = ({"type": "success", "msg": msg,"show": true});
     };

    alertService.closeAlert = function(index) {
      $rootScope.alert = null;
    };
    


    return alertService;
  }]);