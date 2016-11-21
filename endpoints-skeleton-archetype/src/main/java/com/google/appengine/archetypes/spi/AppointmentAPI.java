/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import com.google.api.server.spi.config.Api;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.forms.AppointmentForm;
import com.google.appengine.archetypes.forms.CancelAppointmentForm;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of AppointmentAPI.
 * 
 * @author Lindsey
 */

public class AppointmentAPI {

	// Start of user code (user defined attributes for AppointmentAPI)

	// End of user code

	/**
	 * The constructor.
	 */
	public AppointmentAPI() {
		// Start of user code constructor for AppointmentAPI)
		super();
		// End of user code
	}

	/**
	 * Description of the method createAppointment.
	 */
	public void createAppointment() {
		// Start of user code for method createAppointment
		// End of user code
	}

	/**
	 * Description of the method modifyAppointment.
	 */
	public void modifyAppointment() {
		// Start of user code for method modifyAppointment
		// End of user code
	}

	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 */
	public void cancelAppointment(Client client, Long appointmentId, CancelAppointmentForm removeAppointmentForm) {
		// Start of user code for method cancelAppointment
		// End of user code
	}

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 */
	public void updateAppointmentStatus(Admin admin, Long appointmentId) {
		// Start of user code for method updateAppointmentStatus
		// End of user code
	}

	/**
	 * Description of the method queryAppointments.
	 */
	public void queryAppointments() {
		// Start of user code for method queryAppointments
		// End of user code
	}

	/**
	 * Description of the method filterAppointments.
	 */
	public void filterAppointments() {
		// Start of user code for method filterAppointments
		// End of user code
	}

	/**
	 * Description of the method findAvailableAppointmentTimes.
	 * @param appointmentForm 
	 */
	public void findAvailableAppointmentTimes(AppointmentForm appointmentForm) {
		// Start of user code for method findAvailableAppointmentTimes
		// End of user code
	}



}
