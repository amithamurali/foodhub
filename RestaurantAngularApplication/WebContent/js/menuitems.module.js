


var menuitemsModule = angular.module('menuitems.module',['app'])
menuitemsModule.controller('addMenuItemsController', function($location,$routeParams,
		$scope, $uibModal,addmenuItemsService) {
	
	var addmenuCtrl = this;

	addmenuCtrl.menuitem = {
		
		itemName : "",
		description : "",
		price : "",
		category : ""
			
	};

	addmenuCtrl.back = function() {
		/*+ $routeParams.restauId*/
		$location.path("#/menu/14" );
	}
	
	addmenuCtrl.save = function() {
		console.log(addmenuCtrl.menuitem);
		addmenuItemsService.addNewMenuItem($routeParams.restauId, addmenuCtrl.menuitem,callbackSuccess,callbackError);
	}
			
	
	
	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		var modalInstance = $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return "New Item added successfully.";
				}
			}
		});

		

	};

	var callbackError = function(data, headers) {
		$scope.message = data.message;
		$scope.error = true; 

	};
	

})

menuitemsModule.service('addmenuItemsService', function($http,$timeout,APP_CONSTANT) {
	var addmenuItemsService = {};

		
	addmenuItemsService.addNewMenuItem = function(rid, data, callback,callbackError){
			
			$http.post(APP_CONSTANT.REMOTE_HOST + '/menuitems/restaurant/' + rid,data)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});
		};
	
		
	return addmenuItemsService;
})