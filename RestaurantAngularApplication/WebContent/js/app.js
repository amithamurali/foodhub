/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);
 angular.module("base.module",[]);
 angular.module("welcome.module",[]);
 angular.module("registration.module",[]);
 angular.module("dashboard.module", []);
 angular.module("restaurant.module", []);
 angular.module("charts.module", []);
angular.module("menuitems.module",[]);


 
 
 angular
 .module('appCoreModule', [
	 'ngRoute',
     'ngCookies',
     'ui.bootstrap'
 ]);
//Step 2: Register App
angular.module("app", 	
			['appCoreModule',
			'welcome.module',
			'registration.module',
			'authModule',
			'dashboard.module',
			'base.module',
			'restaurant.module',
			'charts.module',
			//'ui.chart',
			'menuitems.module'
		 ]);



