/**
 * 
 */
var restaurantModule = angular.module("restaurant.module");

restaurantModule.controller("addRestaurantController", function($scope,$rootScope,$location,addrestaurantService) {
	
	
	$scope.back = function() {
		$location.path("/dashboard");
	}
	

	var addRestCtrl = this;

	/*addRestCtrl.restaurant = {
		     restaurantName : "",
		     location : "",
		     cuisuine : "",
		     phoneNumber : "",
		     price : "",
		     status : ""
		  };*/
	
	addRestCtrl.save = function() {				
		
		addrestaurantService.addRestaurant(addRestCtrl,$rootScope.globals.userSession.id, callbackSuccess,callbackRestaurantError);
		
		var callbackSuccess = function(data,headers) { // Status Code:200
			$uibModal.open({
				animation : true,
				component : 'successComponent',
				resolve : {
					msg : function() {
						return 'Congratulations. Your restaurant is registered with us now.';
					}
				}
			});
	    };
	    
	    var callbackRestaurantError = function(data,headers) {
	    		$scope.message = data.message;
	    		$scope.error = true;   
	    };
	}
	
	
	
});

restaurantModule.controller("editRestaurantController", function($location,$rootScope,$routeParams,$scope, $uibModal,editrestaurantService) {
	
	
	$scope.back = function() {
		$location.path("/dashboard");
	}
	
	/*$scope.init = function () {
		editRestCtrl.restaurant = editrestaurantService.restaurantDetails(restaurantId,callbackSuccess,callbackRestaurantError);
	};*/
	
	var editRestCtrl = this;

	/*editRestCtrl.restaurant = {
		     restaurantName : "",
		     location : "",
		     cuisuine : "",
		     phoneNumber : "",
		     price : "",
		     status : ""
		  };*/
	
//	var resturantId = shareRestaurantInfo.restaurantId;
	//var restaurantId = shareRestaurantInfo.getRestaurantId();
	
	editRestCtrl.save = function() {
//$rootScope.globals.restaurant.id
		editrestaurantService.editRestaurant(editRestCtrl, $rootScope.globals.userSession.id,$routeParams.restauId, callbackSuccess,callbackRestaurantError);
		
		var callbackSuccess = function(data,headers) { $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return msgToDisplay;
				}
			}
		});
	    };
	    
	    var callbackRestaurantError = function(data,headers) {
	    		$scope.message = data.message;
	    		$scope.error = true;   
	    };
	}
	
});

restaurantModule.service('addrestaurantService', function($rootScope,$http,$timeout,APP_CONSTANT) {
	var addrestaurantService = {};
	addrestaurantService.addRestaurant =  function(data, id, callback, callbackError) {
						

		var httppost = {
				 method: 'POST',
				 url: APP_CONSTANT.REMOTE_HOST + '/restaurant/user/' + id,
				 headers: {
				   'Content-Type': 'application/json'
				 },
				 data: data.restaurant
				}
				
		$http(httppost).then(function(data){
			  alert('New restaurant added successfully.');
			  
	    });		
		
		};
		
		return addrestaurantService;
})

restaurantModule.service('editrestaurantService', function($rootScope,$http,$timeout,APP_CONSTANT) {
	var editrestaurantService = {};
	
	editrestaurantService.restaurantDetails =  function(restaurantId, callback, callbackError) {
		
		$http.get(APP_CONSTANT.REMOTE_HOST + '/restaurant/'+restaurantId)
		// On Success of $http call
		.success(function(data, status, headers, config) {
			callback(data, headers);
		}).error(function(data, status, headers, config) { 
			callbackError(data, headers);
		});
	

};
	
	editrestaurantService.editRestaurant =  function(data, id,restaurantid, callback, callbackError) {
			
		
		var httpput = {
				 method: 'PUT',
				 url: APP_CONSTANT.REMOTE_HOST + '/restaurant/user/' + id + '/update/'+restaurantid,
				 headers: {
				   'Content-Type': 'application/json'
				 },
				 data: data.restaurant
				}
		
							
		
					/*$http.put(APP_CONSTANT.REMOTE_HOST + '/restaurant/user/' + id + '/update/'+restaurantid,data)		*/			
		$http(httpput).then(function(data){
			  alert('Saved successfully.');
			  
	    });
				//}

			};
		
	return editrestaurantService;
})

	