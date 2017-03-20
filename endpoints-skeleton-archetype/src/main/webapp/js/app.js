'use strict';

/**
 * @ngdoc object
 * @name conferenceApp
 * @requires $routeProvider
 * @requires conferenceControllers
 * @requires ui.bootstrap
 *
 * @description
 * Root app, which routes and specifies the partial html and controller depending on the url requested.
 *
 */
var app = angular.module('conferenceApp', ['conferenceControllers', 'ngRoute', 'ui.bootstrap']).
    config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                
                .when('/profile', {
                    templateUrl: '/partials/profile.html',
                    controller: 'MyProfileCtrl'
                })
                .when('/', {
                    templateUrl: '/partials/viewCalendarMainAdmin.html',
                    controller: 'ViewCalendarAdminController'
                })
                
                
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
                
                .when('/admin/addClient', {
                    templateUrl: 'partials/addClientAdmin.html',
                    controller: 'AddClientController'
                })
                
                .when('/admin/editProfile', {
                    templateUrl: 'partials/adminEditProfile.html',
                    controller: 'AdminEditProfileController'
                })
                
                .when('/admin/logChanges', {
                    templateUrl: 'partials/logTrackingAdmin.html',
                    controller: 'LogController'
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
                
                .when('/admin/viewClient', {
                    templateUrl: 'partials/viewClientAdmin.html',
                    controller: 'ViewClientController'
                })
               
                .when('/admin/viewRoom', {
                    templateUrl: 'partials/viewRoomAdmin.html',
                    controller: 'ViewRoomController',
                })
                
                .when('/admin/viewService', {
                    templateUrl: 'partials/viewServiceAdmin.html',
                    controller: 'ViewServiceController'
                })
                
                .otherwise({
                    redirectTo: '/'
                });
        }]);

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
    };
    return filter;
});


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
        SCOPES: 'https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/calendar https://www.googleapis.com/auth/calendar.readonly',
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


