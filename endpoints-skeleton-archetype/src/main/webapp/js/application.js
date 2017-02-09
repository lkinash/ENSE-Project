(function(angular) {
  'use strict';

var app = angular.module('schedulerApplication', ['ngRoute']);

app.controller('MainController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
  app.controller('ViewEmployeeController', function($scope, $route, $routeParams, $location) {
	 $scope.$route = $route;
	 $scope.$location = $location;
	 $scope.$routeParams = $routeParams;
 });

 app.controller('AddEmployeeController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 app.controller('ViewServiceController', function($scope, $route, $routeParams, $location) {
	 $scope.$route = $route;
	 $scope.$location = $location;
	 $scope.$routeParams = $routeParams;
 });

 app.controller('AddServiceController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 app.controller('ViewRoomController', function($scope, $route, $routeParams, $location) {
	 $scope.$route = $route;
	 $scope.$location = $location;
	 $scope.$routeParams = $routeParams;
 });

 app.controller('AddRoomController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });

 app.controller('AddAdminController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 
 app.config(function($routeProvider, $locationProvider) {
	 $routeProvider

     .when('/admin/viewEmployee', {
         templateUrl: 'partials/viewEmployeeAdmin.html',
         controller: 'ViewEmployeeController'
     })
     
     .when('/admin/viewRoom', {
         templateUrl: 'partials/viewRoomAdmin.html',
         controller: 'ViewRoomController'
     })
     .when('/admin/viewService', {
         templateUrl: 'partials/viewServiceAdmin.html',
         controller: 'ViewServiceController'
     })
     .when('/admin/addEmployee', {
         templateUrl: 'partials/addEmployeeAdmin.html',
         controller: 'AddEmployeeController'
     })
     .when('/admin/addAdmin', {
         templateUrl: 'partials/addAdminUser.html',
         controller: 'AddAdminController'
     })
     .when('/admin/addRoom', {
         templateUrl: 'partials/addRoomAdmin.html',
         controller: 'AddRoomController'
     })
     .when('/admin/addService', {
         templateUrl: 'partials/addServiceAdmin.html',
         controller: 'AddServiceController'
     })
     
     .when('/', {
         templateUrl: 'partials/viewCalendarMainAdmin.html',
         controller: 'MainController'
     })
     .otherwise({  redirectTo: '/' });
	 
	 $locationProvider.html5Mode(true); //activate HTML5 Mode
});
 
})(window.angular);



