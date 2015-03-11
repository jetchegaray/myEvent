

mieventoControllers.controller("ReviewsPendingController", ["$scope", "applicationContext",
                function($scope, applicationContext) {

		var events = applicationContext.getUserContext().getLoggedUserEvents();
	  
		$scope.pendingReviews = _.chain(events).each(function(event){ return event.providers;}).filter(function(provider){ return (provider.reviews == null || provider.reviews.length == 0);}).size().value();
		
 } ]);



mieventoControllers.controller("ReviewsEventController", ["$scope", "userService", "providerService", "applicationContext",
                function($scope, userService, providerService, applicationContext) {

		var events = applicationContext.getUserContext().getLoggedUserEvents();
		$scope.providersWithoutReviews = _.chain(events).map(function(event){ return event.providers;}).flatten().filter(function(provider){ return (provider.reviews == null || provider.reviews.length == 0);}).value();
		
		console.log("AAAAAAA"+angular.toJson($scope.providersWithoutReviews));
		
		$scope.save = function(providerUpdated){
			var user = applicationContext.getUserContext().getLoggedUser();
			$scope.review.userName = user.nickName;
			providerUpdated.reviews.push($scope.review);
			
			userService.update(user, function() {
				//nothing to do.
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});
		
			providerService.updateReviews(providerUpdated, function() {
				//nothing to do.
			}, function(error) {
				applicationContext.getExceptionContext().setDanger(error.data);
			});	
		}
		
		
 } ]);