'use strict';

/**
 * The root conferenceApp module.
 *
 * @type {conferenceApp|*|{}}
 */
var conferenceApp = conferenceApp || {};

/**
 * @ngdoc module
 * @name conferenceControllers
 *
 * @description
 * Angular module for controllers.
 *
 */
conferenceApp.controllers = angular.module('conferenceControllers', ['ui.bootstrap']);

/**
 * @ngdoc controller
 * @name MyProfileCtrl
 *
 * @description
 * A controller used for the My Profile page.
 */
conferenceApp.controllers.controller('MyProfileCtrl',
    function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
        $scope.submitted = false;
        $scope.loading = false;

        /**
         * The initial profile retrieved from the server to know the dirty state.
         * @type {{}}
         */
        $scope.initialProfile = {};

        /**
         * Candidates for the teeShirtSize select box.
         * @type {string[]}
         */
        $scope.teeShirtSizes = [
            'XS',
            'S',
            'M',
            'L',
            'XL',
            'XXL',
            'XXXL'
        ];

        /**
         * Initializes the My profile page.
         * Update the profile if the user's profile has been stored.
         */
        $scope.init = function () {
            var retrieveProfileCallback = function () {
                $scope.room = {};
                $scope.loading = true;
                gapi.client.scheduler.getRoom(1).
                    execute(function (resp) {
                        $scope.$apply(function () {
                            $scope.loading = false;
                            if (resp.error) {
                                // Failed to get a user profile.
                            } else {
                                // Succeeded to get the user profile.
                                $scope.room.number = resp.result.number;
                                $scope.initialProfile = resp.result;
                            }
                        });
                    }
                );
            };
            if (!oauth2Provider.signedIn) {
                var modalInstance = oauth2Provider.showLoginModal();
                modalInstance.result.then(retrieveProfileCallback);
            } else {
                retrieveProfileCallback();
            }
        };

        /**
         * Invokes the conference.saveProfile API.
         *
         */
        $scope.saveProfile = function () {
            $scope.submitted = true;
            $scope.loading = true;
            gapi.client.conference.saveProfile($scope.profile).
                execute(function (resp) {
                    $scope.$apply(function () {
                        $scope.loading = false;
                        if (resp.error) {
                            // The request has failed.
                            var errorMessage = resp.error.message || '';
                            $scope.messages = 'Failed to update a profile : ' + errorMessage;
                            $scope.alertStatus = 'warning';
                            $log.error($scope.messages + 'Profile : ' + JSON.stringify($scope.profile));

                            if (resp.code && resp.code == HTTP_ERRORS.UNAUTHORIZED) {
                                oauth2Provider.showLoginModal();
                                return;
                            }
                        } else {
                            // The request has succeeded.
                            $scope.messages = 'The profile has been updated';
                            $scope.alertStatus = 'success';
                            $scope.submitted = false;
                            $scope.initialProfile = {
                                displayName: $scope.profile.displayName,
                                teeShirtSize: $scope.profile.teeShirtSize
                            };

                            $log.info($scope.messages + JSON.stringify(resp.result));
                        }
                    });
                });
        };
    });



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

    /**
     * Logs out the user.
     */
    $scope.signOut = function () {
        oauth2Provider.signOut();
        $scope.alertStatus = 'success';
        $scope.rootMessages = 'Logged out';
    };

    /**
     * Collapses the navbar on mobile devices.
     */
    $scope.collapseNavbar = function () {
        angular.element(document.querySelector('.navbar-collapse')).removeClass('in');
    };

});


/**
 * @ngdoc controller
 * @name OAuth2LoginModalCtrl
 *
 * @description
 * The controller for the modal dialog that is shown when an user needs to login to achive some functions.
 *
 */
conferenceApp.controllers.controller('OAuth2LoginModalCtrl',
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
 * @ngdoc controller
 * @name DatepickerCtrl
 *
 * @description
 * A controller that holds properties for a datepicker.
 */
conferenceApp.controllers.controller('DatepickerCtrl', function ($scope) {
    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    // Disable weekend selection
    $scope.disabled = function (date, mode) {
        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function () {
        $scope.minDate = ( $scope.minDate ) ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };

    $scope.dateOptions = {
        'year-format': "'yy'",
        'starting-day': 1
    };

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'shortDate'];
    $scope.format = $scope.formats[0];
});





conferenceApp.controllers.controller('AddRoomController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	console.log("reached AddRoom controller");
	var roomForm={
			"number":11,
			"services":[{
				
			}]
	};
		/*
	$scope.serviceTypes= [{
		Id: 1,
        Name: 'Laser'
    }, {
        Id: 2,
        Name: 'Waxing'       	
			
	}];
	*/
	 $scope.init = function(){
		 console.log("Reached Init function: populating list types drop down");
		 gapi.client.scheduler.admin.getAllTypes().execute(function(resp){
			 $scope.listtypes=resp.result.items;
		 });
	 };
	 
	 $scope.getListServices = function(value){
		 console.log("Reacher getlistservics function: populating service dropdown");
	      var typeId= value;
		  roomForm={
				  "number":parseInt(value)
		  };
	        
	        console.log("The typeId from the first dropdown in value variable is"+ value);
	     	gapi.client.scheduler.admin.getAllServices().execute(function(resp){
	     		console.log("Getting list of services ");
				 $scope.listservices=resp.result.items;
				 $scope.$apply();
			 });
	     	console.log("The typeId is"+ roomForm.number);
	     	gapi.client.scheduler.admin.getServiceOfType(roomForm).execute(function(resp){
	     		console.log("getting specific type function");
	     		var testList=resp.items;
	     		//console.log(testList);
	     		
	     	});
	    };
	
	 $scope.choices = [];
	 $scope.services=[];
	 var convertedservice= [];
	 
	  $scope.addServiceList=function(value){
		  var flag=0;
		  console.log("got to addServiceList function value= "+ value);
		  for(var i=0; i< $scope.services.length;i++){
			  if(value==$scope.services[i].id){
				  flag=1;
			  }
		  }
		  if(flag==0){
				  $scope.services.push({'id':value});  
		  }else{
			  console.log("Service was not added as you are unable to add duplicate services IDs");
		  }
	
		  console.log("add the service to services");
	  };
	  $scope.addNewChoice = function() {
	    var newItemNo = $scope.choices.length;
	   $scope.choices.push({'itemNo':newItemNo});
	  };
	    
	  $scope.removeChoice = function(index) {
	    $scope.choices.splice(index,1);
	    $scope.services.splice(index,1);
	  };
	 
	  /*
	  $scope.convertService=function(){
		  
		 for(var i=0; i<$scope.services.length;i++){
			 console.log($scope.services[i].id);
			 convertedservice[i]=$scope.services[i].id;
		 };
		  	console.log(convertedservice);
	  };
	  */
	  $scope.addRoom = function() {
		  
		  for(var i=0; i<$scope.services.length;i++){
				 console.log($scope.services[i].id);
				 convertedservice[i]=parseInt($scope.services[i].id);
			 };
			  	console.log(convertedservice);
			  	
	     roomForm = {
	      "number" : parseInt($scope.number),
	      "serviceIds":convertedservice
	    };
	    console.log("room form object created");
	    console.log("The room number saved in the roomForm Object is:" +roomForm.number);
	    console.log("The services saved in the roomForm Object is:" +roomForm.serviceIds);
	 //gapi.client.scheduler.addRoom(roomForm).execute();
	 
	 $scope.number=9999999;
	 gapi.client.scheduler.admin.addRoom(roomForm).execute();
  };
 
});

 conferenceApp.controllers.controller('ViewEmployeeController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {

	 console.log("controller reached for viewEmployeeAdmin");

	 $scope.init = function(){
		 gapi.client.scheduler.admin.getAllEmployees().execute(function(resp){
			 $scope.employees=resp.result.items;
			 $scope.$apply();
		 });
		  
	 };
	 
 });

 conferenceApp.controllers.controller('AddEmployeeController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	 console.log("Reached AddEmployeeController");
	 $scope.addEmployee = function() {
		  
		var employeeForm={
				"name":$scope.name,
		};
	    console.log("Employee form object created");
	    console.log("The employee name saved in the emloyeeForm Object is:" +employeeForm.name);
	   // console.log("The services saved in the roomForm Object is:" +roomForm.serviceIds);
	 //gapi.client.scheduler.addRoom(roomForm).execute();
	 
	 $scope.name="ADD IT";
	 gapi.client.scheduler.admin.addEmployee(employeeForm).execute();
	 console.log("Added the employee form now");
 };
     
  });
 
 conferenceApp.controllers.controller('ViewServiceController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	 
 });

 conferenceApp.controllers.controller('AddServiceController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	 console.log("Reached the AddServiceController");
	 $scope.init = function(){
		 console.log("Reached Init function: populating list types drop down");
		 gapi.client.scheduler.admin.getAllTypes().execute(function(resp){
			 $scope.listtypes=resp.result.items;
			 $scope.$apply();
		 });
	 };
	 
	 $scope.addService1=function(){
		 var serviceForm=[];
	 };

	 
 });
 
 conferenceApp.controllers.controller('ViewRoomController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	 console.log("controller reached for viewRoomAdmin");
	 $scope.init = function(){
		 gapi.client.scheduler.admin.getAllRooms().execute(function(resp){
			 $scope.rooms=resp.result.items;
			 $scope.services=resp.result;
			 
			 var tempNumber=0;
			 for(var i=0;i< $scope.services.items.length; i++){
				 tempNumber= $scope.services.items[i].number;
				 console.log(tempNumber);
				 //console.log($scope.services.items[i].number);
				 console.log($scope.services.items[i].serviceNames);
				 //console.log(" number of services in array = "+$scope.services.items[i].serviceNames.length);
				 //for(var j=0; j<$scope.services.items[i].serviceNames.length; j++){
					// console.log("The service name = "+j+" "+$scope.services.items[i].serviceNames[j]);
				// }
			 }
			
			 /*
			 $scope.roomsList=resp.result;
			 console.log("printing rooms"+$scope.roomsList.items);
			 console.log("number of Rooms in datastore=" + $scope.roomsList.items.length);
			 console.log("printing all the names of the items returned");
			 for(var i=0; i< $scope.roomsList.items.length; i++){
				 console.log("room number" + $scope.roomsList.items[i].number);
				 console.log("Services IDs" + $scope.roomsList.items[i].services);
				 var serviceId= String($scope.roomsList.items.services);
				 console.log("serviceId string");
				// gapi.client.scheduler.admin.getServices(serviceId).execute(function(resp){
					// $scope.servicesList.push({'name':resp.result.name}); 
				// });
				
		 	}
			*/
			 $scope.$apply();
		 });
		  
	 };
	 
	 
	 $scope.removeRoom = function(index) {
		    //$scope.room.splice(index,1);
		   // $scope.$apply();
		 	console.log("Removing room number ="+index);
		  };
	 
 });

 conferenceApp.controllers.controller('AddAdminController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {

	 $scope.init = function(){
		 gapi.client.scheduler.appointment.test().execute(function (resp) {
             $scope.$root.$apply(function () {

                 $scope.$root.rootMessages = 'Function Call';
             });

		 });
		  
	 };
	 
	 
	
 });

 conferenceApp.controllers.controller('ForgotPasswordController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	 
  });
  
  conferenceApp.controllers.controller('ClientEditProfileController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
  conferenceApp.controllers.controller('ViewCalendarAdminController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {

	  	
  });
  
  conferenceApp.controllers.controller('ClientLoginController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
  conferenceApp.controllers.controller('ClientBookingController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
  

	  
  });
  
  conferenceApp.controllers.controller('ClientCancelAppointmentController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
  conferenceApp.controllers.controller('ClientBookAppointmentController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
  conferenceApp.controllers.controller('AdminEditProfileController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
  conferenceApp.controllers.controller('ViewClientAppointmentController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
      
  conferenceApp.controllers.controller('HomeController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
   conferenceApp.controllers.controller('LoginController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	   
  });
  
   conferenceApp.controllers.controller('PendingController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	   
  }); 
  
  conferenceApp.controllers.controller('RejectController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
   conferenceApp.controllers.controller('SignupController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	   
  });     
  
   conferenceApp.controllers.controller('ViewAccountController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


  });

   
   conferenceApp.controllers.controller('AddClientController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


   });
   
   conferenceApp.controllers.controller('ViewClientController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


   });
   
   
   conferenceApp.controllers.controller('LogController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


   });
