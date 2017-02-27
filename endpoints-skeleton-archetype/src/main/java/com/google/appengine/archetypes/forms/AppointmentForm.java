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
	private Long clientId;

	
	public AppointmentForm(){
		
	}
	
	public AppointmentForm(Type newType, Service newService, Long newClientId){

		this.service = newService;
		this.appointmentType = newType;
		this.clientId = newClientId;
		
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

	/**
	 * Returns clientId.
	 * @return clientId 
	 */
	public Long getClientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute clientId. 
	 * @param newClientId 
	 */
	public void setClientId(Long newClientId) {
		this.clientId = newClientId;
	}

}
