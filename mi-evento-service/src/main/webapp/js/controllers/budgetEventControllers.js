

mieventoControllers.controller("BudgetEventController", ["$rootScope", "$scope", "$state", "providerService", "userService", "applicationContext",
                function($rootScope, $scope, $state, providerService, userService, applicationContext) {

		
		/********* Min and colors **********/
		minColors = function(){
			angular.forEach($scope.providers,function(provider){
				provider.ngStyle = "{'color' : 'red'}";
				
				minPrice = 0;
				minProvider = provider;
				angular.forEach($scope.providersToCompare,function(providerToCompare){
					price = providerToCompare.estimatedPrice;
					
					if (price <= minPrice){
						minPrice = price;
						minProvider = providerToCompare;
					}
				});
				
				minProvider.ngStyle = "{'color' : 'green'}";
			});
		};
		
		
		/********* Transformer **********/
		
		getAllProviderByType = function(providers, type){
			var providersByType = [];
			angular.forEach(providers, function(provider){
				if (angular.equals(provider.providerType,type)){
					providersByType.push(provider);
				}
			});
			return providersByType;
		}
		
		getAllTypes = function(providers){
			var types = [];
			angular.forEach(providers, function(provider){
				types.push(provider.providerType);
			});
			return types;
		}
		
		
		initialize = function(){
			
			$scope.providers = applicationContext.getEventContext().getProvidersSelectedEvent();
			$scope.providersToCompare = applicationContext.getEventContext().getProvidersToCompareEvent();
			
			$scope.providers = _.chain($scope.providers).each(function(provider){ provider.selected = true;}).sortBy("estimatedPrice");
			$scope.providersToCompare = _.chain($scope.providersToCompare).each(function(provider){ provider.selected = false;}).sortBy("estimatedPrice");
			minColors();
			
			$scope.items = [];
			types = _.uniq(getAllTypes($scope.providers));
			
			angular.forEach(types, function(type){
				$scope.providersByType = getAllProviderByType($scope.providers, type);
				var providers = $scope.providersByType;
				
				if ($scope.providersToCompare != null){
					$scope.providersToCompareByType = getAllProviderByType($scope.providersToCompare, type);
					providers = $scope.providersByType.concat($scope.providersToCompareByType);
				}
				var totalBudget = _.chain(providers).filter(function(provider){ return provider.selected === true;}).reduce(function(memo, provider){ return parseInt(memo) + parseInt(provider.estimatedPrice); }, 0).value();
				
				item = {
						"providerType" : type,
						"providers" : providers,
						"totalBudget" : totalBudget
				};
				$scope.items.push(item);
			});
		}	
		
		/*********  Start **************/
		
		initialize();
		
		/********** Functions *************/

		$scope.goToEditProvider = function(provider){
			applicationContext.getEventContext().setEditProvider(provider);
			$state.go("eventState.providerEdit");
		}
		
		$scope.goToSearchAnotherProvider = function(provider){
			var searchLocationTypeRequest = {
					providerType : provider.providerType
			}
			applicationContext.setSearchLocationTypeRequest(searchLocationTypeRequest);
			$state.go("providerListState");
		}
		
		
		$scope.goToMoreCheaper = function(provider){
			
			var place = applicationContext.getEventContext().getPlaceSelectedEvent();
			if (place == null){
				warning = {code : "0013"};
				applicationContext.getExceptionContext().setWarning(warning);
				return false;
			}
			
			var location = (place != null)? place.location : null;
			
			var params = [];
			params.push(provider.providerType);
			
			var providerTypeRequest = {
					types : params
			}
	
			providerService.getMoreCheaperByCategory(providerTypeRequest, function(data){
				
				if (data.length == 0){
					info = {code : "0016"};
					applicationContext.getExceptionContext().setInfo(info);
					return false;
				}
				
				var errorProvComp = null;
				var errorProv = applicationContext.getEventContext().isExistsInProvider(data[0]);
				
				if (errorProv == null){ // si no hay error
					errorProvComp = applicationContext.getEventContext().addProviderToCompareEvent(data[0]);
				}
				var info = (errorProvComp != null) ? errorProvComp : errorProv;
				
				if (info != null){
					applicationContext.getExceptionContext().setInfo(info);
				}else{
					initialize();//refresh
				}
			}, function(error){
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
		
		$scope.checkNumber = function(price){
			console.log(price);
			console.log(price == null);
			console.log(_.isNaN(price));
			if (price == null || _.isNaN(price)){
				return "Error : You must type a valid number !";
			}
			
		}
		
		$scope.save = function(newPrice,provider){
			provider.estimatedPrice = parseFloat(newPrice);
			var user = applicationContext.getUserContext().getLoggedUser();
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				minColors();
				
				var success = {code : "2000"}
				applicationContext.getExceptionContext().setSuccess(success);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
		
		$scope.setSelectProvider = function(item, provider){
			if (provider.selected === true){
				provider.selected = false;
			}else {
				provider.selected = true;
			}
			
			item.totalBudget = _.chain(item.providers).filter(function(provider){ return provider.selected === true;}).reduce(function(memo, provider){ return memo + provider.estimatedPrice; }, 0).value();
		}
		
		
		
		$scope.saveSelectedProviders = function(){

			var allProvidersSelected = [];
			_.each($scope.items, function(item){ allProvidersSelected.push(item.providers)});
			
			if (!validHallsDuplicates(allProvidersSelected)){
				var error = {code : "0017"}
				applicationContext.getExceptionContext().setDanger(error);
				return false;
			}
			
			allProvidersSelected = _.chain(allProvidersSelected).flatten(true).filter(function(provider){ return provider.selected === true;}).value();
			allProvidersNotSelected = _.chain(allProvidersSelected).flatten(true).filter(function(provider){ return provider.selected === false;}).value();
		
			applicationContext.getEventContext().setProvidersSelectedEvent(allProvidersSelected);
			applicationContext.getEventContext().setProvidersToCompareEvent(allProvidersNotSelected);
			
			var user = applicationContext.getUserContext().getLoggedUser();
			
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				initialize();
				$rootScope.$broadcast(TAG_SUMMARY_VIEW_BUDGET_UPDATE);
				
				var success = {code : "2000"}
				applicationContext.getExceptionContext().setSuccess(success);
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});			
		}
		
		
		validHallsDuplicates = function(itemProviders){
			console.log(angular.toJson(itemProviders));
			var size = _.chain(itemProviders).flatten(true).filter(function(provider){ return provider.providerType.indexOf("Salon") != -1 && provider.selected === true;;}).size().value();
			console.log(size);
			return (size > 1) ? false : true;
		}
		
		
 } ]);


