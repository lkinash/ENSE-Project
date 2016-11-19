/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.forms;

import java.util.List;

// Start of user code (user defined imports)
import Entities.Service;
// End of user code

/**
 * Description of RoomForm.
 * 
 * @author Lindsey
 */
public class RoomForm {

	/**
	 * Description of the property services.
	 */
	private List<Service> services = null;

	/**
	 * Description of the property roomNumber.
	 */
	private int roomNumber = 0;


	// Start of user code (user defined methods for RoomForm)

	// End of user code


	/**
	 * Returns services.
	 * @return services 
	 */
	public List<Service> getServices() {
		return null;
	}

	/**
	 * Sets a value to attribute services. 
	 * @param newServices 
	 */
	public void setServices(List<Service> newServices) {
		this.services = newServices;
	}

	/**
	 * Returns roomNumber.
	 * @return roomNumber 
	 */
	public int getRoomNumber() {
		return this.roomNumber;
	}

	/**
	 * Sets a value to attribute roomNumber. 
	 * @param newRoomNumber 
	 */
	public void setRoomNumber(int newRoomNumber) {
		this.roomNumber = newRoomNumber;
	}



}
