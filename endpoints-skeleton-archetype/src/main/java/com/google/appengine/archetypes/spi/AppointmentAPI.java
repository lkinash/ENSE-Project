/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.forms.AppointmentForm;
import com.google.appengine.archetypes.forms.CancelAppointmentForm;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.archetypes.entities.User;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.forms.EmployeeForm;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of AppointmentAPI.
 * 
 * @author Lindsey
 */

@Api(
	    name = "appointment",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
	    audiences = {Constants.ANDROID_AUDIENCE}
	)
public class AppointmentAPI {

	
	/**
	 * Description of the method createAppointment.
	 */
	/*
	@ApiMethod(name = "createAppointment", httpMethod = "post")
  	public WrappedBoolean createAppointment() {
		// Start of user code for method createAppointment
		// End of user code
		return null;
	}

	/**
	 * Description of the method modifyAppointment.
	 */
	/*
	@ApiMethod(name = "modifyAppointment", httpMethod = "post")
  	public WrappedBoolean modifyAppointment() {
		// Start of user code for method modifyAppointment
		// End of user code
		return null;
	}

	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 */
	/*
	@ApiMethod(name = "cancelAppointment", httpMethod = "post")
  	public WrappedBoolean cancelAppointment(User user, long appointmentId, CancelAppointmentForm removeAppointmentForm) {
		// Start of user code for method cancelAppointment
		// End of user code
		return null;
	}

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 */
	/*
	@ApiMethod(name = "updateAppointmentStatus", httpMethod = "post")
  	public WrappedBoolean updateAppointmentStatus(User user, Long appointmentId) {
		// Start of user code for method updateAppointmentStatus
		// End of user code
		return null;
	}

	/**
	 * Description of the method queryAppointments.
	 */
	/*
	@ApiMethod(name = "queryAppointments", httpMethod = "post")
  	public StringObject queryAppointments(User user) {
		// Start of user code for method queryAppointments
		// End of user code
		return null;
	}

	/**
	 * Description of the method filterAppointments.
	 */
	/*
	@ApiMethod(name = "filterAppointments", httpMethod = "post")
  	public WrappedBoolean filterAppointments(User user) {
		// Start of user code for method filterAppointments
		// End of user code
		return null;
	}

	/**
	 * Description of the method findAvailableAppointmentTimes.
	 * @param appointmentForm 
	 */
	/*
	@ApiMethod(name = "findAvailableAppointmentTimes", httpMethod = "get")
  	public Object findAvailableAppointmentTimes(AppointmentForm appointmentForm, User user) {
		// Start of user code for method findAvailableAppointmentTimes
		// End of user code
		return null;
	}

*/

}
