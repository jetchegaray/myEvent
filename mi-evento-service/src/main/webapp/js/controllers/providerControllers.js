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


mieventoControllers.controller("ProviderSearchController",["$scope", "$state", "countryService","providerService", "applicationContext",
                                                               function($scope, $state, countryService, providerService,applicationContext){
			
		providerService.getAllTypes(function(data){
			$scope.types = data;
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});
		
		$scope.search = function(providerType){
			var locationOwn = applicationContext.getEventContext().getPlaceSelectedEvent();
			var providers = applicationContext.getEventContext().getProvidersSelectedEvent();
			
			var placeProvider = _.filter(providers, function(provider){ return provider.providerType.indexOf("Salon") > -1}); 

			var locationDefinitive = null;
			if (locationOwn != null){
				locationDefinitive = locationOwn;
			}else if (placeProvider != null && !angular.isUndefined(placeProvider)) {
				locationDefinitive = placeProvider[0].location;
			}
			
			var searchLocationTypeRequest = {
					providerType : providerType,
					location : locationDefinitive
			}
			console.log(angular.toJson(searchLocationTypeRequest));
			$state.go("providerListState", {"searchLocationTypeRequest" : searchLocationTypeRequest});
		}
		
		$scope.advancedSearch = function(){
			var searchLocationTypeRequest = {
					providerType : $scope.search.providerType,
					location : {
						countryCode : $scope.search.country.name,
						province : $scope.search.state.name,
						city : $scope.search.city.name,
						streetAddress : $scope.search.streetAddress
					}
			}
			$state.go("providerListState", {"searchLocationTypeRequest" : searchLocationTypeRequest});
		}
		
		$scope.toAdvancedSearch = function(){
			$state.go("providerAdvancedSearch");
		}
		
		
}]);


mieventoControllers.controller("LocationSearchComboController",["$scope", "$state", "countryService","providerService", "applicationContext",
                                                           function($scope, $state, countryService, providerService,applicationContext){
	
	$scope.countries = applicationContext.getCountryContext().getAllCountries();
	if ($scope.countries == null){
		countryService.getAll(function(data) {
			$scope.countries = data;
			applicationContext.getCountryContext().setAllCountries(data);
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});	
	}
	
	$scope.loadStates = function(){
		$scope.states = $scope.search.country.states;
		$scope.search.state = ""; 
	}
	$scope.loadCities = function(){
		$scope.cities = $scope.search.state.cities;
		$scope.search.city = ""; 
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
						
						if (angular.equals(state.name, "eventState.budget")){
							
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
						}else /* if (angular.equals(state.name, "eventState.providers"))*/{
							var error = applicationContext.getEventContext().addProviderSelectedEvent(provider);
							
							if (error != null){
								error.description = "El proveedor ya existe para el evento !";
								applicationContext.getExceptionContext().setWarning(error);
								return;
							}
							
							userService.update(logged_user, function() {
								applicationContext.getUserContext().setLoggedUser(logged_user);
								//actualizo la vista del budget 
								$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
								
								info = {code : 0004,description : "El proveedor fue agregado con exito !"};
								applicationContext.getExceptionContext().setSuccess(info);
								
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
						}
					}
						
						
				
//						$state.go(state.name);
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


