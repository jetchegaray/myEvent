<html ng-app="mieventoApp">
	<head>
	  	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="description" content="">
    	<meta name="author" content="">
		<title>MiEvento</title>
		<link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.css">
		<link rel="stylesheet" href="../css/app.css">
		<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
  		<script src="../bower_components/angular/angular.js"></script>
		<script src="../bower_components/angular-resource/angular-resource.js"></script>
		<script src="../bower_components/angular-route/angular-route.js"></script>
		<script src="../js/bootstrapApp.js"></script>
		<script src="../js/app.js"></script>
		<script src="../js/controllers.js"></script>
		<script src="../js/services.js"></script>
		<script src="../js/filters.js"></script>
		<script src="../js/directives.js"></script>
	</head>
 		
	<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Start Bootstrap</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a class="page-scroll" href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#contact">Contact</a>
                    </li>
                </ul>
	            <ul class="nav navbar-nav navbar-right">
	            	<li id="nav-login-btn" class=""><a ng-href="http://localhost:8080/mievento/#/user/login">
	            		<i class="glyphicon glyphicon-user"></i>Login</a>
	            	</li>
         		</ul>
            </div>
         <!-- /.container -->
    </nav>
    <div class="container" ng-view></div> 
</body>
</html>
