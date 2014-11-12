
mieventoControllers.controller("calendarEventController", ["$scope", "$state", "userService", "$modal", "applicationContext", 
					function($scope, $state, userService, $modal, applicationContext) {
					
	  $scope.userEvents = applicationContext.getUserContext().getLoggedUserEvents();
	  
	  calendarEventBuilder = function(applicationContext){
	    	calendarEvents = [];
	    	angular.forEach($scope.userEvents,function(event){
	    		
	    		calendarEvents.push(newCalendarEvent(event.name, new Date(event.eventDate),
	    				new Date(event.eventDate), "Event to do.", 'openSesame'));
	    
	    		angular.forEach(event.tasks,function(task){
	    			calendarEvents.push(newCalendarEvent(task.name, new Date(task.initialDate),
	    					new Date(task.finalDate), "Task to do this day", 'openSesame'));
	    		});
	    	
	    		angular.forEach(event.guests,function(guest){
	    			var name = guest.firstName;
	    			if (guest.lastName != null){
	    				name += " "+guest.lastName;
	    			}
	    			calendarEvents.push(newCalendarEvent(name+" should confirm.", new Date(guest.invitationStatus.updateStatusDate),
	    					new Date(guest.invitationStatus.updateStatusDate), "Guest Invited confirm this day", 'openSesame'));
	    		});
	    	});
	    	
	    	return calendarEvents;
	 	}

	 	newCalendarEvent = function(name, initialDate, finalDate, description, className){
    		return {
    		    title: name,
    	        start:  initialDate,
    	        end: finalDate,
    	        description : description,
    	        className: [className],
    	        allDay: false,
    	        editable: true,
    	        backgroundColor: '#088da5',
    	        borderColor: '#000000'
    	      }
    	}	

	 	//init controller.
		$scope.calendarEvents = calendarEventBuilder(applicationContext);
		var date = new Date();
	    var d = date.getDate();
	    var m = date.getMonth();
	    var y = date.getFullYear();
	    
	    /* event source that contains custom events on the scope */
	    $scope.events = [
	      {title: 'All Day Event',start: new Date(y, m, 1)},
	      {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
	      {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false},
	      {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
	      {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
	      {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
	    ];
	    

	    $scope.calEventsExt = {
	       color: '#f00',
	       textColor: 'yellow',
	       events: [ 
	          {title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
	          {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
	          {type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
	        ]
	    };
//	    /* alert on eventClick */
//	    $scope.onEventClick = function( event, allDay, jsEvent, view ){
//	        $scope.alertMessage = (event.title + ' was clicked ');
//	    };
//	    /* alert on Drop */
//	     $scope.alertOnDrop = function( event, revertFunc, jsEvent, ui, view){
//	       $scope.alertMessage = ('Event Droped on ' + event.start.format());
//	    };
	    /* alert on Resize */
	    $scope.alertOnResize = function( event, jsEvent, ui, view){
	       $scope.alertMessage = ('Event end date was moved to ' + event.end.format());
	    };

	    
	    /* Change View */
	    $scope.changeView = function(view,calendar) {
	      calendar.fullCalendar('changeView',view);
	    };
	    /* Change View */
	    $scope.renderCalender = function(calendar) {
	      if(calendar){
	        calendar.fullCalendar('render');
	      }
	    };
	    
	    
	    /* Modal delete event */
	    $scope.showDeleteModal = function () {
	    	
	    	var modalInstance = $modal.open({
	            backdropClick: false,
	            templateUrl : 'deleteCalendarEvent.html',
				controller : "deleteCalendarEventInstanceController",
	            resolve : {
	            	task : function () {
	                    return $scope.selectedTask;
	            	}
	            }
	    	});
	        
	        modalInstance.result.then(function (task) {
	        	applicationContext.getUserContext().deleteTaskToEvent(task);
	        });
	    };
	    /* Modal add event */
	    $scope.showAddModal = function () {
	    	var modalInstance = $modal.open({
	            templateUrl : 'addCalendarEvent.html',
				controller : "addEventInstanceController",
	            resolve: {
	            	events : function () {
	            		return $scope.calendarEvents;
	                },
	                selectedDay : function(){
	                	return $scope.selectedDay;
	                }
	            }
	        });
	        
	        modalInstance.result.then(function (result) {
	        	 applicationContext.getUserContext().addTaskToEvent(result.selectedEventName, result.selectedTask);
	        	 $scope.calendarEvents.push(newCalendarEvent(result.selectedTask.name, new Date(result.selectedTask.initialDate),
	    					new Date(result.selectedTask.finalDate), "Task to do this day", 'openSesame'));
	        });
	    };
	    
	    
	    /* config object */
	    $scope.uiConfig = {
	      calendar:{
	        editable: true,
	        disableResizing:false,
	        header:{
	        	left: 'prev,next today',
	        	center: 'title',
	        	right: 'month,agendaWeek,agendaDay'
	        },
	        defaultView: 'month',
	        eventClick: function(event, allDay, jsEvent, view){
	        	$scope.selectedTask = event;
	            $scope.$apply(function(){
	              $scope.showDeleteModal();
	            });
	        },
	        eventDrop: $scope.alertOnDrop,
	        eventResize: $scope.alertOnResize,
	        
	        dayClick: function(date, allDay, jsEvent, view){
	        	$scope.selectedDay = date;

	            $scope.$apply(function(){
	              $scope.showAddModal();
	            });
	        },
	      }
	    };

//	    $scope.changeLang = function() {
//	        $scope.uiConfig.calendar.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
//	        $scope.uiConfig.calendar.dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
//	    };
//	    
	    /* event sources array*/
	    $scope.eventSources = [ $scope.calendarEvents];
	
//	    $scope.dayClick = function( date, allDay, jsEvent, view ){
//	        $scope.$apply(function(){
//	          $scope.alertMessage = ('Day Clicked ' + date);
//	        });
//	    };
	    
}]);




mieventoControllers.controller("deleteCalendarEventInstanceController", ["$scope", "$modalInstance", "task", function($scope, $modalInstance, task) {

			$scope.task = task;
			$scope.ok = function() {
				$modalInstance.close();
			}

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			}

} ]);

mieventoControllers.controller("addEventInstanceController", ["$scope", "$modalInstance", "events", "selectedDay", function($scope, $modalInstance, events, selectedDay) {
	
	$scope.events = events;
	$scope.task = {
			initialDate : selectedDay,
			finalDate : selectedDay	
	}
	$scope.selectedEvent = events[0];
	
	$scope.ok = function() {
		var result = {
			selectedEventName : $scope.selectedEvent.title,
			selectedTask : $scope.task
		}
		$modalInstance.close(result);
	}

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	}

} ]);