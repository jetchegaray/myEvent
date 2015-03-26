<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html ng-app="mieventoApp">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MiEvento</title>

<!-- ********************** Styles ********************** -->

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
<link href="../bower_components/angular-xeditable/dist/css/xeditable.css" rel="stylesheet">
<!-- <link rel="stylesheet" href="../css/animate.css"> -->
<link rel="stylesheet" href="../bower_components/angular-ui-select/dist/select.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">
<link rel="stylesheet" href="../bower_components/fullcalendar/dist/fullcalendar.css">
<link href="http://fonts.googleapis.com/css?family=Rancho" rel="stylesheet">
<link rel="stylesheet" href="../css/app.css">

<!-- ********************** Jquery ********************** -->

<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="../bower_components/jquery-ui/jquery-ui.min.js"></script>


<!-- ********************** Angularjs Components ********************** -->

<script src="../bower_components/angular/angular.js"></script>
<script src="../bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="../bower_components/angular-route/angular-route.js"></script>
<script src="../bower_components/angular-resource/angular-resource.js"></script>
<script src="../bower_components/angular-cookies/angular-cookies.js"></script>
<script src="../bower_components/angular-animate/angular-animate.js"></script>
<script src="../bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
<script src="../bower_components/angular-ui-select/dist/select.min.js"></script>
<script src="../bower_components/moment/moment.js"></script>
<script src="../bower_components/underscore/underscore.js"></script>

<script src="../bower_components/angular-ui-calendar/src/calendar.js"></script>
<script src="../bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
<script src="../bower_components/fullcalendar/dist/gcal.js"></script>
<script src="../bower_components/angular-xeditable/dist/js/xeditable.js"></script>
<script src="../bower_components/angular-dragdrop/src/angular-dragdrop.min.js"></script>
<script src="../bower_components/angular-translate/angular-translate.min.js"></script>
<script src="../bower_components/angular-translate-loader-url/angular-translate-loader-url.min.js"></script>
<!-- ********************** angular app ********************** -->

<script src="../js/app.js"></script>
<script src="../js/routes.js"></script>
<script src="../js/directives.js"></script>


<!-- ********************** controllers ********************** -->

<script src="../js/controllers/homeControllers.js"></script>
<script src="../js/controllers/userControllers.js"></script>
<script src="../js/controllers/eventControllers.js"></script>
<script src="../js/controllers/providerControllers.js"></script>
<script src="../js/controllers/guestEventControllers.js"></script>
<script src="../js/controllers/placeEventControllers.js"></script>
<script src="../js/controllers/providerEventControllers.js"></script>
<script src="../js/controllers/calendarEventControllers.js"></script>
<script src="../js/controllers/scheduledEventControllers.js"></script>
<script src="../js/controllers/budgetEventControllers.js"></script>
<script src="../js/controllers/placeAdministratorControllers.js"></script>
<script src="../js/controllers/reviewsEventControllers.js"></script>
<script src="../js/controllers/presentsEventControllers.js"></script>


<script src="../js/controllers/utilsControllers.js"></script>
<script src="../js/controllers/exceptionControllers.js"></script>
<script src="../js/controllers/languageControllers.js"></script>

<!-- ********************** contexts ********************** -->
<script src="../js/contexts/applicationContext.js"></script>
<script src="../js/contexts/userContext.js"></script>
<script src="../js/contexts/eventContext.js"></script>
<script src="../js/contexts/exceptionContext.js"></script>
<script src="../js/contexts/providerContext.js"></script>
<script src="../js/contexts/countryContext.js"></script>



<script src="../js/services.js"></script>
<script src="../js/filters.js"></script>
<script src="../js/directives.js"></script>
</head>

<body>
	
	<div class="row">
		<div id="page-top" data-spy="scroll" data-target=".navbar-default">
		
			<!-- Navigation -->
			<div class="row">
				<div class="navbar-inner">
					<div class="container">
						<a class="brand"> <img src="../img/logo.jpg"
							style="width: 200px; height: 100px;"></a>
						<div id="flags" ng-controller="LanguageController" class="pull-right">
						    <a href ng-click="changeLanguage('es')">
						        <img alt="es" title="Spanish" src="../img/flag-es.png">
						    </a>
						    <a href ng-click="changeLanguage('en')">
						        <img alt="en" title="English" src="../img/flag-us.png">
						    </a>
					 	</div> 	
					 </div>		
				</div>
			</div>
			<div class="row">
				<nav class="navbar navbar-default" role="navigation">
					<div class="container">
						<div class="navbar-header page-scroll">
							<a class="navbar-brand page-scroll" href="#page-top"><spring:message code="index.myEvent"/></a>
						</div>
		
						<!-- Collect the nav links, forms, and other content for toggling -->
						<ul class="nav navbar-nav">
							<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
							
							<li class="dropdown" dropdown><a href="#" class="dropdown-toggle" dropdown-toggle><spring:message code="index.events"/><span class="caret"></span></a>
		
								<ul class="dropdown-menu" role="menu">
									<li ng-repeat="event in $root.loggedUser.events | orderBy:+name">
										<a href ng-click="$root.selectEvent(event)">{{event.name}}</a></li>
									<li class="divider" ng-show="$root.loggedUser.events != null && $root.loggedUser.events.length"></li>
									<li><a ui-sref="eventState.eventCreate"><spring:message code="index.createNewEvent"/></a></li>
								</ul>
							</li>
							
							<li class="dropdown" dropdown><a href="#" class="dropdown-toggle" dropdown-toggle><spring:message code="index.services"/><span class="caret"></span></a>
		
								<ul class="dropdown-menu" role="menu" ng-controller="ProviderTypeController">
									<li ng-repeat="type in types | orderBy:'toString()'"><a ui-sref="providerListState({searchLocationTypeRequest : {providerType : type}})">{{type}}</a></li>
								</ul>
							</li>
							
							<li class="dropdown" dropdown><a href="#" class="dropdown-toggle" dropdown-toggle><spring:message code="index.new"/><span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#eventTypes"><spring:message code="index.news.1"/></a></li>
									<li><a href="#features"><spring:message code="index.news.2"/></a></li>
									<li class="divider"></li>
									<li class="dropdown-header"><spring:message code="index.news.next"/></li>
									<li><a href="#"><spring:message code="index.news.next.1"/></a></li>
									<li><a href="#"><spring:message code="index.news.next.2"/></a></li>
								</ul>
							</li>
						
						<li><a class="page-scroll" href="#contact"><spring:message code="index.contact"/></a>
						</li>	
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li id="nav-login-btn" ng-show="$root.loggedUser == null">
								<a ui-sref="loginState"> <spring:message code="index.login"/><span class="fa fa-user text-primary glyphiconSpace"></span></a></li>
							<li id="nav-login-btn" class="dropdown"  dropdown ng-show="$root.loggedUser != null">
								<a href="#" class="dropdown-toggle" dropdown-toggle>
									{{$root.loggedUser.email}} <i class="fa fa-user text-success glyphiconSpace"></i>
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href ng-click="$root.logout()"><spring:message code="index.logout"/></a></li>
								</ul>
							</li>
						</ul>
					</div>
					<!-- /.container -->
				</nav>
			</div>
			<div class="container" ng-controller="ExceptionController">
				<div ng-show="alert.show" class="text-center animated flipInY">
					<alert type={{alert.type}} close="closeAlert()"><i class="fa fa-exclamation-circle fa-5 glyphiconSpace"></i><strong>{{alert.msg}}</strong></alert>
				</div>
			</div>
  		</div>
  	</div>
  	
  	<div class="row" ui-view></div>
  	
  	<div class="row">
		<footer class="container">
			<div class="row omb_row-sm-offset-3 line"></div>
			<p class="pull-right">
				<a href="#"><spring:message code="index.toTheTop"/></a>
			</p>
			<p>
				&copy; 2014 JE Company, Inc. &middot; <a href="#"><spring:message code="index.privacy"/></a> &middot;
				<a href="#"><spring:message code="index.terms"/></a>
			</p>
		</footer>
	</div>
</body>

</html>
