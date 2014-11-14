
mieventoControllers.controller("calendarEventController", ["$scope", "$state", "userService", "$modal", "applicationContext", 
					function($scope, $state, userService, $modal, applicationContext) {
					
	  $scope.selectedEvent = applicationContext.getEventContext().getSelectedEvent();
	  
	  //FIXME UNIFICARRRRR
	  if ($scope.selectedEvent == null){
		  $state.go("eventState");
	  }
	  
	  calendarEventBuilder = function(){
	    	calendarEvents = [];
	    		
	    		calendarEvents.push(newCalendarEvent( $scope.selectedEvent.name, new Date( $scope.selectedEvent.eventDate),
	    				new Date( $scope.selectedEvent.eventDate), "Event to do.", 'openSesame'));
	    
	    		angular.forEach($scope.selectedEvent.tasks,function(task){
	    			calendarEvents.push(newCalendarEvent(task.name, new Date(task.initialDate),
	    					new Date(task.finalDate), "Task to do this day", 'openSesame'));
	    		});
	    	
	    		angular.forEach($scope.selectedEvent.guests,function(guest){
	    			var name = guest.firstName;
	    			if (guest.lastName != null){
	    				name += " "+guest.lastName;
	    			}
	    			calendarEvents.push(newCalendarEvent(name+" should confirm.", new Date(guest.invitationStatus.updateStatusDate),
	    					new Date(guest.invitationStatus.updateStatusDate), "Guest Invited confirm this day", 'openSesame'));
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
		$scope.calendarEvents = calendarEventBuilder();

		
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
	        
	        modalInstance.result.then(function (selectedTask) {
	        	
	        	applicationContext.getEventContext().deleteTaskFromEvent(selectedTask);
	        	
	        	var index =  $scope.calendarEvents.indexOf(newCalendarEvent(selectedTask.name, new Date(selectedTask.initialDate),
    					new Date(selectedTask.finalDate), "Task to do this day", 'openSesame'));
	        	 $scope.calendarEvents.splice(index, 1);
	        });
	    };
	    
	    
	    
	    /* Modal add event */
	    $scope.showAddModal = function () {
	    	var modalInstance = $modal.open({
	            templateUrl : 'addCalendarEvent.html',
				controller : "addEventInstanceController",
	            resolve: {
	                selectedDay : function(){
	                	return $scope.selectedDay;
	                }
	            }
	        });
	        
	        modalInstance.result.then(function (selectedTask) {
	        	 applicationContext.getEventContext().addTaskToEvent($scope.selectedEvent, selectedTask);
	        	 $scope.calendarEvents.push(newCalendarEvent(selectedTask.name, new Date(selectedTask.initialDate),
	    					new Date(selectedTask.finalDate), "Task to do this day", 'openSesame'));
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
			$modalInstance.close($scope.task);
		}

		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		}

} ]);

mieventoControllers.controller("addEventInstanceController", ["$scope", "$modalInstance", "selectedDay", function($scope, $modalInstance, selectedDay) {
	
		$scope.task = {
				initialDate : selectedDay,
				finalDate : selectedDay	
		}
		
		$scope.ok = function() {
			$modalInstance.close($scope.task);
		}
	
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		}

} ]);