/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

import com.google.appengine.archetypes.entities.Service;
import com.google.appengine.archetypes.entities.Type;

/**
 * Description of AppointmentForm.
 * 
 * @author Lindsey
 */
public class AppointmentForm {
	/**
	 * Description of the property appointmentType.
	 */
	private Type appointmentType;

	/**
	 * Description of the property service.
	 */
	private Service service;

	/**
	 * Description of the property clientId.
	 */
	private long clientId;

	/**
	 * Description of the property clientId.
	 */
	private EventForm eventForm;	
	
	
	public AppointmentForm(){
		
	}
	
	public AppointmentForm(Type newType, Service newService, long newClientId, EventForm newEventForm){

		this.service = newService;
		this.appointmentType = newType;
		this.clientId = newClientId;
		this.eventForm = newEventForm;
		
	}
	
	
	/**
	 * Returns appointmentType.
	 * @return appointmentType 
	 */
	public Type getAppointmentType() {
		return this.appointmentType;
	}

	/**
	 * Sets a value to attribute appointmentType. 
	 * @param newappointmentType 
	 */
	public void setAppointmentType(Type newAppointmentType) {
		this.appointmentType = newAppointmentType;
	}
	
	/**
	 * Returns EventForm.
	 * @return EventForm 
	 */
	public EventForm getEventForm() {
		return this.eventForm;
	}

	/**
	 * Sets a value to attribute EventForm. 
	 * @param newEventForm 
	 */
	public void setEventForm(EventForm newEventForm) {
		this.eventForm = newEventForm;
	}

	/**
	 * Returns service.
	 * @return service 
	 */
	public Service getService() {
		return this.service;
	}

	/**
	 * Sets a value to attribute service. 
	 * @param newService 
	 */
	public void setService(Service newService) {
		this.service = newService;
	}

	/**
	 * Returns clientId.
	 * @return clientId 
	 */
	public long getClientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute clientId. 
	 * @param newClientId 
	 */
	public void setClientId(long newClientId) {
		this.clientId = newClientId;
	}

}
