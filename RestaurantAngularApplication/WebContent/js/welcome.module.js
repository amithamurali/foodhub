/**
 * 
 */
var welcomeModule = angular.module("welcome.module");
welcomeModule.controller('welcomeController', function($scope,welcomeService) {
	
	$scope.init = function () {
		
	$scope.restaurants = welcomeService.listOfAllRestaurant(callbackWelcomeSuccess,callbackWelcomeError);	
	
	};
	
	var callbackWelcomeSuccess = function(data,headers) { // Status Code:200
		$scope.restaurants = data;
		console.log($scope.restaurants);
    };
    
    var callbackWelcomeError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
	
});
welcomeModule.service('welcomeService', function($http,$timeout,APP_CONSTANT) {
	var welcomeService = {};
		
	welcomeService.listOfAllRestaurant =  function(callback, callbackError) {
			
			$http.get(APP_CONSTANT.REMOTE_HOST + '/restaurant/guestuser')
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});
		

	};
	
	return welcomeService;
});