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
     .when('/admin/addEmployee', {
         templateUrl: '/partials/addEmployeeAdmin.html',
         controller: 'AddEmployeeController'
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



