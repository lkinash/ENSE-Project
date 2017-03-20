package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

public class RemoveRoomForm {


	/**
	 * Description of the property services.
	 */
	private long roomId;

	/**
	 * Description of the property number.
	 */
	private int number;


	// Start of user code (user defined methods for RoomForm)

	// End of user code

	public RemoveRoomForm(){
		
	}
	
	public RemoveRoomForm(int newNumber, long newRoomId){

		this.number = newNumber;
		this.roomId = newRoomId;
	}
	
	/**
	 * Returns services.
	 * @return services 
	 */
	public long getRoomId() {
		return this.roomId;
	}

	/**
	 * Sets a value to attribute services. 
	 * @param newServices 
	 */
	public void setRoomId(long newRoomId) {
		this.roomId = newRoomId;
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
