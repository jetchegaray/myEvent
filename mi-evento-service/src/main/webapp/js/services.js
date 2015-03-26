

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
        	pathParams : "@pathParams",
        },
        {
        	getAll : {method : "GET",params: {requestMapping: "all"},isArray:true},
        	getAllTypes : {method : "GET",params: {requestMapping: "types"},isArray:true},
        	getPlaceTypes : {method : "GET",params: {requestMapping: "placeTypes"},isArray:true},
        	getByType : {method : "GET",params: {requestMapping: "byType"},isArray:true},
        	getByLocationAndType : {method : "POST",params: {requestMapping: "byLocationAndType"},isArray:true},
        	getMoreCheaperByCategory : {method : "POST", params : {requestMapping : "moreCheaperByCategory"}, isArray :true},
        	updateReviews : {method : "POST", params : {requestMapping : "updateReviews"}, headers: {'Content-Type': 'application/json'}}
        });
       }                                     
]);



mieventoServices.factory("eventGuestService",["$resource", function($resource){
		return $resource("/mievento/guest/:requestMapping",{
			requestMapping : "@requestMapping"},
			{
				getAllStatusTypes : {method : "GET",params : { requestMapping : "statusTypes"}, isArray : true},
				sendInvitation : {method : "PUT", params : {requestMapping : "sendInvitation"}}
			});	
}])


mieventoServices.factory("eventService",["$resource", function($resource){
		return $resource("/mievento/event/:requestMapping",{requestMapping : "@requestMapping"},
			{
				getAllEventTypes : {method : "GET",params : { requestMapping : "eventTypes"}, isArray : true},
				getAllCountries: {method : "GET",params : { requestMapping : "countries"}, isArray:true},
				getAllProvinces: {method : "GET",params : { requestMapping : "provinces"}, isArray:true}
			});	
}])


mieventoServices.factory("countryService",["$resource", function($resource){
		return $resource("/mievento/country/:requestMapping",{requestMapping : "@requestMapping"},
			{
				getAll : {method : "GET",params : { requestMapping : "all"}, isArray : true},
			});	
}])


mieventoServices.factory('UrlLanguageStorage', ['$location', function($location) {
    return {
        set: function (name, value) {},
        get: function (name) {
            return $location.search()['lang']
        }
    };
}]);