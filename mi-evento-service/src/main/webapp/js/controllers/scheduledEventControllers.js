
mieventoControllers.controller("ScheduledEventController", ["$scope", "$filter", "applicationContext", 
					function($scope, $filter, applicationContext) {
					
	  events = applicationContext.getUserContext().getLoggedUserEvents();
	  
	  var collectItems = [];
	  angular.forEach(events, function(event){
		  console.log(event.initialDate);
		  item = {
				  	date : event.initialDate,
				  	element : {
				  		hour : $filter('date')(event.initialDate, "h:mm a"),
				  		name : event.name
				  	}
		  };
		  collectItems.push(item);
			
		  angular.forEach(event.tasks, function(task){
			
			itemInitial = {
					date : task.initialDate,
					element : {
						hour : $filter('date')(task.initialDate, "h:mm a"),
						name : "Start Task : "+task.name + " - Event :" + event.name
					}
			};
			itemFinal = {
					date : task.finalDate,
					element : {
					  	hour : $filter('date')(task.finalDate, "h:mm a"),
						name : "Finish Task : "+task.name + " - Event :" + event.name
					}
			};
			collectItems.push(itemInitial,itemFinal);
		  });
		});
	  
	  
	  
	  $scope.items = _.chain(collectItems).sortBy("date").groupBy("date").map(function(value, key) {
          return {
              date: key,
              elements : _.pluck(value, "element")
          }
      }).value();
	  console.log(angular.toJson($scope.items));	
}]);