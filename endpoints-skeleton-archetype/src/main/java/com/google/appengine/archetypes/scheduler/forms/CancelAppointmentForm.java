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
	private long roomId;
	
	
	public CancelAppointmentForm(){
		
	}
	
	public CancelAppointmentForm(Long newAppointmentId, String newReasonForCancellation, long newEmployeeId, long newClientId, long newRoomId){
		
		this.appointmentId = newAppointmentId;
		this.reasonForCancellation = newReasonForCancellation;
		this.employeeId = newEmployeeId;
		this.roomId = newRoomId;
		this.clientId = newClientId;
		
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

	
	/**
	 * Returns RoomId.
	 * @return RoomId 
	 */
	public long getRoomId() {
		return this.roomId;
	}

	/**
	 * Sets a value to attribute RoomId. 
	 * @param newRoomId 
	 */
	public void setRoomId(long newRoomId) {
		this.roomId = newRoomId;
	}
	
}
