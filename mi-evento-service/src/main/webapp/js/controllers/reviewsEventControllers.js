TAG_REVIEW_UPDATE = "reviewUpdated"

mieventoControllers.controller("ReviewsPendingController", ["$scope", "applicationContext",
                function($scope, applicationContext) {
	
		getPendingReviews = function(){
			var events = applicationContext.getUserContext().getLoggedUserEvents();
			var user = applicationContext.getUserContext().getLoggedUser();
			var notReviewsSize = _.chain(events).map(function(event){ return event.providers;}).flatten().filter(function(provider){ return (provider.reviews == null || provider.reviews.length == 0);}).size().value();
			
			//FIXME
			var count = 0;
			for (i = 0; i < events.length; i++) { 
				if (events[i].providers != null && events[i].providers.length != 0){
					for (j = 0; j < events[i].providers.length; j++) { 
						var hasMe = false;
						if (events[i].providers[j].reviews != null && events[i].providers[j].reviews.length != 0){
							for (k = 0; k < events[i].providers[j].reviews.length; k++) { 
			
								if (events[i].providers[j].reviews[k].userName == user.nickName){
									hasMe = true;
								}
							}
							if (!hasMe){
								count++;
							}
						}
						
					}
				}
			}
			return notReviewsSize + count;
		}
		
		$scope.pendingReviews = getPendingReviews();
		
		$scope.$on(TAG_REVIEW_UPDATE, function() {
			$scope.pendingReviews = getPendingReviews();
		});
 } ]);



mieventoControllers.controller("ReviewsEventController", ["$rootScope", "$scope", "userService", "providerService", "applicationContext",
                function($rootScope, $scope, userService, providerService, applicationContext) {
		
		getProvidersWithoutReviews = function(){
			var events = applicationContext.getUserContext().getLoggedUserEvents();
			var user = applicationContext.getUserContext().getLoggedUser();
			var providersWRe = _.chain(events).map(function(event){ return event.providers;}).flatten().filter(function(provider){ return (provider.reviews == null || provider.reviews.length == 0);}).value();
		
			//FIXME
			for (i = 0; i < events.length; i++) { 
				if (events[i].providers != null && events[i].providers.length != 0){
					for (j = 0; j < events[i].providers.length; j++) { 
						var hasMe = false;
						if (events[i].providers[j].reviews != null && events[i].providers[j].reviews.length != 0){
							for (k = 0; k < events[i].providers[j].reviews.length; k++) { 
			
								if (events[i].providers[j].reviews[k].userName == user.nickName){
									hasMe = true;
								}
							}
							if (!hasMe){
								providersWRe.push(events[i].providers[j]);
							}
						}
						
					}
				}
			}
			
			angular.forEach(providersWRe,function(prov){
				prov.isReviewOK = false;
			});
			
			return providersWRe;
		}
			
		$scope.providersWithoutReviews = getProvidersWithoutReviews();
		
		//FIXME no lo crea automatico.
		$scope.review = {};
		
		$scope.save = function(providerUpdated){
			
			providerUpdated.isReviewOK = true;
			
			var user = applicationContext.getUserContext().getLoggedUser();
			
			providerUpdated.newReview.userName = user.nickName;
			providerUpdated.reviews.push(providerUpdated.newReview);
			providerUpdated.estimatedPrice = providerUpdated.newEstimatedPrice;
			
			userService.update(user, function() {
				applicationContext.getUserContext().setLoggedUser(user);
				$scope.providersWithoutReviews = getProvidersWithoutReviews();
				$rootScope.$broadcast(TAG_REVIEW_UPDATE);
				
				success = { code : "2004"}
				applicationContext.getExceptionContext().setSuccess(error.data);
				
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		
			providerService.updateReviews(providerUpdated, function() {
				//nothing to do.
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});	
			
			$scope.hoveringOver = function(value) {
			    $scope.overStar = value;
			    $scope.percent = 100 * (value / $scope.max);
			 };
		}
		
 } ]);