mieventoControllers.controller("providerTypeController",["$rootScope","$scope","$routeParams","$state",
         "providerService",function($rootScope,$scope,$routeParams,$state,providerService){
			providerService.getAllTypes(function(data){
				$scope.types = data;
			});
			
			$scope.toProviderByType = function(type){
				$rootScope.providerType = type;
				$state.go('providerState')
//				$location.path(PROVIDER_PATH);
			};
}]);

mieventoControllers.controller("providerListController",["$rootScope","$scope","$routeParams",
         "providerService",function($rootScope,$scope,$routeParams,providerService){
				providerService.getByType({ pathParams: $rootScope.providerType },function(data){
					$scope.providers = data;
					console.log(data);
				},function(error){
					console.log("No hay providers");
				})
}]);