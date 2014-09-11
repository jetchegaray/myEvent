<html ng-app="mieventoApp">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MiEvento</title>
<link rel="stylesheet"
	href="../bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="../css/app.css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<script src="../bower_components/angular/angular.js"></script>
<script src="../bower_components/angular-resource/angular-resource.js"></script>
<script src="../bower_components/angular-route/angular-route.js"></script>
<script src="../bower_components/angular-cookies/angular-cookies.js"></script>
<script src="../bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="../js/app.js"></script>
<script src="../js/controllers.js"></script>
<script src="../js/services.js"></script>
<script src="../js/filters.js"></script>
<script src="../js/directives.js"></script>
</head>

<body>

	<div id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">
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

					<a class="navbar-brand page-scroll" href="#page-top">Mis
						Eventos</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<ul class="nav navbar-nav">
					<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
					<li class="hidden"><a class="page-scroll" href="#page-top"></a>
					</li>
					<li><a class="page-scroll" href="#about">Eventos</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Servicios<span class="caret"></span></a>
							
							<ul class="dropdown-menu" role="menu" ng-controller="providerController">
								<li ng-repeat="type in types | orderBy:+type">
									<a href="#" ng-click="getProviderByType(type)">{{type}}</a>
								</li>
							</ul>		
					</li>
					<li><a class="page-scroll" href="#contact">Contacto</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Nuevo<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Casamientos</a></li>
							<li><a href="#">Cumplea&ntilde;os</a></li>
							<li><a href="#">Fiestas</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Proximamente</li>
							<li><a href="#">Recitales</a></li>
							<li><a href="#">Encuentros</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li id="nav-login-btn" ng-show="$root.logged_user == null"><a
						ng-href="http://localhost:8080/mievento/#/user/login"> Login<i
							class="glyphicon glyphicon-user"></i></a></li>
					<li id="nav-login-btn" ng-show="$root.logged_user != null"><a
						ng-href="http://localhost:8080/mievento/#/user/login">
							{{$root.logged_user}}<i class="glyphicon glyphicon-user"></i>
					</a></li>
				</ul>
			</div>
			<!-- /.container -->
		</nav>
		<div class="container" ng-view></div>
			<!-- FOOTER -->
		<footer class="container">
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
