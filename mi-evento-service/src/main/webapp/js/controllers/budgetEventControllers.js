

mieventoControllers.controller("BudgetEventController", ["$scope", "$state", "providerService", "userService", "applicationContext",
                function($scope, $state, providerService, userService, applicationContext) {

		
		/********* Min and colors **********/
		minColors = function(){
			angular.forEach($scope.providers,function(provider){
				provider.ngStyle = "{'color' : 'red'}";
				
				minPrice = 0;
				minProvider = provider;
				angular.forEach($scope.providersToCompare,function(providerToCompare){
					price = (providerToCompare.price != null) ? providerToCompare.price: providerToCompare.estimatedPrice;
					
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
			providersByType = [];
			angular.forEach(providers, function(provider){
				if (angular.equals(provider.providerType,type)){
					providersByType.push(provider);
				}
			});
			return providersByType;
		}
		
		getAllTypes = function(providers){
			types = [];
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
			types = getAllTypes($scope.providers);
			
			angular.forEach(types, function(type){
				$scope.providersByType = getAllProviderByType($scope.providers, type);
				providers = providersByType;
				
				if ($scope.providersToCompare != null){
					$scope.providersToCompareByType = getAllProviderByType($scope.providersToCompare, type);
					providers = $scope.providersByType.concat($scope.providersToCompareByType);
				}
				
				item = {
						providerType : type,
						providers : providers
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
			$state.go("providerListState",{"providerType" : provider.providerType});
		}
		
		
		$scope.goToMoreCheaper = function(provider){
			
			params = [];
			params.push(provider.providerType);
			providerTypeRequest = {
					types : params
			}
	
			providerService.getMoreCheaperByCategory(providerTypeRequest, function(data){
				applicationContext.getEventContext().setProvidersToCompareEvent(data);
				initialize();//refresh
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
			provider.price = newPrice;
			
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				minColors();
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
		
		$scope.changeProvidersEvent = function(newProviders){
			changeProviders = _.chain(newProviders).find(function(newProviders){ return provider.selected == true})
			applicationContext.getEventContext().setProvidersSelectedEvent(changeProviders);
			
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				initialize();
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		};
		
		
		$scope.setSelectProvider = function(provider){
			if (provider.selected === true){
				provider.selected = false;
			}
			provider.selected = true;
			
			var allProvidersSelected = [];
			_.each($scope.items, function(item){ allProvidersSelected.push(item.providers)});
			allProvidersSelected = _.chain(allProvidersSelected).flatten(true).filter(function(provider){ return provider.selected === true;}).value();
			applicationContext.getEventContext().setProvidersSelectedEvent(allProvidersSelected);
			
			console.log(angular.toJson(allProvidersSelected));
			
			userService.update(applicationContext.getUserContext().getLoggedUser(), function() {
				initialize();
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		}
 } ]);


