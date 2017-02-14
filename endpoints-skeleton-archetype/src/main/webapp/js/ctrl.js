'use strict';

var schedulerApplication = schedulerApplication || {};

schedulerApplication.controllers = angular.module('schedulerControllers', ['ui.bootstrap']);
/*
'use strict';

var schedulerApplication = schedulerApplication || {};

schedulerApplication.controllers = angular.module('schedulerControllers', ['ui.bootstrap']);

	          
schedulerApplication.controllers.controller('MainController', function($scope, $route, $routeParams, $location) {
    $scope.$route = $route;
    $scope.$location = $location;
    $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('ViewEmployeeController', function($scope, $route, $routeParams, $location) {
	 $scope.$route = $route;
	 $scope.$location = $location;
	 $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('AddEmployeeController', function($scope, $route, $routeParams, $location) {
    $scope.$route = $route;
    $scope.$location = $location;
    $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('ViewServiceController', function($scope, $route, $routeParams, $location) {
	 $scope.$route = $route;
	 $scope.$location = $location;
	 $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('AddServiceController', function($scope, $route, $routeParams, $location) {
    $scope.$route = $route;
    $scope.$location = $location;
    $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('ViewRoomController', function($scope, $route, $routeParams, $location) {
	 $scope.$route = $route;
	 $scope.$location = $location;
	 $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('AddRoomController', function($scope, $route, $routeParams, $location) {
    $scope.$route = $route;
    $scope.$location = $location;
    $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('AddAdminController', function($scope, $route, $routeParams, $location) {
    $scope.$route = $route;
    $scope.$location = $location;
    $scope.$routeParams = $routeParams;
});

schedulerApplication.controllers.controller('ForgotPasswordController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('ClientEditProfileController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('ClientLoginController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('ClientBookingController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('ClientCancelAppointmentController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('ClientBookAppointmentController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('AdminEditProfileController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 schedulerApplication.controllers.controller('ViewClientAppointmentController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
     
 schedulerApplication.controllers.controller('HomeController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
  schedulerApplication.controllers.controller('LoginController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
  schedulerApplication.controllers.controller('PendingController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 }); 
 
 schedulerApplication.controllers.controller('RejectController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
  schedulerApplication.controllers.controller('SignupController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });     
 
  schedulerApplication.controllers.controller('ViewAccountController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
 });
 
 
 */
/**
 * @ngdoc controller
 * @name addRoomCtrl
 *
 * @description
 * A controller used for the AddRoomAdmin page.
 */
schedulerApplication.controllers.controller('addRoomCtrl',
    function ($scope, $log, oauth2Provider, HTTP_ERRORS) {

        /**
         * The Room object being edited in the page.
         * @type {{}|*}
         */
        $scope.room = $scope.room || {};


        /**
         * Invokes the admin.addRoom API.
         *
         * @param conferenceForm the form object.
         */
        $scope.addRoom = function (RoomForm) {
          /*  if (!$scope.isValidConference(RoomForm)) {
                return;
            }
*/
            $scope.loading = true;
            gapi.client.admin.addRoom($scope.room).
                execute(function (resp) {
                    $scope.$apply(function () {
                        $scope.loading = false;
                        if (resp.error) {
                            // The request has failed.
                            var errorMessage = resp.error.message || '';
                            $scope.messages = 'Failed to add room : ' + errorMessage;
                            $scope.alertStatus = 'warning';
                            $log.error($scope.messages + ' room : ' + JSON.stringify($scope.Room));

                            if (resp.code && resp.code == HTTP_ERRORS.UNAUTHORIZED) {
                                oauth2Provider.showLoginModal();
                                return;
                            }
                        } else {
                            // The request has succeeded.
                            $scope.messages = 'The Room has been created : ' + resp.result.name;
                            $scope.alertStatus = 'success';
                            $scope.submitted = false;
                            $scope.Room = {};
                            $log.info($scope.messages + ' : ' + JSON.stringify(resp.result));
                        }
                    });
                });
        };
    });

	        
