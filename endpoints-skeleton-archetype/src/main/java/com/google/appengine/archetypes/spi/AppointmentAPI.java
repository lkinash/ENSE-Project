/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.forms.AppointmentForm;
import com.google.appengine.archetypes.forms.CancelAppointmentForm;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.api.users.User;
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
	
	@ApiMethod(name = "createAppointment", httpMethod = "post")
  	public WrappedBoolean createAppointment(final User user) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method modifyAppointment.
	 */
	
	@ApiMethod(name = "modifyAppointment", httpMethod = "post")
  	public WrappedBoolean modifyAppointment(final User user) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 */
	
	@ApiMethod(name = "cancelAppointment", httpMethod = "post")
  	public WrappedBoolean cancelAppointment(final User user,@Named("userAppointmentId") final long userAppointmentId, CancelAppointmentForm removeAppointmentForm) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 */
	
	@ApiMethod(name = "updateAppointmentStatus", httpMethod = "post")
  	public WrappedBoolean updateAppointmentStatus(final User user, @Named("appointmentId") final long appointmentId) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method queryAppointments.
	 */
	
	@ApiMethod(name = "queryAppointments", httpMethod = "post")
  	public Appointment queryAppointments(final User user) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method filterAppointments.
	 */
	
	@ApiMethod(name = "filterAppointments", httpMethod = "post")
  	public WrappedBoolean filterAppointments(final User user) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method findAvailableAppointmentTimes.
	 * @param appointmentForm 
	 */
	
	@ApiMethod(name = "findAvailableAppointmentTimes", httpMethod = "get")
  	public Object findAvailableAppointmentTimes(AppointmentForm appointmentForm, final User user) {

        // TODO 
        // 
		
		return null;
	} 

}
