'use strict';

/**
 *		Scheduler Application
 */


var app = angular.module('schedulerApplication',
	    ['schedulerController', 'ngRoute', 'ui.bootstrap']).
	    config(['$routeProvider',
	        function ($routeProvider) {
	            $routeProvider.
	                when('/admin/addEmployee', {
	                    templateUrl: '/partials/addEmployeeAdmin.html',
	                    controller: 'AddEmployeeCtrl'
	                }).
	                when('/', {
	                    templateUrl: '/partials/login.html'
	                }).
	                otherwise({
	                    redirectTo: '/'
	                });
	        }]);


/**
 * @ngdoc constant
 * @name HTTP_ERRORS
 *
 * @description
 * Holds the constants that represent HTTP error codes.
 *
 */
app.constant('HTTP_ERRORS', {
    'UNAUTHORIZED': 401
});



