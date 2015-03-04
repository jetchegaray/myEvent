
mieventoControllers.controller("ScheduledEventController", ["$scope", "applicationContext", 
					function($scope, applicationContext) {
					
	  events = applicationContext.getUserContext().getLoggedUserEvents();
	  
	  var collectItems = [];
	  angular.forEach(events, function(event){
		  console.log(event.initialDate);
		  item = {
					date : event.initialDate,
					name : event.name
		  };
		  collectItems.push(item);
			
		  angular.forEach(event.tasks, function(task){
			
			itemInitial = {
					date : task.initialDate,
					name : "Start Task : "+task.name + " - Event :" + event.name
			};
			itemFinal = {
					date : task.finalDate,
					name : "Finish Task : "+task.name + " - Event :" + event.name
			};
			collectItems.push(itemInitial,itemFinal);
		  });
		});
	  
	  $scope.items = _.chain(collectItems).sortBy("date").groupBy("date").map(function(value, key) {
          return {
              date: key,
              hours : _.pluck(value, "date"),
              names : _.pluck(value, "name")
          }
      }).value();
}]);