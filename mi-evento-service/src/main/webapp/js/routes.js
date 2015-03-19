BASE_PATH = "/";
HOME_PATH = "/home"
LOGIN_PATH = "/user/login";
SIGNUP_PATH = "/user/signUp";
PROVIDER_PATH = "/providers/:searchLocationTypeRequest";
PROVIDER_DETAIL_PATH = "/provider/detail";
EVENTS_PATH = "/events";
EVENTS_LIST_PATH = "/list";
EVENT_NEW_PATH = "/create";
EVENT_EDIT_PATH = "/edit";
EVENT_GUESTS_PATH = "/guests";
EVENT_NEW_GUESTS_PATH = "/guest/create";
EVENT_EDIT_GUESTS_PATH="/guest/edit";
EVENT_PLACE_PATH = "/place";
EVENT_MYPLACE_PATH = "/myplace";
EVENT_PLACE_ADMIN_PATH = "/placeAdmin"	
EVENT_PROVIDERS_PATH = "/providers";
EVENT_PROVIDER_EDIT_PATH = "/provider/edit";
EVENT_CALENDAR_PATH = "/calendar";
EVENT_SCHEDULED_PATH = "/scheduled"
EVENT_BUDGET_PATH = "/budget"
EVENT_REVIEW_PATH = "/review"
	

mieventoApp.config([ "$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise(HOME_PATH);
	
	$stateProvider
	.state("homeState", {
		url: HOME_PATH,
		templateUrl : "../partials/home.html",
		controller : "HomeController"	
	})
	.state("loginState",{
		url : LOGIN_PATH,
		templateUrl : "../partials/login.html",
		controller : "LoginController"
	})
	.state("signUpState",{
		url : SIGNUP_PATH,
		templateUrl : "../partials/signUp.html",
		controller : "SignUpController"
	})
	.state("providerListState",{
		url : PROVIDER_PATH,
		templateUrl : "../partials/providers/listProvider.html",
		controller : "ProviderListController"
	})
	.state("providerDetailState",{
		url : PROVIDER_DETAIL_PATH,
		templateUrl : "../partials/providers/detailProvider.html",
		controller : "ProviderDetailController"
	})
	.state("eventState",{
		url : EVENTS_PATH,
		templateUrl : "../partials/events/events.html"
	})
	.state("eventState.events",{
		url : EVENTS_LIST_PATH,
		templateUrl : "../partials/events/listEvents.html",
		controller : "EventListController"
	})
	.state("eventState.eventCreate",{
		url : EVENT_NEW_PATH,
		templateUrl : "../partials/events/detailEvent.html",
		controller : "DetailEventController"
	})
	.state("eventState.eventEdit",{
		url : EVENT_EDIT_PATH,
		templateUrl : "../partials/events/detailEvent.html",
		controller : "EditEventController"
	})
	.state("eventState.guests",{
		url : EVENT_GUESTS_PATH,
		templateUrl : "../partials/events/guests/guests.html",
		controller : "GuestsEventController"
	})
	.state("eventState.guestCreate",{
		url : EVENT_NEW_GUESTS_PATH,
		templateUrl : "../partials/events/guests/detailGuest.html",
		controller : "DetailGuestEventController"
	})
	.state("eventState.guestEdit",{
		url : EVENT_EDIT_GUESTS_PATH,
		templateUrl : "../partials/events/guests/detailGuest.html",
		controller : "EditGuestEventController"
	})
	.state("eventState.place",{
		url : EVENT_PLACE_PATH,
		templateUrl : "../partials/events/place/place.html",
		controller : "PlaceEventController"
	})
	.state("eventState.myPlace",{
		url : EVENT_MYPLACE_PATH,
		templateUrl : "../partials/events/place/myPlace.html",
		controller : "MyPlaceEventController"
	})
	.state("eventState.placeAdmin",{
		url : EVENT_PLACE_ADMIN_PATH,
		templateUrl : "../partials/events/place/administratorPlace.html",
		controller : "PlaceAdministratorController"
	})
	.state("eventState.providers",{
		url : EVENT_PROVIDERS_PATH,
		templateUrl : "../partials/events/providers/eventProviders.html",
		controller : "ProvidersEventController"
	})
	.state("eventState.providerEdit",{
		url : EVENT_PROVIDER_EDIT_PATH,
		templateUrl : "../partials/events/providers/detailEventProvider.html",
		controller : "EditProviderEventController"
	})
	.state("eventState.calendar",{
		url : EVENT_CALENDAR_PATH,
		templateUrl : "../partials/events/calendar.html",
		controller : "CalendarEventController"
	})
	.state("eventState.scheduled",{
		url : EVENT_SCHEDULED_PATH,
		templateUrl : "../partials/events/scheduled.html",
		controller : "ScheduledEventController"
	})
	.state("eventState.budget", {
		url : EVENT_BUDGET_PATH,
		templateUrl : "../partials/events/budget.html",
		controller : "BudgetEventController"
	})
	.state("eventState.reviews", {
		url : EVENT_REVIEW_PATH,
		templateUrl : "../partials/events/reviews.html",
		controller : "ReviewsEventController"
	});;
	
	
	
} ]);


