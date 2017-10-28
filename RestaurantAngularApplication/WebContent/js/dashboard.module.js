/**
 * 
 */

var dashboardModule = angular.module("dashboard.module");
/*dashboardModule.controller('CarouselDemoCtrl', function ($scope) {  $scope.myInterval = 5000;
$scope.noWrapSlides = false;
$scope.active = 0;
var slides = $scope.slides = [
    {image: '.\\WebContent\\image\\restaurant-03.jpg', description: 'Image 00'},
    {image: '.\\WebContent\\image\\restaurant-carousel-1.jpg', description: 'Image 01'}
];
var currIndex = 0;

$scope.addSlide = function() {
  var newWidth = 600 + slides.length + 1;
  slides.push({
    image: '//unsplash.it/' + newWidth + '/300',
    text: ['Nice image','Awesome photograph','That is so cool','I love that'][slides.length % 4],
    id: currIndex++
  });
};

$scope.randomize = function() {
  var indexes = generateIndexesArray();
  assignNewIndexesToSlides(indexes);
};

for (var i = 0; i < 4; i++) {
  $scope.addSlide();
}

// Randomize logic below

function assignNewIndexesToSlides(indexes) {
  for (var i = 0, l = slides.length; i < l; i++) {
    slides[i].id = indexes.pop();
  }
}

function generateIndexesArray() {
  var indexes = [];
  for (var i = 0; i < currIndex; ++i) {
    indexes[i] = i;
  }
  return shuffle(indexes);
}

// http://stackoverflow.com/questions/962802#962890
function shuffle(array) {
  var tmp, current, top = array.length;

  if (top) {
    while (--top) {
      current = Math.floor(Math.random() * (top + 1));
      tmp = array[current];
      array[current] = array[top];
      array[top] = tmp;
    }
  }

  return array;
}});
*/
dashboardModule.controller('dashboardController', function($scope,$rootScope,$location,dashboardService) {
	
	$scope.messageDash = "This is Dashboard";
	
	$scope.init = function () {
		if($rootScope.globals.userSession.role == 'Owner')
		{
		$scope.restaurants = dashboardService.listOfRestaurant($rootScope.globals.userSession.id,callbackDashboardListSuccess,callbackDashboardListError);
		}
		if($rootScope.globals.userSession.role == 'Admin' || $rootScope.globals.userSession.role == 'Customer')
			{
				$scope.restaurants = dashboardService.listOfAllRestaurant(callbackDashboardListSuccess,callbackDashboardListError);
			}
	};
	
	$scope.addRestaurant = function () {
		console.log('Add Restaurant');
		$location.path('/restaurant/add');
		
	};
	$scope.viewRestaurant = function (restaurantId) {
		console.log(restaurantId);
		/* $rootScope.globals = {
					restaurant: {	id: restaurantId}
};*/
		//$scope.restId = resturantId;
		$location.path('/restaurant/view/' +restaurantId);

	};
	
	$scope.editRestaurant = function (restaurantId) {
		console.log(restaurantId);
		
		
		//$scope.restaurant= restaurantId;
		//$scope.restaurant = shareRestaurantInfo;
		//shareRestaurantInfo.setRestaurantId(restaurantId);
		/*$rootScope.globals = {
				restaurant: {	id: restaurantId}
};*/
		$location.path('/restaurant/edit/' + restaurantId);
	};

	
	var dbCtrl = this;

	dbCtrl.restaurant = {
		
			 restaurantName : "",
			   location : "",
			   cuisuine : "",
			   phoneNumber : "",
			   price : "",
			   status : ""
			
	};
	
	
	$scope.disableRestaurant = function (restaurantId) {
		console.log(restaurantId);
		
		dashboardService.disableRestaurant(dbCtrl, $rootScope.globals.userSession.id,restaurantId, callbackSuccess,callbackRestaurantError);
		
		var callbackSuccess = function(data,headers) { $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return 'This restaurant has been disabled and wont appear on the website anymore.';
				}
			}
		});
	    };
	    
	    var callbackRestaurantError = function(data,headers) {
	    		$scope.message = data.message;
	    		$scope.error = true;   
	    };
	};
	
	$scope.GenerateCharts = function () {
		console.log('Generate Charts');
		$location.path('/restaurant/charts');

	};
	
	var callbackDashboardListSuccess = function(data,headers) { // Status Code:200
		$scope.restaurants = data;
		console.log($scope.restaurants);
    };
    
    var callbackDashboardListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
	
});

/*dashboardModule.factory('shareRestaurantInfo',function(){
	
		return  { restaurantId : '5'};
})*/

dashboardModule.service('dashboardService', function($http,$timeout,APP_CONSTANT) {
	var dashboardService = {};
	
	dashboardService.listOfRestaurant =  function(id, callback, callbackError) {
		
				$http.get(APP_CONSTANT.REMOTE_HOST + '/restaurant/user/'+id)
				// On Success of $http call
				.success(function(data, status, headers, config) {
					callback(data, headers);
				}).error(function(data, status, headers, config) { 
					callbackError(data, headers);
				});
			

		};
		
		
		dashboardService.listOfAllRestaurant =  function(callback, callbackError) {
			
			$http.get(APP_CONSTANT.REMOTE_HOST + '/restaurant/guestuser')
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});
		

	};
	
	
	
	dashboardService.disableRestaurant =  function(data, id,restaurantid, callback, callbackError) {
			
		
		var httpput = {
				 method: 'PUT',
				 url: APP_CONSTANT.REMOTE_HOST + '/restaurant/user/' + id + '/disable/'+restaurantid,
				 headers: {
				   'Content-Type': 'application/json'
				 },
				 data: data.restaurant
				}
			
		$http(httpput).then(function(data){
			  alert('This restaurant has been disabled and wont appear on the website anymore.');
			  
	    });
		

			};
	
	
	return dashboardService;
})
