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
                    	$scope.username=resp.name;
                    	var form={
                    			"email":resp.email
                    	};
                    	gapi.client.scheduler.admin.signin(form).execute(function(resp){
                    		$scope.idreturned=resp.id;
                    		$scope.clearancereturned=resp.adminClearance;
                    		$scope.$apply();
                    		 if($scope.clearancereturned==="client"){
                    			 oauth2Provider.signedIn = true;
                    			 $location.path('/client/home');
                             }else{
                            	 oauth2Provider.signedIn = true;
                            	 $location.path('/admin/home');
                             }
                    	});
                       // oauth2Provider.signedIn = true;
                       // $scope.alertStatus = 'success';
                       // $scope.rootMessages = 'Logged in with ' + resp.email;
                        
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
       // $scope.alertStatus = 'success';
       // $scope.rootMessages = 'Logged out';
        $location.path('');
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
	 $location.path('/admin/home');
  };
 
});

 conferenceApp.controllers.controller('ViewEmployeeController', function ($scope, $log,$location, $route,passingId, oauth2Provider, HTTP_ERRORS) {

	 console.log("controller reached for viewEmployeeAdmin");
	 
	 $scope.init = function(){
		 console.log("Reached Init function: Retrieving All Employee");
		 	 gapi.client.scheduler.admin.getAllEmployeesWithTimeBlocksServices().execute(function(resp){
			 $scope.employees=resp.result.items;
			 $scope.$apply();
		 });
	 };
	 
	 $scope.removeEmployee = function(index) {
		 	console.log("Removing Employee number ="+index);
		 	var employeeId= $scope.employees[index].employeeId;
		 	console.log("roomId that is being deleted= "+ employeeId);
		 	var removeEmployeeForm={
		 			"employeeId":employeeId
		 	};
		 	gapi.client.scheduler.admin.removeEmployee(removeEmployeeForm).execute();
		 	 $location.path('/admin/home');
		  };

	 
 });

 conferenceApp.controllers.controller('AddEmployeeController', function ($scope, $log,$location, oauth2Provider, HTTP_ERRORS) {
	 console.log("Reached AddEmployeeController");
	 $scope.init = function(){
		 console.log("Reached Init function: populating list types drop down");
		 $scope.choices = [
		                   {
		                	   'itemNo':0,
		                   }];
		 $scope.choices2 = [
		                   {
		                	   'itemNo':0,
		                   }];
		 $scope.choices3 = [
			                   {
			                	   'itemNo':0,
			                   }];
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
	 $scope.timeBlocks=[];
	 $scope.timeBlockList=[];
	 var convertedservice= [];
	 
	 $scope.addHoursList= function(weekDay,startTimeHour,startTimeMinute,endTimeHour,endTimeMinute){
		 var timeBlocks={
					"weekDay":weekDay,
					"startHour":parseInt(startTimeHour),
					"startMinute":parseInt(startTimeMinute),
					"endHour":parseInt(endTimeHour),
					"endMinute":parseInt(endTimeMinute)
				};
			  
				  $scope.timeBlocks.push(timeBlocks);  
	 };
	 
	 $scope.addHoliday=function(dateYear, dateMonth, dateDay){
		 var timeBlockList={
				 "day": parseInt(dateDay),
				 "month":parseInt(dateMonth),
				 "year":parseInt(dateYear) 
		 };
		 $scope.timeBlockList.push(timeBlockList);
		 
	 };
	 
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
	 
	 $scope.removeChoiceTime = function(index) {
		    $scope.timeBlocks.splice(index,1);
		 };
     $scope.removeChoiceHoliday = function(index) {
		    $scope.timeBlockList.splice(index,1);
		  };
		  
	 $scope.addEmployee = function() {
		 console.log("reached adding function");
		 for(var i=0; i<$scope.services.length;i++){
			 console.log($scope.services[i].id);
			 convertedservice[i]=parseInt($scope.services[i].id);
		 };
		  	console.log(convertedservice);
		var timeBlockListForm={
				"timeBlocks":$scope.timeBlocks
		};
		
		var holidayTimeBlockListForm={
				"timeBlockList":$scope.timeBlockList
		};
		var employeeForm={
				"firstName":$scope.fname,
				"lastName":$scope.lname,
				"serviceIds":convertedservice,
				"timeBlockListForm":timeBlockListForm,
				"holidayTimeBlockListForm":holidayTimeBlockListForm
								
		};
	    console.log("Employee form object created");
	    console.log("firstname "+employeeForm.firstName);
	    console.log("lastname "+employeeForm.lastName);
	    console.log("serviceID "+employeeForm.serviceIds);
	    console.log("timeeBlocklistform "+employeeForm.timeBlockListForm);
	   // console.log("The services saved in the roomForm Object is:" +roomForm.serviceIds);
	 //gapi.client.scheduler.addRoom(roomForm).execute();
	 
	 gapi.client.scheduler.admin.addEmployee(employeeForm).execute();
	 console.log("Added the employee form now");
	 $location.path('/admin/home');
	 
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
		 	 $location.path('/admin/home');
		  };

	 $scope.removeSpecificService= function(value){
		 	console.log("Removing Specific Service with an ID of=" + value);
		 	var removeServiceForm={
		 			"serviceId":value
		 	};
		 	gapi.client.scheduler.admin.removeService(removeServiceForm).execute();
		 	 $location.path('/admin/home');
		 
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
			 $scope.$apply();
		 });
	 };
	 var serviceTypeForm={};
	 var typeId=0;
	 $scope.getserviceId=function(value){
		 console.log("the id is " +value);
		 typeId=value;
	 };
	 $scope.addService=function(){
		 console.log("Reached the add service function");
		 console.log("what is in thelisttype feild"+$scope.listtypeselect);
		 console.log("the type id is "+typeId);
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
				      "typeId": typeId,
				      "defaultLength":parseInt($scope.serviceDuration)
				    };
		 }
		console.log( "the type id service form"+serviceTypeForm.typeId);
		gapi.client.scheduler.admin.addServiceType(serviceTypeForm).execute();
		 console.log("Added the ServiceType form now");
		 $location.path('/admin/home');
		 
	 };

	 
 });
 
 conferenceApp.controllers.controller('ViewRoomController', function ($scope, $log, $location, $route, oauth2Provider,passingId, HTTP_ERRORS) {
	 console.log("controller reached for viewRoomAdmin");
	 $scope.init = function(){
		 gapi.client.scheduler.admin.getAllRoomsWithServiceNames().execute(function(resp){
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
		 	 $location.path('/admin/home');
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
		 $location.path('/admin/home');
		 
	 };
 });
 conferenceApp.controllers.controller('AdminBookAppointmentController', function ($scope, $log,$location,$compile, uiCalendarConfig, oauth2Provider, HTTP_ERRORS) {
	  console.log("Reached the AdminBookAppointmentController");
	  
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
	  
	  $scope.init = function(){
		  
			 console.log("Reached Init function: populating list types drop down");
			 gapi.client.scheduler.admin.getAllTypesWithService().execute(function(resp){
				 $scope.listtypes=resp.result.items;
				 $scope.$apply();
			 });
			 
			 console.log("Reached Init function: Retrieving AllClients");
		 	 gapi.client.scheduler.admin.getAllClients().execute(function(resp){
			 $scope.clients=resp.result.items;
			 $scope.$apply();
		 	 });
		 	 
		 	console.log("Reached Init function: Retrieving All Employee");
		 	 gapi.client.scheduler.admin.getAllEmployeesWithTimeBlocksServices().execute(function(resp){
			 $scope.employees=resp.result.items;
			 $scope.$apply();
		 	 });
			 
		 };
		 
		 $scope.bookApt=function(){
			 console.log("client id"+ $scope.clientSelect);
			 console.log("type id"+ $scope.temptypeId);
			 console.log("service id"+ $scope.tempServiceId);
			 console.log("employee id"+ $scope.employeeSelect);
			 console.log("start time"+ $scope.startTimeHour);
			 console.log("start minute"+ $scope.startTimeMinute);
			 console.log("Date"+ $scope.tempdate);
			 var temp="46";
			 var templength=parseInt("60");
			 var d= new Date($scope.tempdate);
			 var date={
					 "month":d.getMonth()+1,
					 "day":d.getDate(),
					 "year":d.getFullYear()
					 
			 };
			 var appointmentForm={
					 "clientId":$scope.clientSelect,
					 "employeeId":$scope.employeeSelect,
					 "serviceId":$scope.tempServiceId,
					 "typeId":$scope.temptypeId,
					 "roomId":temp,
					 "date":date,
					 "hour":$scope.startTimeHour,
					 "minute":$scope.startTimeMinute,
					 "length":templength
			 };
			 gapi.client.scheduler.appointment.addAppointment(appointmentForm).execute();
			 $location.path('/admin/home');
		 };
		 
		 $scope.getDate=function(date){
			 $scope.tempdate=date;
		 };
		 
		 $scope.getListServices = function(value){
			 $scope.temptypeId=value;
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
		    
		    $scope.getServiceId=function(val){
		    	console.log("the value is "+val);
		    	$scope.tempServiceId=val;
		    };
		    

	  
 });
 conferenceApp.controllers.controller('SearchAppointmentController', function ($scope, $log,$location, oauth2Provider, HTTP_ERRORS) {
	 $scope.init=function(){
		 console.log("Reached Init function: Retrieving AllClients");
	 	 gapi.client.scheduler.admin.getAllClients().execute(function(resp){
		 $scope.clients=resp.result.items;
		 $scope.$apply();
	 	 });
	 	console.log("Reached Init function: Retrieving All Employee");
	 	 gapi.client.scheduler.admin.getAllEmployeesWithTimeBlocksServices().execute(function(resp){
		 $scope.employees=resp.result.items;
		 $scope.$apply();
	 	 });
		 
	 };
	 
	 $scope.getAppointments=function(){
		 var tempId=$scope.clientSelect;
		 var clientForm={
			 "clientId":tempId
		 };
		 gapi.client.scheduler.appointment.getClientAppointmentsObject(clientForm).execute(function(resp){
			 $scope.appointmentsList=resp.result.items;
			 console.log("appointment name test"+$scope.appointmentsList[0].employeeName);
			 for(var i=0;i<$scope.appointmentsList.length;i++){
				 var stringcon=$scope.appointmentsList[i].date;
				 stringcon=stringcon.substr(13,16);
				 stringcon=stringcon.replace("T", " at ");
				 $scope.appointmentsList[i].date=stringcon;
			 }
			 $scope.$apply();
		 });
	 };
	 
	 $scope.removeApp=function(val){
		 var reason="cancelling appointment";
		 var appointmentForm={
				 "appointmentId":$scope.appointmentsList[val].appointmentId,
				 "clientId":$scope.appointmentsList[val].clientId,
				 "employeeId":$scope.appointmentsList[val].employeeId,
				 "roomId":$scope.appointmentsList[val].roomId,
				 "reasonForCancellation":reason
		 };
		 
		 gapi.client.scheduler.appointment.removeAppointment(appointmentForm).execute();
		 $location.path('/admin/home');
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
  
  conferenceApp.controllers.controller('ClientBookAppointmentController', function ($scope, $log, $location, oauth2Provider, HTTP_ERRORS) {
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
		      $scope.tempTypeid=value;
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
		    
		    $scope.getServiceId=function(val){
		    	console.log("the value is "+val);
		    	$scope.tempServiceId=val;
		    	
		    	var removeServiceForm={
		    			"serviceId":val
		    	};
		    	
		    	gapi.client.scheduler.admin.getServiceEmployees(removeServiceForm).execute(function(resp){
		    		$scope.employees=resp.result.items;
		    		$scope.$apply();
		    		
		    	});
		    	
		    };
		    
		    $scope.getStartDate=function(sdate){
		    	$scope.tempStartDate=sdate;
		    };
		    
		    $scope.getEndDate=function(edate){
		    	$scope.tempEndDate=edate;
		    };
		    
		    $scope.getOptions=function(){
		    	
		    	var tempclientId=$scope.idreturned;
		    	console.log("The id is ="+tempclientId);
		    	var d= new Date($scope.tempStartDate);
				 var startdate={
						 "month":d.getMonth()+1,
						 "day":d.getDate(),
						 "year":d.getFullYear()
						 
				 };
				 var d1= new Date($scope.tempEndDate);
				 var enddate={
						 "month":d1.getMonth()+1,
						 "day":d1.getDate(),
						 "year":d1.getFullYear()
						 
				 };
		    	var findAppointmentForm={
		    		 "clientId":tempclientId,
					 "employeeId":$scope.employeeSelect,
					 "serviceId":$scope.tempServiceId,
					 "typeId":$scope.tempTypeid,
					 "startDateRange":startdate,
					 "endDateRange":enddate
		    	};
		    	
		    	 gapi.client.scheduler.appointment.getAppointmentOptions(findAppointmentForm).execute(function(resp){
			    		$scope.appointments=resp.result.items;
			    		$scope.$apply();			    		
			    	});
		    	 
		    	 $scope.bookAppointment= function(val){
		    		 var appointmentForm=$scope.appointments[val];
		    		 gapi.client.scheduler.appointment.addAppointment(appointmentForm).execute();
		    		 console.log("book the appointment ");
		    		 $location.path('/admin/home');
		    	 };
		    	
		    };

	  
  });
  
  conferenceApp.controllers.controller('editAdminController', function ($scope, $log, $location, oauth2Provider, passingId, HTTP_ERRORS) {
	  console.log("reached the edit admin controller");
	  console.log("the id"+passingId.getId());
	  
	  var tempId=passingId.getId();
	  
	  $scope.init=function(){
			 console.log("Reached Init function: Retrieving All Admins");
		 	 gapi.client.scheduler.admin.getAllAdmins().execute(function(resp){
			 $scope.adminlist=resp.result.items;
			 $scope.$apply();
			 
			 for(var i=0; i<$scope.adminlist.length;i++){
				if(tempId===$scope.adminlist[i].adminId){
			 	$scope.fname=$scope.adminlist[i].firstName;
			 	$scope.lname=$scope.adminlist[i].lastName;
			 	$scope.email=$scope.adminlist[i].email;
			 	$scope.adminClearanceSubmit=$scope.adminlist[i].adminClearance;
				}
			 };
		 });
		 	 
	  };
	  
	  var adminForm={
				 
		 };
		 $scope.addAdmin=function(){
			 adminForm={
					 "adminId":tempId,
					 "firstName":$scope.fname,
					 "lastName":$scope.lname,
					 "email":$scope.email,
					 "clearance":$scope.adminClearanceSubmit
			 };
			 console.log(adminForm);
			 gapi.client.scheduler.admin.updateAdmin(adminForm).execute();
			 console.log("updated the  the Admin ");
			 $location.path('/admin/home');
			 
		 };
	  
  });
  /*
  conferenceApp.controllers.controller('editEmployeeController', function ($scope, $log, $location, oauth2Provider, passingId, HTTP_ERRORS) {
	  console.log("reached the edit employee controller");
	  console.log("the id"+passingId.getId());
	  
	 // var tempId=passingId.getId();
	
	  
		 		 	 
  });
  */
  conferenceApp.controllers.controller('editClientAdminController', function ($scope, $log, $location, oauth2Provider, passingId, HTTP_ERRORS) {
	  console.log("reached the edit client controller");
	  console.log("the id"+passingId.getId());
	  var tempId2=passingId.getId();
	  var clientForm={
			  "clientId":tempId2
	  };
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
	   
		gapi.client.scheduler.client.getClientObject(clientForm).execute(function(resp){
	 		$scope.clientOb=resp.result;
			 $scope.$apply();
			 if(resp){
			 $scope.Fname=$scope.clientOb.firstName;
			 $scope.lname=$scope.clientOb.lastName;
			 $scope.phoneNumber=$scope.clientOb.phoneNumber;
			 $scope.email=$scope.clientOb.email;
			 $scope.dateYear=$scope.clientOb.birthdayBlock.year;
			 $scope.dateMonth=$scope.clientOb.birthdayBlock.month;
			 $scope.dateDay=$scope.clientOb.birthdayBlock.day;
			 }
	 	 });

		
	
	 $scope.addClient=function(){
			 var clientForm={};
			 var newBirthday={
					 "month":parseInt($scope.dateMonth),
					 "year":parseInt($scope.dateYear),
					 "day":parseInt($scope.dateDay)
			 };
			 console.log(newBirthday);
			 var password="sdkjfs";
			 clientForm = {
					  "clientId":tempId2,
				      "firstName" : $scope.Fname,
				      "lastName": $scope.lname,
				      "phoneNumber": $scope.phoneNumber,
				      "email": $scope.email,
				      "birthday": newBirthday,
				      "password":password
		
				    };
			gapi.client.scheduler.client.updateClient(clientForm).execute();
			console.log("Client updated ");
			 $location.path('/admin/home');
		 };

		 
  });
  
conferenceApp.controllers.controller('homeIndexController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {
	console.log("Reached home index controller");
  });
  
  conferenceApp.controllers.controller('ViewClientAppointmentController', function ($scope, $log, $location, oauth2Provider, HTTP_ERRORS) {
	  console.log("reached the view Client appointment controller");
	  $scope.init=function(){
		  var tempId=$scope.idreturned;
		  console.log("the id is "+tempId);
		  var clientForm={
					 "clientId":tempId
				 };
		  gapi.client.scheduler.appointment.getClientAppointmentsObject(clientForm).execute(function(resp){
				 $scope.appointmentsList=resp.result.items;
				 console.log("appointment name test"+$scope.appointmentsList[0].employeeName);
				 for(var i=0;i<$scope.appointmentsList.length;i++){
					 var stringcon=$scope.appointmentsList[i].date;
					 stringcon=stringcon.substr(13,16);
					 stringcon=stringcon.replace("T", " at ");
					 $scope.appointmentsList[i].date=stringcon;
				 }
				 $scope.$apply();
			 });
	  };
	  
	  $scope.removeApp=function(val){
			 var reason="cancelling appointment";
			 var appointmentForm={
					 "appointmentId":$scope.appointmentsList[val].appointmentId,
					 "clientId":$scope.appointmentsList[val].clientId,
					 "employeeId":$scope.appointmentsList[val].employeeId,
					 "roomId":$scope.appointmentsList[val].roomId,
					 "reasonForCancellation":reason
			 };
			 
			 gapi.client.scheduler.appointment.removeAppointment(appointmentForm).execute();
			 $location.path('/admin/home');
			 
		 };
	  
  });
      
  conferenceApp.controllers.controller('ClientHomeController', function ($scope, $log,$location,$compile, uiCalendarConfig, oauth2Provider, HTTP_ERRORS) {
	  console.log("Reached the client home page");
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
	  
	    $scope.gotoBook=function(){
	    	 $location.path('/client/bookAppointment');
	    };
	    
	    $scope.gotoview=function(){
	    	$location.path('/client/viewAppointment');
	    };
  });
  
   conferenceApp.controllers.controller('LoginController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	   
  });
  
   conferenceApp.controllers.controller('PendingController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	   
  }); 
  
  conferenceApp.controllers.controller('RejectController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	  
  });
  
   conferenceApp.controllers.controller('SignupController', function ($scope, $log, oauth2Provider, HTTP_ERRORS) {


	   
  });     
  
   conferenceApp.controllers.controller('ViewAccountController', function ($scope, $log,$route,$location, passingId, oauth2Provider, HTTP_ERRORS) {
	   console.log("Reached the View admin controller");
	   console.log("the id"+passingId.getId());
		  
	   $scope.init = function(){
			 console.log("Reached Init function: Retrieving All Admins");
			 	 gapi.client.scheduler.admin.getAllAdmins().execute(function(resp){
				 $scope.admins=resp.result.items;
				 $scope.$apply();
			 });
		 };
		 
	   $scope.removeAdmin = function(index) {
			 	//console.log("Removing admin number ="+index);
			 	var adminId= $scope.admins[index].adminId;
			 	//console.log("Admin that is being deleted= "+ adminId);
			 	var removeAdminForm={
			 			"adminId":adminId
			 	};
			 	gapi.client.scheduler.admin.removeAdmin(removeAdminForm).execute();
			 	 $location.path('/admin/home');
			  };
			  
		$scope.editAdmin=function(val){
			passingId.setId($scope.admins[val].adminId);
			$location.path('/admin/editAdmin');
		
		};

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
			 console.log(newBirthday);
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
			 $location.path('/admin/home');
		 };
   });
   conferenceApp.controllers.controller('ViewClientController', function ($scope, $log,$location,$route,passingId, oauth2Provider, HTTP_ERRORS) {
	   console.log("Reached the View client controller");
	   console.log("the id"+passingId.getId());
	  
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
			 	 $location.path('/admin/home');
			  };
			  
		$scope.editClient=function(val){
					passingId.setId($scope.clients[val].clientId);
					$location.path('/admin/editClient');
				
		};
		
   });
   
   conferenceApp.controllers.controller('ViewCalendarController',function ($scope,$compile, uiCalendarConfig){
		
	   $scope.init=function(){
		   gapi.client.scheduler.admin.getAllEmployeesWithTimeBlocksServices().execute(function(resp){
				 $scope.employees=resp.result.items;
				 $scope.$apply();
			 });
		   gapi.client.scheduler.admin.getAllRoomsWithServiceNames().execute(function(resp){
				 $scope.rooms=resp.result.items;
				 $scope.$apply();
			 });
		   
	   };
	   
	
	   $scope.calendarURL="ronbrfav0rnhnjo3bjfi0m186c@group.calendar.google.com";
	   
	   var date = new Date();
	    var d = date.getDate();
	    var m = date.getMonth();
	    var y = date.getFullYear();
	    
	    $scope.changeTo = 'Hungarian';
	    /* event source that pulls from google.com */
	    $scope.eventSource = {
	            url: $scope.calendarURL,
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
	    
	    $scope.changeCal=function(){
			   if($scope.firstSelect==="employee"){
				   console.log("its an employee" +$scope.employeeSelect);
				   $scope.calendarURL=$scope.employees[$scope.employeeSelect].calendarId;
				   console.log($scope.calendarURL=$scope.employees[$scope.employeeSelect].calendarId);
				   //$scope.myCalendar1.fullCalendar("refetchEvents");
				   $('#calendar').fullCalendar('rerenderEvents');
			   }
			   if($scope.firstSelect==="room"){
				   console.log("its an room");
			   }
			   if($scope.firstSelect==="main"){
				   console.log("its an main");
				   var calURL="ronbrfav0rnhnjo3bjfi0m186c@group.calendar.google.com";
			   }
			   
		   };
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
