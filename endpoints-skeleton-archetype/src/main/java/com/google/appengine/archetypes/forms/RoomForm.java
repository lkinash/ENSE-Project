/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

import java.util.List;

// Start of user code (user defined imports)
import com.google.appengine.archetypes.entities.Service;
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
	private List<Long> serviceIds;

	/**
	 * Description of the property roomNumber.
	 */
	private int roomNumber;


	// Start of user code (user defined methods for RoomForm)

	// End of user code

	public RoomForm(){
		
	}
	
	public RoomForm(int newRoomNumber, List<Long> newServices){

		this.roomNumber = newRoomNumber;
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
