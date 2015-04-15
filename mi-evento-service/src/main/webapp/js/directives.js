	
mieventoApp.directive('disableAnimation', function($animate){
    return {
        restrict: 'A',
        link: function($scope, $element, $attrs){
            $attrs.$observe('disableAnimation', function(value){
                $animate.enabled(!value, $element);
            });
        }
    }
});


mieventoApp.directive('mieventoMap', function() {
    
	 return {
	        restrict: 'A',
	        template: '<div id="gmaps"></div>',
	        replace: true,
	        scope: {
	        	markerMap: "=",
	        	centerMap: "="
	        },
	        link: function(scope, element, attrs) {
		        var map, infoWindow;
		        var markers = [];
		        // map config
		        var mapOptions = {
		            center: new google.maps.LatLng(scope.centerMap.lat,scope.centerMap.lng),
		            zoom: 14,
//		            mapTypeId: google.maps.MapTypeId.SATELLITE,
		            scrollwheel: false
		        };
		        
		        // init the map
		        function initMap() {
		            if (map === void 0) {
		                map = new google.maps.Map(element[0], mapOptions);
		            }
		        }    
		        
		        // place a marker
		        function setMarker(map, position, title, content) {
		            var marker;
		            var markerOptions = {
		                position: position,
		                map: map,
		                title: title,
		                icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
		            };
		
		            marker = new google.maps.Marker(markerOptions);
		            markers.push(marker); // add marker to array
		            
		            google.maps.event.addListener(marker, 'click', function () {
		                // close window if not undefined
		                if (infoWindow !== void 0) {
		                    infoWindow.close();
		                }
		                // create new window
		                var infoWindowOptions = {
		                    content: content
		                };
		                infoWindow = new google.maps.InfoWindow(infoWindowOptions);
		                infoWindow.open(map, marker);
		            });
		        }
		        
		        // show the map and place some markers
		        initMap();
		        
		        setMarker(map, new google.maps.LatLng(scope.markerMap.lat, scope.markerMap.lng), scope.markerMap.title, scope.markerMap.description);
		    }
	 };
});

mieventoApp.directive('uiSelectRequired', function() {
	  return {
	    require: 'ngModel',
	    link: function(scope, elm, attrs, ctrl) {
	      ctrl.$validators.uiSelectRequired = function(modelValue, viewValue) {
	        return modelValue && modelValue.length;
	      };
	    }
	  };
	});