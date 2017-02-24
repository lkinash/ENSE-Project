'use strict';

var schedulerApplication = schedulerApplication || {};

schedulerApplication.controllers = angular.module('schedulerControllers', [ 'ui.bootstrap' ]);

schedulerApplication.controllers.controller('MainController', function($scope,
		$route, $routeParams, $location, $log, oauth2Provider, HTTP_ERRORS) {

	/**
	 * Returns if the viewLocation is the currently viewed page.
	 * 
	 * @param viewLocation
	 * @returns {boolean} true if viewLocation is the currently viewed page.
	 *          Returns false otherwise.
	 */
	$scope.isActive = function(viewLocation) {
		return viewLocation === $location.path();
	};

	/**
	 * Returns the OAuth2 signedIn state.
	 * 
	 * @returns {oauth2Provider.signedIn|*} true if siendIn, false otherwise.
	 */
	$scope.getSignedInState = function() {
		return oauth2Provider.signedIn;
	};

	/**
	 * Calls the OAuth2 authentication method.
	 */
	$scope.signIn = function() {
		oauth2Provider.signIn(function() {
			gapi.client.oauth2.userinfo.get().execute(function(resp) {
				$scope.$apply(function() {
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
	 * Render the signInButton and restore the credential if it's stored in the
	 * cookie. (Just calling this to restore the credential from the stored
	 * cookie. So hiding the signInButton immediately after the rendering)
	 */
	$scope.initSignInButton = function() {
		gapi.signin.render('signInButton', {
			'callback' : function() {
				jQuery('#signInButton button').attr('disabled', 'true').css(
						'cursor', 'default');
				if (gapi.auth.getToken() && gapi.auth.getToken().access_token) {
					$scope.$apply(function() {
						oauth2Provider.signedIn = true;
					});
				}
			},
			'clientid' : oauth2Provider.CLIENT_ID,
			'cookiepolicy' : 'single_host_origin',
			'scope' : oauth2Provider.SCOPES
		});
	};

	/**
	 * Logs out the user.
	 */
	$scope.signOut = function() {
		oauth2Provider.signOut();
		$scope.alertStatus = 'success';
		$scope.rootMessages = 'Logged out';
	};

	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId()); // Do not send to your backend!
												// Use an ID token instead.
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail()); // This is null if the
														// 'email' scope is not
														// present.
	}

	function init() {
		window.init();

		gapi.client.load('admin', 'v1', null, '//' + window.location.host
				+ '/_ah/api');

	}

	/**
	 * Returns the OAuth2 signedIn state.
	 * 
	 * @returns {oauth2Provider.signedIn|*} true if siendIn, false otherwise.
	 */
	$scope.getSignedInState = function() {
		return oauth2Provider.signedIn;
	};

	/**
	 * Calls the OAuth2 authentication method.
	 */
	$scope.signIn = function() {
		oauth2Provider.signIn(function() {
			gapi.client.oauth2.userinfo.get().execute(function(resp) {
				$scope.$apply(function() {
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
	 * Render the signInButton and restore the credential if it's stored in the
	 * cookie. (Just calling this to restore the credential from the stored
	 * cookie. So hiding the signInButton immediately after the rendering)
	 */
	$scope.initSignInButton = function() {
		gapi.signin.render('signInButton', {
			'callback' : function() {
				jQuery('#signInButton button').attr('disabled', 'true').css(
						'cursor', 'default');
				if (gapi.auth.getToken() && gapi.auth.getToken().access_token) {
					$scope.$apply(function() {
						oauth2Provider.signedIn = true;
					});
				}
			},
			'clientid' : oauth2Provider.CLIENT_ID,
			'cookiepolicy' : 'single_host_origin',
			'scope' : oauth2Provider.SCOPES
		});

	};

});

schedulerApplication.controllers.controller('AddRoomController', function(
		$scope, $route, $routeParams, $location) {

	$scope.addRoom = function() {
		roomForm = {
			"number" : $scope.number
		};

		gapi.client.admin.addRoom(roomForm).execute();
	};

});

schedulerApplication.controllers.controller('ViewEmployeeController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('AddEmployeeController', function(
		$scope, $route, $routeParams, $location) {

	$scope.addEmployee = function() {
		$scope.employeeForm = {
			"email" : $scope.email,
			"name" : $scope.name,
			"password" : $scope.password
		};

		gapi.client.admin.addEmployee($scope.employeeForm).execute();
	};
});

schedulerApplication.controllers.controller('ViewServiceController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('AddServiceController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('ViewRoomController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('AddAdminController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('ForgotPasswordController',
		function($scope, $route, $routeParams, $location) {

		});

schedulerApplication.controllers.controller('ClientEditProfileController',
		function($scope, $route, $routeParams, $location) {

		});

schedulerApplication.controllers.controller('ClientLoginController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('ClientBookingController',
		function($scope, $route, $routeParams, $location) {

		});

schedulerApplication.controllers.controller(
		'ClientCancelAppointmentController', function($scope, $route,
				$routeParams, $location) {

		});

schedulerApplication.controllers.controller('ClientBookAppointmentController',
		function($scope, $route, $routeParams, $location) {

		});

schedulerApplication.controllers.controller('AdminEditProfileController',
		function($scope, $route, $routeParams, $location) {

		});

schedulerApplication.controllers.controller('ViewClientAppointmentController',
		function($scope, $route, $routeParams, $location) {

		});

schedulerApplication.controllers.controller('HomeController', function($scope,
		$route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('LoginController', function($scope,
		$route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('PendingController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('RejectController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('SignupController', function(
		$scope, $route, $routeParams, $location) {

});

schedulerApplication.controllers.controller('ViewAccountController', function(
		$scope, $route, $routeParams, $location) {

});
