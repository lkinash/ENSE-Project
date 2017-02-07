
'use strict';

/**
 *		Scheduler Application
 */

var schedulerApplication = schedulerApplication || {};


/**
 * @ngdoc module
 * @name schedulerController
 *
 * @description
 * Angular module for controllers.
 *
 */
schedulerApplication.controllers = angular.module('schedulerController', ['ui.bootstrap']);



/**
 * @ngdoc controller
 * @name RootCtrl
 *
 * @description
 * The root controller having a scope of the body element and methods used in the application wide
 * such as user authentications.
 *
 */
conferenceApp.controllers.controller('RootCtrl', function ($scope, $location, oauth2Provider) {

    /**
     * Returns if the viewLocation is the currently viewed page.
     *
     * @param viewLocation
     * @returns {boolean} true if viewLocation is the currently viewed page. Returns false otherwise.
     */
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };



});



conferenceApp.controllers.controller('AddEmployeeCtrl', function ($scope,  $location) {
	 

	    });