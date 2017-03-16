/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.Type;

/**
 * Description of AppointmentForm.
 * 
 * @author Lindsey
 */
public class AppointmentForm {
	/**
	 * Description of the property appointmentType.
	 */
	private long typeId;

	/**
	 * Description of the property service.
	 */
	private long serviceId;

	/**
	 * Description of the property clientId.
	 */
	private long clientId;

	/**
	 * Description of the property clientId.
	 */
	private long employeeId;
	
	/**
	 * Description of the property clientId.
	 */
	private EventForm eventForm;	
	
	
	public AppointmentForm(){
		
	}
	
	public AppointmentForm(long newType, long newService, long newClientId, long newEmployeeId, EventForm newEventForm){

		this.serviceId = newService;
		this.typeId = newType;
		this.clientId = newClientId;
		this.employeeId = newEmployeeId;
		this.eventForm = newEventForm;
		
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
	 * Returns appointmentlong.
	 * @return appointmentlong 
	 */
	public long getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets a value to attribute appointmentlong. 
	 * @param newappointmentlong 
	 */
	public void setTypeId(long newTypeId) {
		this.typeId = newTypeId;
	}

	/**
	 * Returns serviceId.
	 * @return serviceId 
	 */
	public long getServiceId() {
		return this.serviceId;
	}

	/**
	 * Sets a value to attribute serviceId. 
	 * @param newlong 
	 */
	public void setServiceId(long newServiceId) {
		this.serviceId = newServiceId;
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
	
	/**
	 * Returns employeeId.
	 * @return employeeId 
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute employeeId. 
	 * @param newEmployeeId 
	 */
	public void setEmployeeId(long newEmployeeId) {
		this.employeeId = newEmployeeId;
	}

}
