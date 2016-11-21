/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.appengine.archetypes.entities.Status;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Appointment.
 * 
 * @author Lindsey
 */
public class Appointment{

	/**
	 * Description of the property status.
	 */
	private Status status = null;

	/**
	 * Description of the property eventId.
	 */
	private Long eventId = null;

	// Start of user code (user defined methods for Appointment)

	// End of user code

	public Appointment(){
		
	}
	
	public Appointment(Status status, Long eventId) {
		this.status = status;
		this.eventId = eventId;
	}

	/**
	 * Returns status.
	 * @return status 
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * Sets a value to attribute status. 
	 * @param newStatus 
	 */
	public void setStatus(Status newStatus) {
		this.status = newStatus;
	}

	/**
	 * Returns eventId.
	 * @return eventId 
	 */
	public Long getEventId() {
		return this.eventId;
	}

	/**
	 * Sets a value to attribute eventId. 
	 * @param newEventId 
	 */
	public void setEventId(Long newEventId) {
		this.eventId = newEventId;
	}

}
