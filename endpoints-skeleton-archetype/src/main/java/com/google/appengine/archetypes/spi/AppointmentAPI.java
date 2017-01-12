/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;
import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
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



/**
 * Description of AppointmentAPI.
 * 
 */

@Api(
	    name = "appointment",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	    description = "An API for making appointments."
	)
public class AppointmentAPI {

	
	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "createAppointment", httpMethod = "post")
  	public WrappedBoolean createAppointment(final User user, AppointmentForm appointmentForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
		
        
		return null;
	}

	/**
	 * Description of the method modifyAppointment.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "modifyAppointment", httpMethod = "post")
  	public WrappedBoolean modifyAppointment(final User user, AppointmentForm appointmentForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
		
        
		return null;
	}

	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "cancelAppointment", httpMethod = "post")
  	public WrappedBoolean cancelAppointment(final User user,@Named("userAppointmentId") final long userAppointmentId, CancelAppointmentForm removeAppointmentForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
        
		
		return null;
	}

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "updateAppointmentStatus", httpMethod = "post")
  	public WrappedBoolean updateAppointmentStatus(final User user, @Named("appointmentId") final long appointmentId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
		
        
		return null;
	}

	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "queryAppointments", httpMethod = "post")
  	public Appointment queryAppointments(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
        
		
		return null;
	}

	/**
	 * Description of the method filterAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "filterAppointments", httpMethod = "post")
  	public WrappedBoolean filterAppointments(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
		
        
		return null;
	}

	/**
	 * Description of the method findAvailableAppointmentTimes.
	 * @param appointmentForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "findAvailableAppointmentTimes", httpMethod = "get")
  	public Object findAvailableAppointmentTimes(AppointmentForm appointmentForm, final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
        
		
		return null;
	} 
	
	private static boolean checkAuthorizationForPage(final User user){
  		
        // TODO 
        // Get the page ID
  		
		
		// TODO 
        // Get the user clearances
		
		
		// TODO 
        // Check the user clearances against the page ID
  		
  		
  		return true;
  	}

}
