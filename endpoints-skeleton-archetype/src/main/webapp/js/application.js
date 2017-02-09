(function(angular) {
  'use strict';

angular.module('schedulerApplication', ['ngRoute'])

.controller('MainController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 })
 
  .controller('ViewEmployeeController', function($scope, $routeParams) {
     $scope.name = 'ViewEmployeeController';
     $scope.params = $routeParams;
 })

 .controller('AddEmployeeController', function($scope, $routeParams) {
     $scope.name = 'AddEmployeeController';
     $scope.params = $routeParams;
 })
 
 .config(	function($routeProvider, $locationProvider) {
	 $routeProvider

     .when('/admin/viewEmployee', {
         templateUrl: '/partials/viewEmployeeAdmin.html',
         controller: 'ViewEmployeeController'
     })
     .when('/admin/viewCalendar', {
         templateUrl: '/partials/viewCalendarMainAdmin.html',
         controller: 'ViewCalendarMainController'
     })
     .when('/admin/viewRoom', {
         templateUrl: '/partials/viewRoomAdmin.html',
         controller: 'ViewRoomController'
     })
     .when('/admin/viewService', {
         templateUrl: '/partials/viewServiceAdmin.html',
         controller: 'ViewServiceController'
     })
     .when('/admin/addEmployee', {
         templateUrl: '/partials/addEmployeeAdmin.html',
         controller: 'AddEmployeeController'
     })
     .when('/admin/addAdmin', {
         templateUrl: '/partials/addAdminUser.html',
         controller: 'AddAdminController'
     })
     .when('/admin/addRoom', {
         templateUrl: '/partials/addRoomAdmin.html',
         controller: 'AddRoomController'
     })
     .when('/admin/addService', {
         templateUrl: '/partials/addServiceAdmin.html',
         controller: 'AddServiceController'
     })
     .when('/', {
         templateUrl: '/partials/homeIndex.html'
     })
     .otherwise({
         redirectTo: '/'
     });

  // configure html5 to get links working on jsfiddle
  $locationProvider.html5Mode(true);
});
 
})(window.angular);



