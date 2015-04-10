
mieventoControllers.controller("ScheduledEventController", ["$scope", "$filter", "applicationContext", 
					function($scope, $filter, applicationContext) {
					
	  events = applicationContext.getUserContext().getLoggedUserEvents();
	  
	  var collectItems = [];
	  angular.forEach(events, function(event){
		  console.log(event.initialDate);
		  item = {
				  	dateFormat :  $filter('date')(event.initialDate, "ddMMyyyy"),
				  	date : event.initialDate,
			  		hour : $filter('date')(event.initialDate, "h:mm a"),
			  		name : "Event :"+event.name
		  };
		  collectItems.push(item);
			
		  angular.forEach(event.tasks, function(task){
			
			itemInitial = {
					dateFormat : $filter('date')(task.initialDate, "ddMMyyyy"),
					date : task.initialDate,
					hour : $filter('date')(task.initialDate, "h:mm a"),
					name : "Start Task : "+task.name + " - From Event :" + event.name
			};
			itemFinal = {
					dateFormat : $filter('date')(task.finalDate, "ddMMyyyy"),
					date : task.finalDate,
				  	hour : $filter('date')(task.finalDate, "h:mm a"),
					name : "Finish Task : "+task.name + " - From Event :" + event.name
			};
			collectItems.push(itemInitial,itemFinal);
		  });
		});
	  
	  
	  
	  $scope.items = _.chain(collectItems).sortBy("date").groupBy("dateFormat").map(function(values, key){
		  return {
			  date : values[0].date,
			  dayEvent : _.chain(values).sortBy("hour").groupBy("hour").map(function(value, key){
				  return {
					  hour : key,
					  events : _.pluck(values,"name")
				  }
			  }).value()
		  };
	  }).value();
	  console.log(angular.toJson($scope.items));	
}]);