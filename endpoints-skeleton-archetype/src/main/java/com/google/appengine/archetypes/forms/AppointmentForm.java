/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

import com.google.appengine.archetypes.entities.Employee;
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
	 * Description of the property preferedEmployee.
	 */
	private Employee preferedEmployee;

	/**
	 * Description of the property clientId.
	 */
	private Long clientId;

	// Start of user code (user defined methods for AppointmentForm)

	// End of user code
	
	public AppointmentForm(){
		
	}
	
	public AppointmentForm(Type newType, Service newService, Employee newPreferedEmployee, Long newClientId){
		
		this.appointmentType = newType;
		this.clientId = newClientId;
		this.preferedEmployee = newPreferedEmployee;
		this.service = newService;
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
	 * Returns preferedEmployee.
	 * @return preferedEmployee 
	 */
	public Employee getPreferedEmployee() {
		return this.preferedEmployee;
	}

	/**
	 * Sets a value to attribute preferedEmployee. 
	 * @param newPreferedEmployee 
	 */
	public void setPreferedEmployee(Employee newPreferedEmployee) {
		this.preferedEmployee = newPreferedEmployee;
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
