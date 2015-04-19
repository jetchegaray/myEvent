mieventoControllers.controller("ProviderTypeController",["$scope", "$state", "providerService", "applicationContext", 
                                                         function($scope, $state, providerService,applicationContext){
			
			providerService.getAllTypes(function(data){
				$scope.types = data;
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
			
			
			$scope.goToProviderList = function(type){
				var place = applicationContext.getEventContext().getPlaceSelectedEvent();
				
				var location = (place != null)? place.location : null;
				
				var searchLocationTypeRequest = {
						providerType : type,
						location : location
				}
				applicationContext.setSearchLocationTypeRequest(searchLocationTypeRequest);
				$state.go("providerListState", {}, {reload: true});
			}
					
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
		
		
		$scope.search = {}; //para que funcionen los combos en un child controller.
		$scope.advancedSearch = function(){
			
			var countryCode = null;
			if (!angular.isUndefined($scope.search.country)){
				countryCode = $scope.search.country.name;
			}
			var province = null;
			if (!angular.isUndefined($scope.search.state)){
				countryCode = $scope.search.state.name;
			}
			var city = null;
			if (!angular.isUndefined($scope.search.city)){
				city = $scope.search.city.name;
			}
			
			var searchLocationTypeRequest = {
					name : $scope.search.businessName,
					providerType : $scope.search.providerType,
					location : {
						countryCode : countryCode,
						province : province,
						city : city,
						streetAddress : $scope.search.streetAddress
					}
			}	
			applicationContext.setSearchLocationTypeRequest(searchLocationTypeRequest);
			$state.go("providerListState");
		}
		
		$scope.toAdvancedSearch = function(){
			$state.go("providerAdvancedSearch");
		}
		
		
}]);


mieventoControllers.controller("LocationSearchComboController",["$scope", "countryService", "applicationContext",
                                                           function($scope, countryService, applicationContext){
	
	$scope.countries = applicationContext.getCountryContext().getAllCountries();
	if ($scope.countries == null){
		countryService.getAll(function(data) {
			$scope.countries = data;
			applicationContext.getCountryContext().setAllCountries(data);
		
			if (!angular.isUndefined($scope.$parent.countrySelected) && $scope.$parent.countrySelected != ""){
		
				$scope.search.country = _.find($scope.countries,function(country){ return country.name == $scope.$parent.countrySelected;});
				if ($scope.$parent.stateSelected != ""){
					$scope.states = $scope.search.country.states;
					$scope.search.state = _.find($scope.search.country.states,function(state){ return state.name == $scope.$parent.stateSelected});
					
					if ($scope.$parent.citySelected != ""){
						$scope.cities = $scope.search.state.cities;
						$scope.search.city = _.find($scope.search.state.cities,function(city){ return city.name == $scope.$parent.citySelected});
					}
				}
			}
		
		}, function(error) {
			applicationContext.getExceptionContext().setDanger(error.data);
		});	

	}
	
	$scope.loadStates = function(search){
		$scope.states = $scope.search.country.states;
		$scope.search.state = ""; 
	}
	
	$scope.loadCities = function(search){
		$scope.cities = $scope.search.state.cities;
		$scope.search.city = ""; 
	}
}]);




mieventoControllers.controller("ProviderDetailController",["$scope","applicationContext", 
                                                           function($scope, applicationContext){
	
		$scope.provider = applicationContext.getProviderContext().getDetailProvider();
		$scope.user = applicationContext.getUserContext().getLoggedUser();
		
		var description = applicationContext.getProviderContext().getLocationToStringProvider();
		$scope.centerMap = { lat: $scope.provider.location.lat, lng: $scope.provider.location.lng };
		$scope.markerMap = { lat : $scope.provider.location.lat ,lng : $scope.provider.location.lng , title : $scope.provider.businessName ,description : description};
		
		$scope.getStars = function(review){	
			return 	_.range(0,review.rating);
		}
		
		$scope.getEmptyStars = function(review){
			return 	_.range(review.rating,5);
		}
}]);



mieventoControllers.controller("ProviderListController",["$rootScope", "$scope", "$state", 
         "$anchorScroll", "$filter","providerService", "userService", "applicationContext", 
         function( $rootScope, $scope, $state, $anchorScroll, $filter, providerService, userService, applicationContext){
				$scope.searching = true;
				$scope.currentPage = 1;
				$scope.itemPerPage = 10;
				$scope.maxSize = 5;
				
				getPage = function(){
					$scope.totalItems = _.size($scope.providers);
					
					var begin = (($scope.currentPage - 1) * $scope.itemPerPage);
					var end = begin + $scope.itemPerPage;
					$scope.providersPagination = $scope.providers.slice(begin, end);
					$anchorScroll();
				}
				providerService.getByLocationAndType(applicationContext.getSearchLocationTypeRequest(),function(data){
				
					$scope.searching =false;
					$scope.providers = $filter('orderBy')(data,"estimatedPrice");
					getPage();
		
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
						var state = applicationContext.getPreviousState();
						
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
								info = {code : "2002"};
								applicationContext.getExceptionContext().setSuccess(info);
							}
						}else /* if (angular.equals(state.name, "eventState.providers"))*/{
							var error = null;
							
							if (provider.providerType.indexOf("Salon") > -1){
								if (applicationContext.getEventContext().getPlaceSelectedEvent() != null){
									error = {code : "0014"};
									applicationContext.getExceptionContext().setWarning(error);
									return
								}
//								applicationContext.getEventContext().setProviderPlaceSelectedEvent(provider);
							}//else {
							
							error = applicationContext.getEventContext().addProviderSelectedEvent(provider);
//							}
							
							if (error != null){
								
								error = {code : "0003"};
								applicationContext.getExceptionContext().setWarning(error);
								return;
							}
							
							userService.update(logged_user, function() {
								applicationContext.getUserContext().setLoggedUser(logged_user);
								//actualizo la vista del budget 
								$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
								
								info = {code : "2001"};
								applicationContext.getExceptionContext().setSuccess(info);
								
							}, function(error) {
								applicationContext.getExceptionContext().setDanger(error.data);
							});
						}
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
				
				$scope.pageChanged = function() {
		            getPage(); 
				}; 
}]);


