/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of CancelAppointmentForm.
 * 
 * @author Lindsey
 */
public class CancelAppointmentForm {
	/**
	 * Description of the property appointmentId.
	 */
	private Long appointmentId;

	/**
	 * Description of the property reasonForCancellation.
	 */
	private String reasonForCancellation;

	// Start of user code (user defined methods for CancelAppointmentForm)

	// End of user code
	
	public CancelAppointmentForm(){
		
	}
	
	public CancelAppointmentForm(Long newAppointmentId, String newReasonForCancellation){
		
		this.appointmentId = newAppointmentId;
		this.reasonForCancellation = newReasonForCancellation;
	}
	
	
	/**
	 * Returns appointmentId.
	 * @return appointmentId 
	 */
	public Long getAppointmentId() {
		return this.appointmentId;
	}

	/**
	 * Sets a value to attribute appointmentId. 
	 * @param newAppointmentId 
	 */
	public void setAppointmentId(Long newAppointmentId) {
		this.appointmentId = newAppointmentId;
	}

	/**
	 * Returns reasonForCancellation.
	 * @return reasonForCancellation 
	 */
	public String getReasonForCancellation() {
		return this.reasonForCancellation;
	}

	/**
	 * Sets a value to attribute reasonForCancellation. 
	 * @param newReasonForCancellation 
	 */
	public void setReasonForCancellation(String newReasonForCancellation) {
		this.reasonForCancellation = newReasonForCancellation;
	}


}
