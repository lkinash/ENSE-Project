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
var app = angular.module('schedulerApplication',
    ['schedulerControllers', 'ngRoute', 'ui.bootstrap']).
    config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                     .when('/admin/addAdmin', {
         templateUrl: 'partials/addAdminUser.html',
         controller: 'AddAdminController'
     })
     
     .when('/admin/addEmployee', {
         templateUrl: '/partials/addEmployeeAdmin.html',
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
