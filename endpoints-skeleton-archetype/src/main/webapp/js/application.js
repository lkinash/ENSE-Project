
  'use strict';

var app = angular.module('conferenceApp', ['schedulerControllers', 'ngRoute', 'ui.bootstrap']).
config(['$routeProvider',
        function ($routeProvider) {
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
         templateUrl: 'partials/viewCalendarMainAdmin.html'
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
 

/**
 * @ngdoc service
 * @name oauth2Provider
 *
 * @description
 * Service that holds the OAuth2 information shared across all the pages.
 *
 */
app.factory('oauth2Provider', function ($modal) {
    var oauth2Provider = {
        CLIENT_ID: '573107621545-m9kr231t053m9sprflhffl349vcqrcb6.apps.googleusercontent.com',
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
        gapi.auth.setToken({access_token: ''})
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

