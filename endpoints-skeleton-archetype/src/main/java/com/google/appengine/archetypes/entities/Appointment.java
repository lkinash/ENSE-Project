/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.appengine.archetypes.list.Status;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Appointment.
 * 
 * @author Lindsey
 */
@Entity
public class Appointment{

	/**
	 * Description of the property status.
	 */
	private Status status;

	/**
	 * Description of the property eventId.
	 */
	@Id 
	private Long eventId;

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
