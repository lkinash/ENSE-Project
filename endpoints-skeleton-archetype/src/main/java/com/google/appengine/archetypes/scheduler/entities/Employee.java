/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;


import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)
import com.googlecode.objectify.annotation.Index;

// End of user code

/**
 * Description of Employee.
 * 
 * @author Lindsey
 */
@Entity
public class Employee {

	/**
	 * Description of the property calendar.
	 */
	@Index
	private String calendarId;

	/**
	 * Description of the property firstName.
	 */
	@Index
	private String name;

	/**
	 * Description of the property firstName.
	 */
	@Index
	private List<Long> serviceIds;

	
	/**
	 * Description of the property AccountId.
	 */
	@Id
	private long userId;
	
	
	public Employee(){
		
	}
	
	public Employee(String newCalendarId, String newName, long newUserId, List<Long> newServiceIds){
		
		this.userId = newUserId;
		this.calendarId = newCalendarId;
		this.name = newName;
		this.serviceIds = newServiceIds;
	}

	
	/**
	 * Description of the method setServices.
	 * @param serviceIds 
	 */
	public void setServiceIds(List<Long> newServices) {
		this.serviceIds = newServices;
	}

	/**
	 * Description of the method getServices.
	 * @return 
	 */
	public List<Long> getServiceIds() {
		return this.serviceIds;
	}
	
	
	/**
	 * Returns calendarId.
	 * @return calendarId 
	 */
	public String getCalendarId() {
		return this.calendarId;
	}

	/**
	 * Sets a value to attribute calendarId. 
	 * @param newCalendarId 
	 */
	public void setCalendarId(String newCalendarId) {
		this.calendarId = newCalendarId;
	}

	/**
	 * Returns firstName.
	 * @return firstName 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute firstName. 
	 * @param newFirstName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setUserId(long newAccountId) {
		this.userId = newAccountId;
	}


}