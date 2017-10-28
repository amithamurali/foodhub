/**
 * 
 */

var baseModule = angular.module("base.module",[]);

baseModule.controller('aboutMenuController', function($scope,$routeParams,$uibModal,$rootScope,restaurantService) {
		
	$scope.restID = $routeParams.restauId;
	$scope.init = function () {
		$scope.restaurantDetails = restaurantService.restaurantDetails($routeParams.restauId,callbackDashboardListSuccess,callbackDashboardListError);
		
	};
	
	var callbackDashboardListSuccess = function(data,headers) { // Status Code:200
		$scope.restaurantDetails = data;
		console.log($scope.restaurantDetails);
    };
    
    var callbackDashboardListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
});

baseModule.controller('menuController', function($location,$routeParams, $route,$scope,$rootScope,menuItemsService) {
	$scope.message = "This is menu";
	//var restaurantId = $routeParams.userid;
	
	$scope.addMenuItem = function () {
		console.log('Add Menu Item');	
		$location.path('/menuitem/add/' + $routeParams.restauId);
		
	};
	
	$scope.restID = $routeParams.restauId;
	
	var menuCtrl = this;

	menuCtrl.menuitem = {
		
				  "itemName" : "roti",
				  "description": "veg",
				  "price":"expensive",
				  "category" : "veg"
				};
	
	$scope.deleteMenuItem = function (menuItemId) {
		console.log('Delete Menu Item');	
		menuItemsService.deleteMenuItem($routeParams.restauId,menuItemId, menuCtrl,callbackSuccess,callbackError);
		// $route.reload();
	};
	
	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		var modalInstance = $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return "Item has been deleted.";
				}
			}
		});

		

	};

	var callbackError = function(data, headers) {
		$scope.message = data.message;
		$scope.error = true; 

	};

	
	$scope.init = function () {
		$scope.menuItems = menuItemsService.menuItemsList($routeParams.restauId,callbackMenuItemsSuccess,callbackMenuItemsError);
		
	};
	
	
	
	var callbackMenuItemsSuccess = function(data,headers) { // Status Code:200
		$scope.menuItems = data;
		console.log($scope.menuItems);
    };
    
    var callbackMenuItemsError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };

});

baseModule.controller('reviewController', function($scope,$routeParams,$rootScope,$uibModal,reviewService) {
	$scope.message = "Customer reviews";
	$scope.restID = $routeParams.restauId;
	//var newmessage = $scope.newreply;
	
	$scope.init = function () {
		$scope.reviewsandreplies = reviewService.reviews($routeParams.restauId,callbackReviewsSuccess,callbackReviewsError);
	};
	
	$scope.addReview = function () {
		console.log('Add Review');
		reviewService.addNewReview($routeParams.restauId,$rootScope.globals.userSession.id,$scope.newreview, callbackSuccess,callbackError);
			
		//$location.path('/review/add');
		
	};
	
	
	var reviewCtrl = this;

	reviewCtrl.reply = {
		
			comments : "thank you for the review."
				};
	
	reviewCtrl.addReply = function (reviewId) {
		console.log(reviewId);
		reviewService.addNewReply($routeParams.restauId,$rootScope.globals.userSession.id,reviewId, reviewCtrl.reply, callbackSuccess,callbackError);
		//$location.path('/reply/add');

	};

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		var modalInstance = $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return "Thank you for your Review.";
				}
			}
		});

		

	};

	var callbackError = function(data, headers) {
		$scope.message = data.message;
		$scope.error = true; 

	};
	var callbackReviewsSuccess = function(data,headers) { // Status Code:200
		$scope.reviewsandreplies = data;
		console.log($scope.reviewsandreplies);
    };
    
    var callbackReviewsError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };

    });

baseModule.service('restaurantService', function($http,$timeout,APP_CONSTANT) {
	var restaurantService = {};
	
	restaurantService.restaurantDetails =  function(restaurantId, callback, callbackError) {
		
				$http.get(APP_CONSTANT.REMOTE_HOST + '/restaurant/'+restaurantId)
				// On Success of $http call
				.success(function(data, status, headers, config) {
					callback(data, headers);
				}).error(function(data, status, headers, config) { 
					callbackError(data, headers);
				});
			

		};
		
	
	return restaurantService;
});

baseModule.service('menuItemsService', function($http,$timeout,APP_CONSTANT) {
	var menuItemsService = {};
	
	menuItemsService.menuItemsList =  function(id, callback, callbackError) {
		
				$http.get(APP_CONSTANT.REMOTE_HOST + '/menuitems/restaurant/'+id)
				// On Success of $http call
				.success(function(data, status, headers, config) {
					callback(data, headers);
				}).error(function(data, status, headers, config) { 
					callbackError(data, headers);
				});
			

		};	
		
menuItemsService.deleteMenuItem = function(rid,menuItemId, data, callback,callbackError){
			
	var httpdelete = {
			 method: 'DELETE',
			 url: APP_CONSTANT.REMOTE_HOST + '/menuitems/restaurant/' + rid + '/delete/' + menuItemId,
			 headers: {
			   'Content-Type': 'application/json'
			 },
			 data: data.menuitem
			}
			
	$http(httpdelete).then(function(data){
		  alert('Menu Item deleted.');
		 		  
   });
	
			/*$http.delete(APP_CONSTANT.REMOTE_HOST + '/menuitems/restaurant/' + rid + '/delete/' + menuItemId)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});*/
		};
	
	return menuItemsService;
});

baseModule.service('reviewService', function($http,$timeout,APP_CONSTANT) {
	var reviewService = {};
	
	reviewService.reviews =  function(id, callback, callbackError) {
		
				$http.get(APP_CONSTANT.REMOTE_HOST + '/reviewreply/'+id)
				// On Success of $http call
				.success(function(data, status, headers, config) {
					callback(data, headers);
				}).error(function(data, status, headers, config) { 
					callbackError(data, headers);
				});
			

		};
		
		reviewService.addNewReview = function(rid,uid,comments, callback,callbackError){
			
			$http.post(APP_CONSTANT.REMOTE_HOST + '/review/user/'+ uid+'/restaurant/' + rid,{
				"comments": comments
			})
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});
		};
		
	reviewService.addNewReply = function(rid,uid,reviewid,data, callback,callbackError){
			
			$http.post(APP_CONSTANT.REMOTE_HOST + '/reviewreply/user/'+ uid+'/restaurant/' + rid + '/review/' + reviewid,data)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});
		
		/*var httppost = {
				 method: 'POST',
				 url: APP_CONSTANT.REMOTE_HOST + '/reviewreply/user/' + uid + '/restaurant/'+rid + '/review/' + reviewid,
				 headers: {
				   'Content-Type': 'application/json'
				 },
				 data: data.comments
				}
				
		$http(httppost).then(function(data){
			  alert('reply added successfully.');
			  
	    });*/
		};
	return reviewService;
});


