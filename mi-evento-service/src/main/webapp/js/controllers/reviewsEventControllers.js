TAG_REVIEW_UPDATE = "reviewUpdated"

mieventoControllers.controller("ReviewsPendingController", ["$scope", "applicationContext",
                function($scope, applicationContext) {
	
		getPendingReviews = function(){
			var events = applicationContext.getUserContext().getLoggedUserEvents();
			return _.chain(events).map(function(event){ return event.providers;}).flatten().filter(function(provider){ return (provider.reviews == null || provider.reviews.length == 0);}).size().value();
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
			var providersWRe = _.chain(events).map(function(event){ return event.providers;}).flatten().filter(function(provider){ return (provider.reviews == null || provider.reviews.length == 0);}).value();
			
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
			
			console.log(angular.toJson(providerUpdated));
			
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