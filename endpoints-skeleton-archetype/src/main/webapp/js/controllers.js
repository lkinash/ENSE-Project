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
                	$scope.username=resp.name;
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

conferenceApp.controllers.controller('AddRoomController', function ($scope, $log,$location, oauth2Provider, HTTP_ERRORS) {
	console.log("reached AddRoom controller");
	var roomForm={
			"number":11,
			"services":[{
				
			}]
	};
	
	 $scope.init = function(){
		 console.log("Reached Init function: populating list types drop down");
		 $scope.choices = [
		                   {
		                	   'itemNo':0,
		                   }];
		 gapi.client.scheduler.admin.getAllTypesWithService().execute(function(resp){
			 $scope.listtypes=resp.result.items;
			 $scope.$apply();
		 });
	 };
	 
	 
	 $scope.getListServices = function(value){
		 console.log("Reacher getlistservics function: populating service dropdown");
	      console.log("The typeId from the first dropdown in value variable is"+ value);
	      $scope.listservices=[];
	      for(var i=0; i<$scope.listtypes.length;i++){
	    	  if($scope.listtypes[i].typeId==value){
	    		  console.log(" the typeId found is "+ $scope.listtypes[i].typeId);
	    		   console.log("There are this many services for this"+ $scope.listtypes[i].service.length);
	    		   $scope.listservices=$scope.listtypes[i].service;
	    		   console.log("these are the services"+ $scope.listservices);
	    		  
	    	  }
	      }
	    };
	

	 $scope.services=[];
	 var convertedservice= [];
	 
	 $scope.addServiceList=function(value,name,typeName){
		  var flag=0;
		  console.log("got to addServiceList function v $scope.servicesalue= "+ value);
		  for(var i=0; i< $scope.services.length;i++){
			  if(value==$scope.services[i].id){
				  flag=1;
			  }
		  }
		  if(flag==0){
			  $scope.services.push({'id':value,'name':name,'typeName':typeName});  
		  }else{
			  console.log("Service was not added as you are unable to add duplicate services IDs");
		  }
		  
		  console.log("add the service to services");
	 };
	    
	 $scope.removeChoice = function(index) {
	    $scope.services.splice(index,1);
	 };
	 
	 $scope.addRoomFinal = function() {
		  
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
	 
	 //$scope.number=9999999;
	 gapi.client.scheduler.admin.addRoom(roomForm).execute();
	 $location.path('/admin/viewRoom');
  };
 
});

 conferenceApp.controllers.controller('ViewEmployeeController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {

	 console.log("controller reached for viewEmployeeAdmin");

	 
 });

 conferenceApp.controllers.controller('AddEmployeeController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	 console.log("Reached AddEmployeeController");
	 $scope.hours=["0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"
	               ];
	 $scope.minutes=["00","05","10","15","20","25","30","35","40","45","50","55"
	                 ];
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
 
 conferenceApp.controllers.controller('ViewServiceController', function ($scope, $log,$location,$route, oauth2Provider, HTTP_ERRORS) {
	 console.log("Reached the ViewServiceController");
	 
	 $scope.init = function(){
		 console.log("Reached Init function: Retrieving AllTypesWithService");
		 gapi.client.scheduler.admin.getAllTypesWithService().execute(function(resp){
			 $scope.listtypes=resp.result.items;
			 $scope.$apply();
		 });
	 };
	 
	 $scope.removeType = function(index) {
		 	console.log("Removing type number ="+index);
		 	var typeId= $scope.listtypes[index].typeId;
		 	console.log("typeId that is being deleted= "+ typeId);
		 	var removeTypeForm={
		 			"typeId":typeId
		 	};
		 	gapi.client.scheduler.admin.removeType(removeTypeForm).execute();
		 	console.log("removed type");
		 	$route.reload();
		  };

	 $scope.removeSpecificService= function(value){
		 	console.log("Removing Specific Service with an ID of=" + value);
		 	var removeServiceForm={
		 			"serviceId":value
		 	};
		 	gapi.client.scheduler.admin.removeService(removeServiceForm).execute();
		 	$route.reload();
		 
	 };
 });

 conferenceApp.controllers.controller('AddServiceController', function ($scope, $log,$location,oauth2Provider, HTTP_ERRORS) {
	 console.log("Reached the AddServiceController");
	 $scope.typenames=[];
	 $scope.init = function(){
		 console.log("Reached Init function: populating list types drop down");
		 gapi.client.scheduler.admin.getAllTypes().execute(function(resp){
			 $scope.listtypes=resp.result.items;
			 console.log("The length is "+$scope.listtypes.length);
			// for(var i=0;i<$scope.listtypes.length;i++){
				// $scope.typenames[i]=$scope.listtypes[i].typeName;
				// console.log("name"+i+" "+$scope.typenames[i]);
			// }
			 $scope.listtypeselect=$scope.listtypes[0].typeName;
			 $scope.$apply();
		 });
	 };
	 var serviceTypeForm={};
	 $scope.addService=function(){
		 console.log("Reached the add service function");
		 if($scope.newtype==true){
		 serviceTypeForm = {
			      "name" : $scope.serviceName,
			      "price": parseFloat($scope.servicePrice),
			      "clearanceRequired": $scope.clearanceCheck,
			      "typeId": 0,
			      "typeName": $scope.newTypeName,
			      "defaultLength":parseInt($scope.serviceDuration),
			    };
		 }else{
			 serviceTypeForm = {
				      "name" : $scope.serviceName,
				      "price": parseFloat($scope.servicePrice),
				      "clearanceRequired": $scope.clearanceCheck,
				      "typeId": $scope.listtypeselect.typeId,
				      "typeName": $scope.listtypeselect.typeName,
				      "defaultLength":parseInt($scope.serviceDuration)
				    };
		 }
		//console.log(serviceTypeForm);
		 gapi.client.scheduler.admin.addServiceType(serviceTypeForm).execute();
		 console.log("Added the ServiceType form now");
		 $location.path('/admin/viewService');
		 
	 };

	 
 });
 
 conferenceApp.controllers.controller('ViewRoomController', function ($scope, $log, $location, $route, oauth2Provider, HTTP_ERRORS) {
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
		 	console.log("Removing room number ="+index);
		 	var roomId= $scope.rooms[index].roomId;
		 	console.log("roomId that is being deleted= "+ roomId);
		 	var removeRoomForm={
		 			"roomId":roomId
		 	};
		 	gapi.client.scheduler.admin.removeRoom(removeRoomForm).execute();
		 	$route.reload();
		  };
	 
 });

 conferenceApp.controllers.controller('AddAdminController', function ($scope, $log,$location, oauth2Provider, HTTP_ERRORS) {
	 console.log("reached the AddAdminController");

	 var adminForm={
			 
	 };
	 $scope.addAdmin=function(){
		 adminForm={
				 "firstName":$scope.fname,
				 "lastName":$scope.lname,
				 "email":$scope.email,
				 "clearance":$scope.adminClearanceSubmit
		 };
		 console.log(adminForm);
		 gapi.client.scheduler.admin.addAdmin(adminForm).execute();
		 console.log("Added the Admin form now");
		 $location.path('/admin/viewAccount');
		 
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
	  console.log("Reached the ClientBookAppointmentController");
	  $scope.init = function(){
			 console.log("Reached Init function: populating list types drop down");
			 gapi.client.scheduler.admin.getAllTypesWithService().execute(function(resp){
				 $scope.listtypes=resp.result.items;
				 $scope.$apply();
			 });
		 };
		 
		 
		 $scope.getListServices = function(value){
			 console.log("Reacher getlistservics function: populating service dropdown");
		      console.log("The typeId from the first dropdown in value variable is"+ value);
		      $scope.listservices=[];
		      for(var i=0; i<$scope.listtypes.length;i++){
		    	  if($scope.listtypes[i].typeId==value){
		    		//  console.log(" the typeId found is "+ $scope.listtypes[i].typeId);
		    		 //  console.log("There are this many services for this"+ $scope.listtypes[i].service.length);
		    		   $scope.listservices=$scope.listtypes[i].service;
		    		   //console.log("these are the services"+ $scope.listservices);
		    		  
		    	  }
		      }
		    };

	  
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

   
   conferenceApp.controllers.controller('AddClientController', function ($scope, $log, $location,$route, oauth2Provider, HTTP_ERRORS) {
	 console.log("reached the AddClientController");
	   var endYear = 2017;
	   var startYear = 1920;
	   $scope.selects = {

			   days: function(){

			     // Get number of days based on month + year 
			     // (January = 31, February = 28, April = 30, February 2000 = 29) or 31 if no month selected yet
			     var nbDays = new Date($scope.dateYear, $scope.dateMonth, 0).getDate() || 31;

			     var daysList = [];
			     for( var i = 1; i <= nbDays ; i++){
			       var iS = i.toString();
			       daysList.push( (iS.length < 2) ? '0' + iS : iS ); // Adds a leading 0 if single digit
			     }
			     return daysList;
			   },
			   months: function(){
			     var monthList = [];
			     for( var i = 1; i <= 12 ; i++){
			       var iS = i.toString();
			       monthList.push( (iS.length < 2) ? '0' + iS : iS ); // Adds a leading 0 if single digit
			     }
			     return monthList;
			   },
			   years: function(){
			     var yearsList = [];
			     for( var i = endYear; i >= startYear ; i--){
			       yearsList.push( i.toString() );
			     }
			     return yearsList;
			   }
			 };
	   var clientForm={};
		 $scope.addClient=function(){
			 var newBirthday={
					 "month":parseInt($scope.dateMonth),
					 "year":parseInt($scope.dateYear),
					 "day":parseInt($scope.dateDay)
			 };
			 var password="sdkjfs";
			 clientForm = {
				      "firstName" : $scope.Fname,
				      "lastName": $scope.lname,
				      "phoneNumber": $scope.phoneNumber,
				      "email": $scope.email,
				      "birthday": newBirthday,
				      "password":password
		
				    };
			gapi.client.scheduler.client.addClient(clientForm).execute();
			console.log("Client successfully added");
			$location.path('/');
		 };
   });
   conferenceApp.controllers.controller('ViewClientController', function ($scope, $log,$route, oauth2Provider, HTTP_ERRORS) {
	   console.log("Reached the View client controller");
	  
	   $scope.init = function(){
			 console.log("Reached Init function: Retrieving AllClients");
			 	 gapi.client.scheduler.admin.getAllClients().execute(function(resp){
				 $scope.clients=resp.result.items;
				 $scope.$apply();
			 });
		 };
		 
	   $scope.removeClient = function(index) {
			 	console.log("Removing client number ="+index);
			 	var clientId= $scope.clients[index].clientId;
			 	console.log("Client that is being deleted= "+ clientId);
			 	var removeClientForm={
			 			"clientId":clientId
			 	};
			 	gapi.client.scheduler.client.removeClient(removeClientForm).execute();
			 	$route.reload();
			  };
		
   });
   
   conferenceApp.controllers.controller('ViewCalendarController',function ($scope,$compile, uiCalendarConfig){
		var date = new Date();
	    var d = date.getDate();
	    var m = date.getMonth();
	    var y = date.getFullYear();
	    
	    $scope.changeTo = 'Hungarian';
	    /* event source that pulls from google.com */
	    $scope.eventSource = {
	            url: "ronbrfav0rnhnjo3bjfi0m186c@group.calendar.google.com",
	            googleCalendarApiKey: 'AIzaSyCk3oNQRiy8vVIM-dyIx4DZfy2qyTT3avU',
	            className: 'gcal-event',           // an option!
	            currentTimezone: 'America/Chicago' // an option!
	    };
	    /* event source that contains custom events on the scope */
	    $scope.events = [
	      {title: 'All Day Event',start: new Date(y, m, 1)},
	      {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
	      {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false},
	      {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
	      {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
	      {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
	    ];
	    /* event source that calls a function on every view switch */
	    $scope.eventsF = function (start, end, timezone, callback) {
	      var s = new Date(start).getTime() / 1000;
	      var e = new Date(end).getTime() / 1000;
	      var m = new Date(start).getMonth();
	      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
	      callback(events);
	    };

	    $scope.calEventsExt = {
	       color: '#f00',
	       textColor: 'yellow',
	       events: [ 
	          {type:'party',title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
	          {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
	          {type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
	        ]
	    };
	    /* alert on eventClick */
	    $scope.alertOnEventClick = function( date, jsEvent, view){
	        $scope.alertMessage = (date.title + ' was clicked ');
	    };
	    /* alert on Drop */
	     $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
	       $scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
	    };
	    /* alert on Resize */
	    $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
	       $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
	    };
	    /* add and removes an event source of choice */
	    $scope.addRemoveEventSource = function(sources,source) {
	      var canAdd = 0;
	      angular.forEach(sources,function(value, key){
	        if(sources[key] === source){
	          sources.splice(key,1);
	          canAdd = 1;
	        }
	      });
	      if(canAdd === 0){
	        sources.push(source);
	      }
	    };
	    /* add custom event*/
	    $scope.addEvent = function() {
	      $scope.events.push({
	        title: 'Open Sesame',
	        start: new Date(y, m, 28),
	        end: new Date(y, m, 29),
	        className: ['openSesame']
	      });
	    };
	    /* remove event */
	    $scope.remove = function(index) {
	      $scope.events.splice(index,1);
	    };
	    /* Change View */
	    $scope.changeView = function (view, calendar) {
	        $scope.currentView = view;
	        uiCalendarConfig.calendars[calendar].fullCalendar('changeView', view);
	    };
	    /* Change View */
	    $scope.renderCalender = function (calendar) {
	        $timeout(function () {
	            if (uiCalendarConfig.calendars[calendar]) {
	                uiCalendarConfig.calendars[calendar].fullCalendar('render');
	            }
	        });
	    };
	     /* Render Tooltip */
	    $scope.eventRender = function( event, element, view ) { 
	        element.attr({'tooltip': event.title,
	                     'tooltip-append-to-body': true});
	        $compile(element)($scope);
	    };
	    /* config object */
	    $scope.uiConfig = {
	      calendar:{
	        height: 450,
	        editable: true,
	        header:{
	          left: 'title',
	          center: '',
	          right: 'today prev,next'
	        },
	        eventClick: $scope.alertOnEventClick,
	        eventDrop: $scope.alertOnDrop,
	        eventResize: $scope.alertOnResize,
	        eventRender: $scope.eventRender
	      }
	    };

	    $scope.changeLang = function() {
	      if($scope.changeTo === 'Hungarian'){
	        $scope.uiConfig.calendar.dayNames = ["Vasárnap", "Hétfo", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat"];
	        $scope.uiConfig.calendar.dayNamesShort = ["Vas", "Hét", "Kedd", "Sze", "Csüt", "Pén", "Szo"];
	        $scope.changeTo= 'English';
	      } else {
	        $scope.uiConfig.calendar.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	        $scope.uiConfig.calendar.dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	        $scope.changeTo = 'Hungarian';
	      }
	    };
	    /* event sources array*/
	    $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];
	    $scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.events];
	});
   conferenceApp.controllers.controller('LogController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	 console.log("reached the log controller");
	   $scope.init = function(){
			 console.log("Reached Init function: Retrieving AllClients");
			 	 gapi.client.scheduler.admin.getAllChanges().execute(function(resp){
				 $scope.changes=resp.result.items;
				 $scope.$apply();
			 });
		 };

   });
