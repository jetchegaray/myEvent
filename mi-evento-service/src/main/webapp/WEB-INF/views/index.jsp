<html ng-app="mieventoApp">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MiEvento</title>

<!-- ********************** Styles ********************** -->
<link rel="stylesheet"
	href="../bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="../css/app.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">

<!-- ********************** Jquery ********************** -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- ********************** Angularjs Components ********************** -->

<script src="../bower_components/angular/angular.js"></script>
<script src="../bower_components/angular-resource/angular-resource.js"></script>
<script src="../bower_components/angular-route/angular-route.js"></script>
<script src="../bower_components/angular-cookies/angular-cookies.js"></script>
<script src="../bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="../bower_components/angular-animate/angular-animate.js"></script>
<script src="../bower_components/angular-strap/dist/angular-strap.min.js"></script> 
<script src="../bower_components/angular-strap/dist/angular-strap.tpl.min.js"></script>
<script src="../bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>

<!-- ********************** angular app ********************** -->

<script src="../js/app.js"></script>
<script src="../js/routes.js"></script>
<script src="../js/directives.js"></script>


<!-- ********************** controllers ********************** -->

<script src="../js/controllers/providerControllers.js"></script>
<script src="../js/controllers/userControllers.js"></script>
<script src="../js/controllers/eventControllers.js"></script>


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
					
					</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Eventos<span class="caret"></span></a>

						<ul class="dropdown-menu" role="menu">
							<li ng-repeat="event in $root.logged_user.events | orderBy:+name">
								<a href ng-click="$root.selectEvent(event)">{{event.name}}</a></li>
							<li class="divider" ng-show="$root.logged_user.events != null && $root.logged_user.events.length"></li>
							<li><a ui-sref="eventState.create">Crear Nuevo Evento</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Servicios<span class="caret"></span></a>

						<ul class="dropdown-menu" role="menu"
							ng-controller="providerTypeController">
							<li ng-repeat="type in types | orderBy:+type"><a href ng-click="toProviderByType(type)">{{type}}</a></li>
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
						</ul></li>
				
				<li><a class="page-scroll" href="#contact">Contacto</a>
				</li>	
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li id="nav-login-btn" ng-show="$root.logged_user == null"><a
						ui-sref="loginState"> Login<span class="glyphicon glyphicon-user text-primary"></span></a></li>
					<li id="nav-login-btn" ng-show="$root.logged_user != null"><a
						ui-sref="loginState">
							{{$root.logged_user.email}} <i class="glyphicon glyphicon-user text-success"></i>
					</a></li>
				</ul>
			</div>
			<!-- /.container -->
		</nav>
		<div class="container">
			<div ng-show="$root.alert.show" >
				<alert type="$root.alert.type" close="$root.closeAlert()">{{$root.alert.msg}}</alert>
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
