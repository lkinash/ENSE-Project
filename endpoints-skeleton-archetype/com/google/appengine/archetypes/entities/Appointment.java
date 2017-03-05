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
    private Key<Employee> employeeKey;
	
	/**
	 * Description of the property eventId.
	 */
	@Index 
	private String eventId;
	
	/**
	 * Description of the property eventId.
	 */
	@Id 
	private long appointmentId;
	
	/**
	 * Description of the property appointmentType.
	 */
	@Index
	private Type appointmentType;

	/**
	 * Description of the property service.
	 */
	@Index
	private Service service;

	/**
	 * Description of the property service.
	 */
	@Index
	private long clientId;

		

	public Appointment(){
		
	}
	
	public Appointment(Status status, String eventId, long newAppointmentId, Key<Employee> newEmployeeKey, Type newType, Service newService) {
		
		this.status = status;
		this.eventId = eventId;
		this.employeeKey = newEmployeeKey;
		this.appointmentId = newAppointmentId;
		this.service = newService;
		this.appointmentType = newType;
		
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
	public String getEventId() {
		return this.eventId;
	}

	/**
	 * Sets a value to attribute eventId. 
	 * @param newEventId 
	 */
	public void setEventId(String newEventId) {
		this.eventId = newEventId;
	}
	
	/**
	 * Returns eventId.
	 * @return eventId 
	 */
	public long getAppointmentId() {
		return this.appointmentId;
	}

	/**
	 * Sets a value to attribute eventId. 
	 * @param newEventId 
	 */
	public void setAppointmentId(long newAppointmentId) {
		this.appointmentId = newAppointmentId;
	}
	
	/**
	 * Returns eventId.
	 * @return eventId 
	 */
	public long getclientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute eventId. 
	 * @param newEventId 
	 */
	public void setclientId(long newclientId) {
		this.clientId = newclientId;
	}
	
	/**
	 * Returns eventId.
	 * @return eventId 
	 */
	public Key<Employee> getEmployeeKey() {
		return this.employeeKey;
	}

	/**
	 * Sets a value to attribute eventId. 
	 * @param newEventId 
	 */
	public void setEmployeeKey(Key<Employee> newEmployeeKey) {
		this.employeeKey = newEmployeeKey;
	}

	/**
	 * Returns appointmentType.
	 * @return appointmentType 
	 */
	public Type getappointmentType() {
		return this.appointmentType;
	}

	/**
	 * Sets a value to attribute appointmentType. 
	 * @param newappointmentType 
	 */
	public void setappointmentType(Type newappointmentType) {
		this.appointmentType = newappointmentType;
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

}
