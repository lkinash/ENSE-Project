/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.archetypes.list.Status;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

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
	@Index
    private Status status;

    @Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<Client> clientKey;
	
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
