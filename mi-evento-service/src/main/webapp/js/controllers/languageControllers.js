


mieventoControllers.controller('LanguageController', ['$scope','$translate','$location', 
							function($scope, $translate, $location) {
   
   
    $scope.changeLanguage = function (locale) {
        $translate.use(locale);
        $location.search('lang', locale); 
    };
}]);