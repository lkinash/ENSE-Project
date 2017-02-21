(function(angular) {
  'use strict';

var app = angular.module('schedulerApplication', ['ngRoute']);

app.controller('MainController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
     
     


     function onSignIn(googleUser) {
    	  var profile = googleUser.getBasicProfile();
    	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    	  console.log('Name: ' + profile.getName());
    	  console.log('Image URL: ' + profile.getImageUrl());
    	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    	}
     
     function init() {
    	  window.init();
    	  var ROOT = '//' + window.location.host + '/_ah/api';
    	  
    	  $scope.room.number = "number";
          
    	  gapi.client.load('admin', 'v1', null, ROOT);
    	 $scope.room = {};
              $scope.loading = true;
              gapi.client.admin.getRoom("4").
                  execute(function (resp) {
                      $scope.$apply(function () {
                          $scope.loading = false;

                              //$scope.room.number = resp.result.number;

                          
                      });
                  }
              );
          
     }
     
     /**
      * Returns the OAuth2 signedIn state.
      *
      * @returns {oauth2Provider.signedIn|*} true if siendIn, false otherwise.
      */
     $scope.getSignedInState = function () {
         return oauth2Provider.signedIn;
     };

     /**
      * Calls the OAuth2 authentication method.
      */
     $scope.signIn = function () {
         oauth2Provider.signIn(function () {
             gapi.client.oauth2.userinfo.get().execute(function (resp) {
                 $scope.$apply(function () {
                     if (resp.email) {
                         oauth2Provider.signedIn = true;
                         $scope.alertStatus = 'success';
                         $scope.rootMessages = 'Logged in with ' + resp.email;
                     }
                 });
             });
         });
     };
     
     
     /**
      * Render the signInButton and restore the credential if it's stored in the cookie.
      * (Just calling this to restore the credential from the stored cookie. So hiding the signInButton immediately
      *  after the rendering)
      */
     $scope.initSignInButton = function () {
         gapi.signin.render('signInButton', {
             'callback': function () {
                 jQuery('#signInButton button').attr('disabled', 'true').css('cursor', 'default');
                 if (gapi.auth.getToken() && gapi.auth.getToken().access_token) {
                     $scope.$apply(function () {
                         oauth2Provider.signedIn = true;
                     });
                 }
             },
             'clientid': oauth2Provider.CLIENT_ID,
             'cookiepolicy': 'single_host_origin',
             'scope': oauth2Provider.SCOPES
         });
     };
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
     
     
     $scope.addEmployee = function() {
    	    $scope.employeeForm = {
    	      "email" : $scope.email,
    	      "name" : $scope.name,
    	      "password" : $scope.password
    	    };
     
    	 gapi.client.admin.addEmployee($scope.employeeForm).execute();
     };
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
     
     $scope.addEmployee = function() {
 	    $scope.roomForm = {
 	      "name" : $scope.name,
 	    };
  
 	 gapi.client.admin.addRoom($scope.roomForm).execute();
  };
  
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
 
 /**
  * @ngdoc filter
  * @name startFrom
  *
  * @description
  * A filter that extracts an array from the specific index.
  *
  */
 app.filter('startFrom', function () {
     /**
      * Extracts an array from the specific index.
      *
      * @param {Array} data
      * @param {Integer} start
      * @returns {Array|*}
      */
     var filter = function (data, start) {
         return data.slice(start);
     }
     return filter;
 });
 
 app.factory('oauth2Provider', function ($modal) {
	    var oauth2Provider = {
	        CLIENT_ID: '1032207459940-kl5tt995gsvp5j15ec0stmoastj68ame.apps.googleusercontent.com',
	        SCOPES: 'https://www.googleapis.com/auth/userinfo.email profile',
	        signedIn: false
	    };

	    /**
	     * Calls the OAuth2 authentication method.
	     */
	    oauth2Provider.signIn = function (callback) {
	        gapi.auth.signIn({
	            'clientid': oauth2Provider.CLIENT_ID,
	            'cookiepolicy': 'single_host_origin',
	            'accesstype': 'online',
	            'approveprompt': 'auto',
	            'scope': oauth2Provider.SCOPES,
	            'callback': callback
	        });
	    };

	    /**
	     * Logs out the user.
	     */
	    oauth2Provider.signOut = function () {
	        gapi.auth.signOut();
	        // Explicitly set the invalid access token in order to make the API calls fail.
	        gapi.auth.setToken({access_token: ''});
	        oauth2Provider.signedIn = false;
	    };

	    /**
	     * Shows the modal with Google+ sign in button.
	     *
	     * @returns {*|Window}
	     */
	    oauth2Provider.showLoginModal = function() {
	        var modalInstance = $modal.open({
	            templateUrl: '/partials/login.modal.html',
	            controller: 'OAuth2LoginModalCtrl'
	        });
	        return modalInstance;
	    };

	    return oauth2Provider;
	}); 
 
app.controller('OAuth2LoginModalCtrl',
		    function ($scope, $modalInstance, $rootScope, oauth2Provider) {
		        $scope.singInViaModal = function () {
		            oauth2Provider.signIn(function () {
		                gapi.client.oauth2.userinfo.get().execute(function (resp) {
		                    $scope.$root.$apply(function () {
		                        oauth2Provider.signedIn = true;
		                        $scope.$root.alertStatus = 'success';
		                        $scope.$root.rootMessages = 'Logged in with ' + resp.email;
		                    });

		                    $modalInstance.close();
		                });
		            });
		        };
		    });
 
})(window.angular);
