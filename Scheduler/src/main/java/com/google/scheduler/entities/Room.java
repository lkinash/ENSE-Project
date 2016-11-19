/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.entities;

import com.google.scheduler.entities.Service;
import java.util.List;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Room.
 * 
 * @author Lindsey
 */
public class Room {
	/**
	 * Description of the property number.
	 */
	private int number = 0;

	/**
	 * Description of the property service.
	 */
	private List<Service> service = null;

	/**
	 * Description of the property calendar.
	 */
	private Object calendar = null ;
	
	// Start of user code (user defined attributes for Room)
	
	// End of user code


	/**
	 * Description of the method setRoomNumber.
	 * @param roomNumber 
	 */
	public void setRoomNumber(int roomNumber) {
		// Start of user code for method setRoomNumber
		// End of user code
	}

	/**
	 * Description of the method setServices.
	 * @param services 
	 */
	public void setServices(List services) {
		// Start of user code for method setServices
		// End of user code
	}

	/**
	 * Description of the method getServices.
	 * @return 
	 */
	public List getServices() {
		// Start of user code for method getServices
		List getServices = null;
		return getServices;
		// End of user code
	}

	// Start of user code (user defined methods for Room)

	// End of user code
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
	 * Returns service.
	 * @return service 
	 */
	public List<Service> getService() {
		return this.service;
	}

	/**
	 * Returns calendar.
	 * @return calendar 
	 */
	public Object getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets a value to attribute calendar. 
	 * @param newCalendar 
	 */
	public void setCalendar(Object newCalendar) {
		this.calendar = newCalendar;
	}

}
