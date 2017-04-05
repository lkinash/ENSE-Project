/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.archetypes.scheduler.forms.TimeBlockForm;
import com.google.appengine.archetypes.scheduler.list.Status;
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
	 * Description of the property employee.
	 */
	private String employeeName;
    
	/**
	 * Description of the property eventId.
	 */
	@Index 
	private String eventId;
	
	/**
	 * Description of the property clientId.
	 */
	private long employeeId;
	
	/**
	 * Description of the property eventId.
	 */
	@Id 
	private long appointmentId;
	
	/**
	 * Description of the property appointmentlong.
	 */
	@Index
	private long typeId;

	/**
	 * Description of the property serviceId.
	 */
	@Index
	private long serviceId;
	
	/**
	 * Description of the property serviceId.
	 */
	private String serviceName;

	/**
	 * Description of the property serviceId.
	 */
	@Index
	private long clientId;


	/**
	 * Description of the property serviceId.
	 */
	@Index
	private long roomId;
		
	/**
	 * Description of the property end
	 */
	private String date;

	
	public Appointment(){
		
	}
	
	public Appointment(Status status, String eventId, long newAppointmentId, Key<Employee> newEmployeeKey,
				long newServiceId, long newTypeId, long newClientId, long newRoomId, long newEmployeeId) {
		
		this.status = status;
		this.eventId = eventId;
		this.employeeKey = newEmployeeKey;
		this.appointmentId = newAppointmentId;
		this.serviceId = newServiceId;
		this.typeId = newTypeId;
		this.clientId = newClientId;
		this.roomId = newRoomId;
		this.date = null;
		this.employeeName = null;
		this.serviceName = null;
		this.employeeId = newEmployeeId;
		
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
	public long getClientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute eventId. 
	 * @param newEventId 
	 */
	public void setClientId(long newclientId) {
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
	 * Returns roomId.
	 * @return roomId 
	 */
	public long getRoomId() {
		return this.roomId;
	}

	/**
	 * Sets a value to attribute roomId. 
	 * @param newlong 
	 */
	public void setRoomId(long newRoomId) {
		this.roomId = newRoomId;
	}

	public String getEmployeeName(){
		return this.employeeName;
	}
	
	public void setEmployeeName(String newName){
		this.employeeName = newName;
	}
	
	
	
	public String getServiceName(){
		return this.serviceName;
	}
	
	public void setServiceName(String newName){
		this.serviceName = newName;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String newName){
		this.date = newName;
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
