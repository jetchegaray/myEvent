mieventoControllers.controller("ProviderTypeController",["$scope", "providerService", "applicationContext", 
                                                         function($scope, providerService,applicationContext){
			
			providerService.getAllTypes(function(data){
				$scope.types = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
					
}]);


mieventoControllers.controller("ProviderPlaceTypesController",["$scope", "providerService", "applicationContext",
                                                               function($scope, providerService,applicationContext){
			
			providerService.getPlaceTypes(function(data){
				$scope.placeTypes = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		
}]);


mieventoControllers.controller("ProviderSearchController",["$scope", "$state", "providerService", "applicationContext",
                                                               function($scope, $state, providerService,applicationContext){
			
		providerService.getAllTypes(function(data){
			$scope.types = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
	
		$scope.search = function(providerType){
			var locationOwn = applicationContext.getEventContext().getPlaceSelectedEvent();
			var providers = applicationContext.getEventContext().getProvidersSelectedEvent();
			
			var placeProvider = _.filter(function(provider){ return provider.providerType.indexOf("Salon") > -1}); 
			
			var locationDefinitive = (locationOwn != null) ? locationOwn : placeProvider.location;
			
			var searchLocationTypeRequest = {
					providerType : providerType,
					location : locationDefinitive
			}
			$state.go("providerListState", {"searchLocationTypeRequest" : searchLocationTypeRequest});
		}
		
}]);


mieventoControllers.controller("ProviderDetailController",["$scope","applicationContext",function($scope, applicationContext){
		
		$scope.provider = applicationContext.getProviderContext().getDetailProvider();
		$scope.user = applicationContext.getUserContext().getLoggedUser();

		$scope.getStars = function(review){
			return 	_.range(0,review.rating);
		}
		
		$scope.getEmptyStars = function(review){
			return 	_.range(review.rating,5);
		}
}]);



mieventoControllers.controller("ProviderListController",["$rootScope", "$scope", "$state", "$stateParams", 
         "providerService", "userService", "applicationContext", 
         function( $rootScope, $scope, $state, $stateParams, providerService, userService, applicationContext){
				$scope.searching = true;
				
				providerService.getByLocationAndType($stateParams.searchLocationTypeRequest,function(data){
					$scope.searching =false;
					$scope.providers = data;
					
				}, function(error) {
					applicationContext.getExceptionContext().setDanger(error.data);
				});
				
				
				$scope.addProvider = function(provider){
					var logged_user = applicationContext.getUserContext().getLoggedUser();
					
					if (logged_user == null){
						$state.go("loginState");
					}else{
						
						var selectedEvent = applicationContext.getEventContext().getSelectedEvent();
						
						if (selectedEvent == null){
							$state.go("eventState.events");
						}
						state = applicationContext.getPreviousState();
						
						if (angular.equals(state.name, "eventState.place")){
						
							applicationContext.getEventContext().setEventLocationSelectedEvent(provider);
							
							userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
								info = {code : 0003,description : "El Lugar fue agregado con exito !"};
								applicationContext.getExceptionContext().setSuccess(info);
							
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
							
								
						}else if (angular.equals(state.name, "eventState.providers")){
							
							var error = applicationContext.getEventContext().addProviderSelectedEvent(provider);
							
							if (error != null){
								error.description = "El proveedor ya existe para el evento !";
								applicationContext.getExceptionContext().setWarning(error);
								return;
							}
							
							var user = applicationContext.getUserContext().getLoggedUser();
							
							userService.update(user, function() {
								applicationContext.getUserContext().setLoggedUser(user);
								//actualizo la vista del budget 
								$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
								
								info = {code : 0004,description : "El proveedor fue agregado con exito !"};
								applicationContext.getExceptionContext().setSuccess(info);
								
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
						}else if (angular.equals(state.name, "eventState.budget")){
							
							var errorProvComp = null;
							var errorProv = applicationContext.getEventContext().isExistsInProvider(provider);
							
							if (errorProv == null){ // si no hay error
								errorProvComp = applicationContext.getEventContext().addProviderToCompareEvent(provider);
							}
							var info = (errorProvComp != null) ? errorProvComp : errorProv;
							
							if (info != null){
								applicationContext.getExceptionContext().setInfo(info);
							}else{
								error = {code : 0005,description : "El proveedor para comparar fue agregado con exito !"};
								applicationContext.getExceptionContext().setSuccess(error);
							}
						}
				
//						$state.go(state.name);
					}
				}
				
				$scope.goToDetail = function(provider){
					applicationContext.getProviderContext().setDetailProvider(provider);
					$state.go("providerDetailState");
				}
				
				
				$scope.getAverageStars = function(provider){
					 if (provider.reviews.length === 0){
						 return _.range(0,0);
					 }
					 var rating = getRatingReview(provider.reviews);
					 return _.range(0, rating);
				}
				
				$scope.getAverageEmptyStars = function(provider){
					if (provider.reviews.length === 0){
						 return _.range(0,5);
					 }
					 var rating = getRatingReview(provider.reviews);
					 return _.range(rating, 5);
				}
				
				getRatingReview = function(reviews){
					var sumRating =  _.chain(reviews).map(function(review){ return review.rating;}).reduce(function(memo, rating){ return memo + rating; },0).value();
					return Math.round(sumRating / reviews.length);
				}
				
}]);


