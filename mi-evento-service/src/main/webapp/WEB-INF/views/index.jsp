
<!DOCTYPE html>
<html ng-app="mieventoApp">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MiEvento</title>

<!-- ********************** Styles ********************** -->
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/app.css">
<!-- <link rel="stylesheet" href="../css/animate.css"> -->
<link rel="stylesheet" href="../bower_components/angular-ui-select/dist/select.min.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.8.5/css/selectize.default.css">
<link rel="stylesheet" href="../bower_components/fullcalendar/dist/fullcalendar.css">
<!-- ********************** Jquery ********************** -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="../bower_components/jquery-ui/jquery-ui.min.js"></script>

<!-- ********************** Angularjs Components ********************** -->

<script src="../bower_components/angular/angular.js"></script>
<script src="../bower_components/angular-route/angular-route.js"></script>
<script src="../bower_components/angular-resource/angular-resource.js"></script>
<script src="../bower_components/angular-cookies/angular-cookies.js"></script>
<script src="../bower_components/angular-animate/angular-animate.js"></script>
<script src="../bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
<script src="../bower_components/angular-ui-select/dist/select.min.js"></script>
<script src="../bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="../bower_components/moment/moment.js"></script>
<script type="text/javascript" src="../bower_components/angular-ui-calendar/src/calendar.js"></script>
<script type="text/javascript" src="../bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
<script type="text/javascript" src="../bower_components/fullcalendar/dist/gcal.js"></script>

<!-- ********************** angular app ********************** -->

<script src="../js/app.js"></script>
<script src="../js/routes.js"></script>
<script src="../js/directives.js"></script>


<!-- ********************** controllers ********************** -->

<script src="../js/controllers/providerControllers.js"></script>
<script src="../js/controllers/userControllers.js"></script>
<script src="../js/controllers/eventControllers.js"></script>
<script src="../js/controllers/guestEventControllers.js"></script>
<script src="../js/controllers/placeEventControllers.js"></script>
<script src="../js/controllers/providerEventControllers.js"></script>
<script src="../js/controllers/calendarEventControllers.js"></script>
<script src="../js/controllers/utilsControllers.js"></script>
<script src="../js/controllers/exceptionControllers.js"></script>

<!-- ********************** contexts ********************** -->
<script src="../js/contexts/applicationContext.js"></script>
<script src="../js/contexts/userContext.js"></script>
<script src="../js/contexts/eventContext.js"></script>
<script src="../js/contexts/exceptionContext.js"></script>
<script src="../js/contexts/providerContext.js"></script>



<script src="../js/services.js"></script>
<script src="../js/filters.js"></script>
<script src="../js/directives.js"></script>
</head>

<body>

	<div id="page-top" data-spy="scroll" data-target=".navbar-default">
		<!-- Navigation -->
		<div class="navbar-inner">
			<div class="container">
				<a class="brand"> <img src="../img/logo.jpg"
					style="width: 200px; height: 100px;"></a>
			</div>
		</div>
		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
				<div class="navbar-header page-scroll">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<a class="navbar-brand page-scroll" href="#page-top">Mi Evento</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<ul class="nav navbar-nav">
					<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Eventos<span class="caret"></span></a>

						<ul class="dropdown-menu" role="menu">
							<li ng-repeat="event in $root.loggedUser.events | orderBy:+name">
								<a href ng-click="$root.selectEvent(event)">{{event.name}}</a></li>
							<li class="divider" ng-show="$root.loggedUser.events != null && $root.loggedUser.events.length"></li>
							<li><a ui-sref="eventState.events">Crear Nuevo Evento</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Servicios<span class="caret"></span></a>

						<ul class="dropdown-menu" role="menu" ng-controller="providerTypeController">
							<li ng-repeat="type in types | orderBy:'toString()'"><a ui-sref="providerListState({providerType : type})">{{type}}</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Nuevo<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#eventTypes">Que Eventos puedo crear ?</a></li>
							<li><a href="#features">Como organizar mis eventos ?</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Proximamente</li>
							<li><a href="#">Recitales</a></li>
							<li><a href="#">Encuentros</a></li>
						</ul>
					</li>
				
				<li><a class="page-scroll" href="#contact">Contacto</a>
				</li>	
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li id="nav-login-btn" ng-show="$root.loggedUser == null">
						<a ui-sref="loginState"> Login<span class="fa fa-user text-primary glyphiconSpace"></span></a></li>
					<li id="nav-login-btn" class="dropdown" ng-show="$root.loggedUser != null">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							{{$root.loggedUser.email}} <i class="fa fa-user text-success glyphiconSpace"></i>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href ng-click="$root.logout()">logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.container -->
		</nav>
		<div class="container" ng-controller="exceptionController">
			<div ng-show="alert.show" class="text-center animated flipInY">
				<alert type="{{alert.type}}" close="closeAlert()"><i class="fa fa-exclamation-circle fa-5 glyphiconSpace"></i><strong>{{alert.msg}}</strong></alert>
			</div>
		</div>
		<div class="container" ui-view></div>
		<!-- FOOTER -->
		<footer class="container">
			<div class="row omb_row-sm-offset-3 line"></div>
			<p class="pull-right">
				<a href="#">Back to top</a>
			</p>
			<p>
				&copy; 2014 Company, Inc. &middot; <a href="#">Privacy</a> &middot;
				<a href="#">Terms</a>
			</p>
		</footer>
	</div>
	
	
</body>

</html>
