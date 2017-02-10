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

 app.controller('ForgotPasswordController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('ClientEditProfileController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('ClientLoginController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('ClientBookingController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('ClientCancelAppointmentController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('ClientBookAppointmentController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('AdminEditProfileController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
  app.controller('ViewClientAppointmentController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
      
  app.controller('HomeController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
   app.controller('LoginController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
   app.controller('PendingController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  }); 
  
  app.controller('RejectController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });
  
   app.controller('SignupController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });     
  
   app.controller('ViewAccountController', function($scope, $route, $routeParams, $location) {
      $scope.$route = $route;
      $scope.$location = $location;
      $scope.$routeParams = $routeParams;
  });



 
 app.config(function($routeProvider, $locationProvider) {
	 $routeProvider

	 
     .when('/admin/addAdmin', {
         templateUrl: 'partials/addAdminUser.html',
         controller: 'AddAdminController'
     })
     
     .when('/admin/addEmployee', {
         templateUrl: 'partials/addEmployeeAdmin.html',
         controller: 'AddEmployeeController'
     })

     .when('/admin/addRoom', {
         templateUrl: 'partials/addRoomAdmin.html',
         controller: 'AddRoomController'
     })
     
     .when('/admin/addService', {
         templateUrl: 'partials/addServiceAdmin.html',
         controller: 'AddServiceController'
     })
     
     .when('/admin/editProfile', {
         templateUrl: 'partials/adminEditProfile.html',
         controller: 'AdminEditProfileController'
     })
     
     .when('/client/bookAppointment', {
         templateUrl: 'partials/bookAppointment.html',
         controller: 'ClientBookAppointmentController'
     })
     
     .when('/client/cancelAppointment', {
         templateUrl: 'partials/cancelAppointment.html',
         controller: 'ClientCancelAppointmentController'
     })
     
     .when('/client/editBooking', {
         templateUrl: 'partials/editBooking.html',
         controller: 'ClientBookingController'
     })
     
     .when('/client/login', {
         templateUrl: 'partials/clientLogin.html',
         controller: 'ClientLoginController'
     })
     
     .when('/client/editProfile', {
         templateUrl: 'partials/editProfile.html',
         controller: 'ClientEditProfileController'
     })
     
     .when('/forgotpassword', {
         templateUrl: 'partials/forgotPassword.html',
         controller: 'ForgotPasswordController'
     })
     
     .when('/home', {
         templateUrl: 'partials/homeIndex.html',
         controller: 'HomeController'
     })
     
     .when('/login', {
         templateUrl: 'partials/Login.html',
         controller: 'LoginController'
     })
     
     .when('/admin/pending', {
         templateUrl: 'partials/pendingAppAdmin.html',
         controller: 'PendingController'
     })    
     
     .when('/admin/reject', {
         templateUrl: 'partials/rejectAppAdmin.html',
         controller: 'RejectController'
     })
    
     .when('/signup', {
         templateUrl: 'partials/signUp.html',
         controller: 'SignupController'
     })
    
     .when('/admin/viewAccount', {
         templateUrl: 'partials/viewAdminAccount.html',
         controller: 'ViewAccountController'
     })
     
     .when('/client/viewAppointment', {
         templateUrl: 'partials/viewClientAppointment.html',
         controller: 'ViewClientAppointmentController'
     })
     
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
     
     .when('/', {
         templateUrl: 'partials/viewCalendarMainAdmin.html',
         controller: 'MainController'
     })
     .otherwise({  redirectTo: '/' });
	 
	 $locationProvider.html5Mode(true); //activate HTML5 Mode
});
 
})(window.angular);
