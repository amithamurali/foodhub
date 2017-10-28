/**
 * 
 */

var configModule = angular.module('app') // Please dont not use [], dependency 
.config(function($routeProvider) {	
//    $urlRouterProvider.otherwise('/login');
	$routeProvider
    // route for the home page
	.when('/', {
		 templateUrl : 'partial/welcome.html',
	     controller  : 'welcomeController',
	     controllerAs: 'welcomeCtrl'
	})
	.when('/login', {
		 templateUrl : 'partial/login.html',
	     controller  : 'authController',
	     controllerAs: 'authCtrl'
	})
	.when('/registration', {
		 templateUrl : 'partial/registration.html',
	     controller  : 'registrationController',
	     controllerAs: 'regCtrl'
	})
	.when('/logout', {
		redirectTo: '/'
	})
   .when('/dashboard', {
        templateUrl : 'partial/home.html',
        controller  : 'dashboardController',
        controllerAs: 'dbCtrl'
    })
    .when('/restaurant/add', {
        templateUrl : 'partial/restaurant-add.html',
        controller  : 'addRestaurantController',
        controllerAs: 'addRestCtrl'
    })
    .when('/restaurant/view/:restauId', {
        templateUrl : 'partial/menu.html',
        controller  : 'menuController'
    })
    .when('/restaurant/edit/:restauId', {
        templateUrl : 'partial/restaurant-edit.html',
        controller  : 'editRestaurantController',
        controllerAs: 'editRestCtrl'
    })
     .when('/restaurant/charts', {
        templateUrl : 'partial/reports.html',
        controller  : 'ChartController'
    })    
    .when('/menuitem/add/:restauId', {
        templateUrl : 'partial/menuitem-add.html',
        controller  : 'addMenuItemsController',
        controllerAs: 'addmenuCtrl'
    })
    .when('/menu/:restauId', {
        templateUrl : 'partial/menu.html',
        controller  : 'menuController',
        controllerAs: 'menuCtrl'
    })
    .when('/about/:restauId', {
        templateUrl : 'partial/about.html',
        controller  : 'aboutMenuController',
        controllerAs: 'aboutCtrl'
    })
     .when('/reviews/:restauId', {
        templateUrl : 'partial/reviews.html',
        controller  : 'reviewController',
        controllerAs: 'reviewCtrl'
    })
     .when('/unauthorized', {
        templateUrl : 'partial/error.html',
        controller  : 'errorController',
        controllerAs: 'errorCtrl'
    })
    .otherwise({ redirectTo: '/' });
})


.run(
    function ($rootScope, $location, $cookieStore,$window, $http,AUTH_EVENTS,APP_CONSTANT) {
    	//Management 
    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
    		console.log('Clicked on '+$location.path());
            if ( !($location.path() == '/'
            		|| $location.path() == '/registration'
            		|| $location.path() == '/login')
            		  
            		  && !$rootScope.globals.userSession) {
            		console.log('Invalid Path')
                $location.path('/');
            }else if($location.path() == '/logout'){
            		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
            }else{
            
        		console.log('Valid Path')

            }
        });
    	
	$rootScope.$on(AUTH_EVENTS.loginFailed, function(event, next){
    		console.log('Login failed');
        

    		//$scope.message = "Login failed";
    });
	
	$rootScope.$on(AUTH_EVENTS.logoutSuccess, function(event, next){
		console.log('Logout Success');
		$window.localStorage.removeItem("globals");
		$rootScope.userSession=null;
	    $rootScope.$emit("CallParentMethod", {});

		//$scope.message = "Login failed";
});
    
    $rootScope.$on(AUTH_EVENTS.loginSuccess, function(event, next){
		//$scope.message = "Login Success";
		console.log('Login success');
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
	    
	    $rootScope.$emit("CallParentMethod", {});
		$location.path('/dashboard');
    });
    
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.userSession) {
        $http.defaults.headers.common[APP_CONSTANT.AUTH_KEY] = $rootScope.globals.userSession.authKey; // jshint ignore:line
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;

    }

    

})



