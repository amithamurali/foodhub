/**
 * 
 */
var registrationModule = angular.module("registration.module", []);
registrationModule.controller('registrationController', function($location,
		$scope, $uibModal, registrationService) {

	var regCtrl = this;

	regCtrl.registration = {
		//title : "",
		firstName : "",
		lastName : "",
		username : "",
		password : "",
		gender : "",
		phoneNumber : "",
		email : "",
		address : "",
		//dateOfBirth : "",
		userType : ""
	};

	regCtrl.dobOptions = {
		maxDate : new Date(),
		popup : false,
		format : 'dd-MMMM-yyyy',
		altInputFormats : [ 'M!/d!/yyyy' ]
	};

	regCtrl.setUpDob = function() {
		var todayDate = new Date();
		regCtrl.dobOptions.maxDate = new Date(todayDate.getFullYear() - 18,
				todayDate.getMonth(), todayDate.getDate());
		regCtrl.registration.dateOfBirth = regCtrl.dobOptions.maxDate;
	};

	regCtrl.dateOptions = {
		dateDisabled : disabled,
		formatYear : 'yy',
		maxDate : regCtrl.dobOptions.maxDate,
		// minDate : new Date(),
		startingDay : 1
	};
	// calling today function
	regCtrl.setUpDob();

	// Disable weekend selection
	function disabled(data) {
		// var date = data.date, mode = data.mode;
		// return mode === 'day'
		// && (date.getDay() === 0 || date.getDay() === 6);
		return false;
	}

	regCtrl.open = function() {
		regCtrl.dobOptions.popup = true;
	};

	regCtrl.cancel = function() {
		$location.path('/');
	}

	regCtrl.register = function() {
		console.log(regCtrl.registration);
		registrationService.register(regCtrl.registration, callbackSuccess,
				callbackError);

	}

	regCtrl.error = false;
	regCtrl.message = "";

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		//if (data.success) {
			regCtrl.message = "Registeration Successful";
			$uibModal.open({
				animation : true,
				component : 'successComponent',
				resolve : {
					msg : function() {
						return 'Thank you for registering with us. Please log in to access the application further.';
					}
				}
			});
			//regCtrl.openComponentModal('Registration Successful');
			//$location.path('/');

		/*} else {
			regCtrl.message = data.message;
			regCtrl.error = true;
		}*/

	};

	var callbackError = function(data, headers) {
		regCtrl.message = "Sorry!! something has gone wrong.. try again later.";
		regCtrl.error = true;

	};

	regCtrl.openComponentModal = function(msgToDisplay) {
		var modalInstance = $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return msgToDisplay;
				}
			}
		});

	};

});

registrationModule.factory('registrationService', function($rootScope, $http,
		$timeout, $cookieStore, $window, APP_CONSTANT, AUTH_EVENTS) {
	var regService = {};

	regService.register = function(data, callback, callbackError) {
		$http.post(APP_CONSTANT.REMOTE_HOST + '/registration', data

			)
			// On Success of $http call
			.success(function(data, status, headers, config) {
				callback(data, headers);
			}).error(function(data, status, headers, config) { 
				callbackError(data, headers);
			});
		//}

	};
	
	return regService;

});
