
mieventoControllers.controller("calendarEventController", ["$scope", "$state", "userService", "applicationContext", 
					function($scope, $state, userService, applicationContext) {
					
		$scope.calendarEvents = calendarBuilder(applicationContext);
		
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
	    /* event source that calls a function on every view switch */
	    $scope.eventsF = function (start, end, timezone, callback) {
	      var s = new Date(start).getTime() / 1000;
	      var e = new Date(end).getTime() / 1000;
	      var m = new Date(start).getMonth();
	      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
	      callback(events);
	    };

	    $scope.calEventsExt = {
	       color: '#f00',
	       textColor: 'yellow',
	       events: [ 
	          {title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
	          {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
	          {type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
	        ]
	    };
	    /* alert on eventClick */
	    $scope.alertOnEventClick = function( event, allDay, jsEvent, view ){
	        $scope.alertMessage = (event.title + ' was clicked ');
	    };
	    /* alert on Drop */
	     $scope.alertOnDrop = function( event, revertFunc, jsEvent, ui, view){
	       $scope.alertMessage = ('Event Droped on ' + event.start.format());
	    };
	    /* alert on Resize */
	    $scope.alertOnResize = function( event, jsEvent, ui, view){
	       $scope.alertMessage = ('Event end date was moved to ' + event.end.format());
	    };
	    /* add and removes an event source of choice */
	    $scope.addRemoveEventSource = function(sources,source) {
	      var canAdd = 0;
	      angular.forEach(sources,function(value, key){
	        if(sources[key] === source){
	          sources.splice(key,1);
	          canAdd = 1;
	        }
	      });
	      if(canAdd === 0){
	        sources.push(source);
	      }
	    };
	    /* add custom event*/
	    $scope.addEvent = function() {
	      $scope.events.push({
	        title: 'Open Sesame',
	        start: new Date(y, m, 28),
	        end: new Date(y, m, 29),
	        className: ['openSesame']
	      });
	    };
	    /* remove event */
	    $scope.remove = function(index) {
	      $scope.events.splice(index,1);
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
	    /* config object */
	    $scope.uiConfig = {
	      calendar:{
	        editable: true,
	        header:{
	          left: 'title',
	          center: '',
	          right: 'today prev,next'
	        },
	        eventClick: $scope.alertOnEventClick,
	        eventDrop: $scope.alertOnDrop,
	        eventResize: $scope.alertOnResize,
	        dayClick: $scope.dayClick
	      }
	    };

	    $scope.changeLang = function() {
	        $scope.uiConfig.calendar.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	        $scope.uiConfig.calendar.dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	    };
	    /* event sources array*/
	    $scope.eventSources = [ $scope.events, $scope.eventsF, $scope.calEventsExt];
	
	    $scope.dayClick = function( date, allDay, jsEvent, view ){
	        $scope.$apply(function(){
	          $scope.alertMessage = ('Day Clicked ' + date);
	        });
	    };
	
	    
	    calendarEventBuilder = function(applicationContext){
	    	calendarEvents = [];
	    	
	    	events = applicationContext.getUserContext().getLoggedUserEvents();
	    	
	    	angular.forEach(events,function(event){
	    		calendarEvents.push(newCalendarEvent(event.name, new Date(event.eventDate).format("dd-mm-yyyy HH:MM"),
	    			new Date(y, m, 29), 'openSesame'));
	    		
	    		angular.forEach(event.tasks,function(task){
	    			calendarEvents.push(newCalendarEvent(task.name, new Date(task.initialDate).format("dd-mm-yyyy HH:MM"),
	    					new Date(task.finalDate).format("dd-mm-yyyy HH:MM"), 'openSesame'));
	    		});
	    		
	    		angular.forEach(event.guests,function(guest){
	    			calendarEvents.push(newCalendarEvent(guest.firstName+" "+guest.lastName+" should confirm.", new Date(guest.invitationStatus.updateStatusDate).format("dd-mm-yyyy HH:MM"),
	    					new Date(guest.invitationStatus.updateStatusDate).format("dd-mm-yyyy HH:MM"), 'openSesame'));
	    		});
	    	});
	    		
	    	newCalendarEvent = function(name, initialDate, finalDate, className){
	    		return {
	    	        title: name,
	    	        start:  initialDate,
	    	        end: finalDate,
	    	        className: [className]
	    	      }
	    	}	
	    	
	    	

	    } 
	    
}]);
