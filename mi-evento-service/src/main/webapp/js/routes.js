BASE_PATH = "/";
HOME_PATH = "/home"
LOGIN_PATH = "/user/login";
SIGNUP_PATH = "/user/signUp";
PROVIDER_PATH = "/providers/:providerType";
PROVIDER_DETAIL_PATH = "/provider/detail";
EVENTS_PATH = "/events";
EVENT_NEW_PATH = "/create";
EVENT_GUESTS_PATH = "/guests";
EVENT_NEW_GUESTS_PATH = "/guest/create";
EVENT_EDIT_GUESTS_PATH="/guest/edit";
EVENT_PLACE_PATH = "/place";

//STATE_HOME="homeState";
//STATE_LOGIN="loginState";
//STATE_SIGNINUP="signUpState";	
//STATE_PROVIDER="providerState";	
//STATE_EVENT="eventState";
//STATE_EVENT_CREATE="eventState.create";
//STATE_EVENT_GUEST="eventState.guests";
//STATE_EVENT_GUEST_CREATE="eventState.guest.create";
//STATE_EVENT_PLACE="eventState.place";



mieventoApp.config([ "$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise(HOME_PATH);
	 
	$stateProvider
	.state("homeState", {
		url: HOME_PATH,
		templateUrl : "../partials/home.html"
	})
	.state("loginState",{
		url : LOGIN_PATH,
		templateUrl : "../partials/login.html",
		controller : "loginController"
	})
	.state("signUpState",{
		url : SIGNUP_PATH,
		templateUrl : "../partials/signUp.html",
		controller : "signUpController"
	})
	.state("providerListState",{
		url : PROVIDER_PATH,
		templateUrl : "../partials/providers/listProvider.html",
		controller : "providerListController"
	})
	.state("providerDetailState",{
		url : PROVIDER_DETAIL_PATH,
		templateUrl : "../partials/providers/detailProvider.html",
		controller : "providerDetailController"
	})
	.state("eventState",{
		url : EVENTS_PATH,
		templateUrl : "../partials/events/events.html",
	})
	.state("eventState.create",{
		url : EVENT_NEW_PATH,
		templateUrl : "../partials/events/newEvent.html",
		controller : "newEventController"
	})
	.state("eventState.guests",{
		url : EVENT_GUESTS_PATH,
		templateUrl : "../partials/events/guests/guests.html",
		controller : "guestsEventController"
	})
	.state("eventState.guestCreate",{
		url : EVENT_NEW_GUESTS_PATH,
		templateUrl : "../partials/events/guests/detailGuest.html",
		controller : "newGuestEventController"
	})
	.state("eventState.guestEdit",{
		url : EVENT_EDIT_GUESTS_PATH,
		templateUrl : "../partials/events/guests/detailGuest.html",
		controller : "editGuestEventController"
	})
	.state("eventState.place",{
		url : EVENT_PLACE_PATH,
		templateUrl : "../partials/events/place.html",
		controller : "placeEventController"
	})
	
	
} ]);


