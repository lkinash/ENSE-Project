/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.forms;

import com.google.scheduler.entities.Employee;
import com.google.scheduler.nuImage.AppointmentType;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of AppointmentForm.
 * 
 * @author Lindsey
 */
public class AppointmentForm {
	/**
	 * Description of the property apointmentType.
	 */
	private AppointmentType apointmentType = null;

	/**
	 * Description of the property treatment.
	 */
	private Object treatment = null;

	/**
	 * Description of the property preferedEmployee.
	 */
	private Employee preferedEmployee = null;

	/**
	 * Description of the property clientId.
	 */
	private Long clientId = null;

	// Start of user code (user defined methods for AppointmentForm)

	// End of user code
	/**
	 * Returns apointmentType.
	 * @return apointmentType 
	 */
	public AppointmentType getApointmentType() {
		return this.apointmentType;
	}

	/**
	 * Sets a value to attribute apointmentType. 
	 * @param newApointmentType 
	 */
	public void setApointmentType(AppointmentType newApointmentType) {
		this.apointmentType = newApointmentType;
	}

	/**
	 * Returns treatment.
	 * @return treatment 
	 */
	public Object getTreatment() {
		return this.treatment;
	}

	/**
	 * Sets a value to attribute treatment. 
	 * @param newTreatment 
	 */
	public void setTreatment(Object newTreatment) {
		this.treatment = newTreatment;
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
