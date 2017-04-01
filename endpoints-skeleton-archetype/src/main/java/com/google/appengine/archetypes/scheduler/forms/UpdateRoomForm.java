package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

public class UpdateRoomForm {

	/**
	 * Description of the property services.
	 */
	private List<Long> serviceIds;

	/**
	 * Description of the property number.
	 */
	private int number;

	/**
	 * Description of the property serivces.
	 */
	private long roomId;

	

	public UpdateRoomForm(){
		
	}
	
	public UpdateRoomForm(long newRoomId, int newNumber, List<Long> newServices){

		this.number = newNumber;
		this.serviceIds = newServices;
		this.roomId = newRoomId;
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


	/**
	 * Returns room.
	 * @return room 
	 */
	public long getRoomId() {
		return this.roomId;
	}

	/**
	 * Sets a value to attribute room. 
	 * @param newroom 
	 */
	public void setRoomId(long newRoomId) {
		this.roomId = newRoomId;
	}

	
}
