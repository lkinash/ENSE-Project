/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.entities.Service;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.List;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Room.
 * 
 * @author Lindsey
 */
@Entity
public class Room {
	
	@Id 
	private long roomId;
	
	/**
	 * Description of the property number.
	 */
	private int number;

	/**
	 * Description of the property service.
	 */
	@Index
	private List<Service> services;

	/**
	 * Description of the property calendar.
	 */
	@Index
    private Calendar calendar;
	
	// Start of user code (user defined attributes for Room)
	
	// End of user code

	public Room(){
		
	}
	
	public Room(int newNumber, List<Service> newService, Calendar newCalendar, long newRoomId){
		
		this.number = newNumber;
		this.calendar = newCalendar;
		this.services = newService;
		this.roomId = newRoomId;
	}
	

	/**
	 * Description of the method setServices.
	 * @param services 
	 */
	public void setServices(List<Service> newServices) {
		this.services = newServices;
	}

	/**
	 * Description of the method getServices.
	 * @return 
	 */
	public List<Service> getServices() {
		return this.services;
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
	 * Returns calendar.
	 * @return calendar 
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets a value to attribute calendar. 
	 * @param newCalendar 
	 */
	public void setCalendar(Calendar newCalendar) {
		this.calendar = newCalendar;
	}
	
	public long getRoomId(){
		return this.roomId; 
	}

	public void setRoomId(long newRoomId){
		this.roomId = newRoomId;
	}
}
