/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;


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
public class Employee extends Account {
	/**
	 * Description of the property calendar.
	 */
	@Index
	private Calendar calendar;

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

	
	
	public Employee(){
		
	}
	
	public Employee(Calendar newCalendar, String newName, String newEmail, String newPassword, long newUserId, List<Long> newServiceIds){
		
		super(newEmail, newPassword, newUserId);
		
		this.calendar = newCalendar;
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
	 * Description of the method getUserId.
	 * @return 
	 */
	public long getUserId() {
		return super.getUserId();
	}
	
	public void setUserId(long newUserId) {
		super.setUserId(newUserId);
	}

	/**
	 * Description of the method getEmail.
	 * @return 
	 */
	public String getEmail() {
		return super.getEmail();
	}

	/**
	 * Description of the method setEmail.
	 * @param email 
	 */
	public void setEmail(String newEmail) {
		super.setEmail(newEmail);
	}


	/**
	 * Description of the method getPassword.
	 * @return 
	 */
	public String getPassword() {
		return super.getPassword();
	}

	/**
	 * Description of the method setPassword.
	 * @param password 
	 */
	public void setPassword(String newPassword) {
		super.setPassword(newPassword);
	}



}
