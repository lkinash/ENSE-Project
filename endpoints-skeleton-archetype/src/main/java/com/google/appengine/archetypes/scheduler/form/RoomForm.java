/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.devrel.training.conference.form;

import java.util.List;

/**
 * Description of RoomForm.
 * 
 * @author Lindsey
 */
public class RoomForm {

	/**
	 * Description of the property services.
	 */
	private List<Long> serviceIds;

	/**
	 * Description of the property number.
	 */
	private int number;


	// Start of user code (user defined methods for RoomForm)

	// End of user code

	public RoomForm(){
		
	}
	
	public RoomForm(int newNumber, List<Long> newServices){

		this.number = newNumber;
		this.serviceIds = newServices;
	}
	
	/**
	 * Returns services.
	 * @return services 
	 */
	public List<Long> getServiceIds() {
		return serviceIds;
	}

	/**
	 * Sets a value to attribute services. 
	 * @param newServices 
	 */
	public void setServiceIds(List<Long> newServiceIds) {
		this.serviceIds = newServiceIds;
	}

	/**
	 * Returns number.
	 * @return number 
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Sets a value to attribute number. 
	 * @param newNumber 
	 */
	public void setNumber(int newNumber) {
		this.number = newNumber;
	}



}
